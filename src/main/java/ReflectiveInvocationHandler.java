import http.HttpClient;
import http.JerseyClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by burakdede on 15.10.15.
 */
public class ReflectiveInvocationHandler implements InvocationHandler {

    private final static Logger logger = LoggerFactory.getLogger(ReflectiveInvocationHandler.class.getSimpleName());

    private static Map<Method, MethodMetadata> methodCache = new HashMap<>();

    private String baseUrl;
    private HttpClient httpClient;

    public ReflectiveInvocationHandler(String baseUrl) {
        this.baseUrl = baseUrl;
        httpClient = new JerseyClient();
    }

    public static void putInCache(Method m, MethodMetadata metadata) {
        methodCache.putIfAbsent(m, metadata);
    }

    public static MethodMetadata getMethodFromCache(Method method) {
        return methodCache.getOrDefault(method, null);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MethodMetadata methodMetadata = getMethodFromCache(method);
        try {
            if (methodMetadata != null) {
                return methodMetadata.invoke(baseUrl, httpClient, args);
            } else {
                // by pass these methods
                // we do not care them let them flow
                return method.invoke(this, args);
            }
        } catch (InvocationTargetException e) {
            logger.trace("Problem while invoking method", e);
        }
        return null;
    }
}
