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

    private final static Logger LOGGER = LoggerFactory.getLogger(ReflectiveInvocationHandler.class.getSimpleName());

    private static Map<Method, MethodMetadata> methodCache = new HashMap<>();

    private String baseUrl;
    private HttpClient httpClient;

    public ReflectiveInvocationHandler(String baseUrl) {
        this.baseUrl = baseUrl;
        httpClient = new JerseyClient();
    }

    /**
     * Pass http client implementation which
     * implements basic http methods. Don't force people to use JerseyClient.
     *
     * @param baseUrl
     * @param httpClient
     */
    public ReflectiveInvocationHandler(String baseUrl, HttpClient httpClient) {
        this.baseUrl = baseUrl;
        this.httpClient = httpClient;
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
                return method.invoke(this, args);
            }
        } catch (InvocationTargetException e) {
            LOGGER.trace("Problem while invoking method", e);
        }

        return null;
    }
}
