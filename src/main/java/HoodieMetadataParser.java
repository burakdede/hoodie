import annotation.Header;
import annotation.QueryParam;
import annotation.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * Created by burakdede on 15.10.15.
 */
public class HoodieMetadataParser {

    private final static Logger logger = LoggerFactory.getLogger(HoodieMetadataParser.class.getSimpleName());

    public static void parse(Class claz) {

        for (Method m : claz.getMethods()) {
            if (m.isAnnotationPresent(Request.class)) {
                // parse request line
                Request requestAnnotation = m.getAnnotation(Request.class);
                String[] requestPathPair = requestAnnotation.value().split(" ");
                String httpType = requestPathPair[0];
                String path = requestPathPair[1];

                MethodMetadata methodMetadata = new MethodMetadata(m, path, httpType);

                // parse parameters and annotations
                Annotation[][] paramAnnotations = m.getParameterAnnotations();
                for (int i = 0; i < paramAnnotations.length; i++) {
                    Annotation[] paramAnnotation = paramAnnotations[i];
                    for (int j = 0; j < paramAnnotation.length; j++) {
                        Class<? extends Annotation> annotationType = paramAnnotation[j].annotationType();
                        if (annotationType == QueryParam.class) {
                            QueryParam queryParam = annotationType.getAnnotation(QueryParam.class);
                            methodMetadata.addNewQueryParam(j, queryParam.value());
                        } else if (annotationType == Header.class) {
                            Header header = annotationType.getAnnotation(Header.class);
                            methodMetadata.addNewHeader(j, header.value());
                        }
                    }
                }

                // parse return type
                Type t = m.getReturnType();
                methodMetadata.setReturnType(t);
                // parse return class
                Class returnClass = m.getReturnType();
                methodMetadata.setReturnClass(returnClass);

                // store metadata in cache for each invocation
                ReflectiveInvocationHandler.putInCache(m, methodMetadata);
            }
        }
    }
}