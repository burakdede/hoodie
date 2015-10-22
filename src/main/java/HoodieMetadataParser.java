import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.ws.Response;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.net.URLDecoder;
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
        }

        return newHeaders;
    }

    public static Map<String, String> parseQueryParams(Map<Integer, String> queryParams, Object[] args) {
        Map<String, String> newQueryParams = new HashMap<>();
        for (Map.Entry<Integer, String> param : queryParams.entrySet()) {
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
            path = path.replaceAll("\\{(" + pathParam.getValue() + ")\\}", paramEncoded);
        }
        return path;
    }

    public static void parse(Class claz) {

        MethodMetadata methodMetadata = null;

        for (Method m : claz.getMethods()) {
            if (m.isAnnotationPresent(Request.class)) {
                // parse request line
                Request requestAnnotation = m.getAnnotation(Request.class);
                String[] requestPathPair = requestAnnotation.value().split(" ");
                String httpType = requestPathPair[0];
                String path = requestPathPair[1];

                methodMetadata = new MethodMetadata(m, path, httpType);

                // parse return type
                Type t = m.getReturnType();
                if (httpType.equalsIgnoreCase("HEAD") && m.getReturnType() != Response.class) {
                    logger.error("HEAD request can only return Response as return type");
                }
                methodMetadata.setReturnType(t);

                // parse parameters and annotations

                Annotation[][] paramAnnotations = m.getParameterAnnotations();
                for (int i = 0; i < paramAnnotations.length; i++) {
                    Annotation[] paramAnnotation = paramAnnotations[i];
                    for (int j = 0; j < paramAnnotation.length; j++) {
                        Class<? extends Annotation> annotationType = paramAnnotation[j].annotationType();
                        if (annotationType == QueryParam.class) {
                            QueryParam queryParam = (QueryParam) paramAnnotation[j];
                            methodMetadata.addNewQueryParam(i, queryParam.value());
                        } else if (annotationType == PathParam.class) {
                            PathParam pathParam = (PathParam) paramAnnotation[j];
                            methodMetadata.addNewPathParam(j, pathParam.value());
                        } else if (annotationType == Body.class) {
                            methodMetadata.setBody(m.getAnnotatedParameterTypes()[j].getType());
                        }
                    }
                }


            }

            if (m.isAnnotationPresent(Header.class)) {
                Header header = m.getAnnotation(Header.class);
                String[] headerKeyValue = header.value().split(":");
                methodMetadata.addNewHeader(headerKeyValue[0], headerKeyValue[1]);
            }

            // parse return class
            Class returnClass = m.getReturnType();
            methodMetadata.setReturnClass(returnClass);

            // store metadata in cache for each invocation
            ReflectiveInvocationHandler.putInCache(m, methodMetadata);
        }
    }
}
