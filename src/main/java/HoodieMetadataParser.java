import annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by burakdede on 15.10.15.
 */
public class HoodieMetadataParser {

    private final static Logger logger = LoggerFactory.getLogger(HoodieMetadataParser.class.getSimpleName());

    public static Map<String, String> parseHeaders(Map<Integer, String> headers, Object[] args) {
        Map<String, String> newHeaders = new HashMap<>();
        for (Map.Entry<Integer, String> header : headers.entrySet()) {
            newHeaders.putIfAbsent((String) args[header.getKey()], header.getValue());
            logger.debug("Header key: " + header.getKey() + " value: " + header.getValue());
        }

        return newHeaders;
    }

    /**
     * Parse query parameters given
     *
     * @param queryParams
     * @param args
     * @return
     */
    public static Map<String, String> parseQueryParams(Map<Integer, String> queryParams, Object[] args) {
        logger.debug("Parsing query parameters");
        Map<String, String> newQueryParams = new HashMap<>();
        for (Map.Entry<Integer, String> param : queryParams.entrySet()) {
            logger.debug("Query param key: " + param.getValue() + " value: " + args[param.getKey()]);
            newQueryParams.putIfAbsent(param.getValue(), (String) args[param.getKey()]);
        }

        return newQueryParams;
    }

    public static String replacePathParams(String path, Map<Integer, String> pathParams, Object[] args) {
        String paramEncoded = null;
        for (Map.Entry<Integer, String> pathParam : pathParams.entrySet()) {
            Object paramVal = args[pathParam.getKey()];
            try {
                paramEncoded = URLEncoder.encode((String) paramVal, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                logger.error("Can not encode given path param => " + paramVal);
                e.printStackTrace();
            }
            logger.debug("Replacing path " + path + " with " + pathParam.getValue());
            path = path.replaceAll("\\{(" + pathParam.getValue() + ")\\}", paramEncoded);
        }
        return path;
    }

    public static void parse(Class claz) {

        MethodMetadata methodMetadata = null;

        for (Method m : claz.getMethods()) {
            if (m.isAnnotationPresent(Request.class)) {

                // parse request line
                logger.debug("Parsing request line");
                Request requestAnnotation = m.getAnnotation(Request.class);
                String[] requestPathPair = requestAnnotation.value().split(" ");
                String httpType = requestPathPair[0];
                String path = requestPathPair[1];
                RequestType requestType = RequestType.findRequestType(httpType);
                logger.debug("Http request type: " + httpType);
                logger.debug("Request path: " + path);


                methodMetadata = new MethodMetadata(m, path, requestType);

                // parse return type
                logger.debug("Parsing return type");
                Type t = m.getReturnType();
                if (httpType.equalsIgnoreCase("HEAD") && m.getReturnType() != Response.class) {
                    logger.error("HEAD only support javax.ws.rs.core.Response as return type.");
                }

                methodMetadata.setReturnType(t);
                methodMetadata.setReturnClass(m.getReturnType());

                logger.debug("Return type: " + t.getTypeName());


                // parse parameters and annotations
                Annotation[][] paramAnnotations = m.getParameterAnnotations();
                for (int i = 0; i < paramAnnotations.length; i++) {
                    Annotation[] paramAnnotation = paramAnnotations[i];
                    for (int j = 0; j < paramAnnotation.length; j++) {
                        Class<? extends Annotation> annotationType = paramAnnotation[j].annotationType();
                        if (annotationType == QueryParam.class) {
                            QueryParam queryParam = (QueryParam) paramAnnotation[j];
                            methodMetadata.addNewQueryParam(i, queryParam.value());
                            logger.debug("Query param: " + queryParam.value());
                        } else if (annotationType == PathParam.class) {
                            PathParam pathParam = (PathParam) paramAnnotation[j];
                            methodMetadata.addNewPathParam(j, pathParam.value());
                            logger.debug("Path param: " + pathParam.value());
                        } else if (annotationType == Body.class) {
                            methodMetadata.setBody(m.getAnnotatedParameterTypes()[j].getType());
                            logger.debug("Body: " + m.getAnnotatedParameterTypes()[j].getType());
                        }
                    }
                }
            }

            // parse header key,values
            if (m.isAnnotationPresent(Header.class)) {
                Header header = m.getAnnotation(Header.class);
                String[] headerKeyValue = header.value().split(":");
                methodMetadata.addNewHeader(headerKeyValue[0], headerKeyValue[1]);
                logger.debug("Header: " + headerKeyValue[0] + " " + headerKeyValue[1]);
            }

            // store metadata in cache for each invocation
            ReflectiveInvocationHandler.putInCache(m, methodMetadata);
        }
    }
}
