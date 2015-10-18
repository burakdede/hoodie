import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by burakdede on 15.10.15.
 */
public class ReflectiveInvocationHandler implements InvocationHandler {

    private final static Logger logger = LoggerFactory.getLogger(ReflectiveInvocationHandler.class.getSimpleName());

    private static Map<Method, MethodMetadata> methodCache = new HashMap<>();

    public static void putInCache(Method m, MethodMetadata metadata) {
        methodCache.putIfAbsent(m, metadata);
    }

    public static MethodMetadata getMethodFromCache(Method method) {
        return methodCache.getOrDefault(method, null);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MethodMetadata methodMetadata = getMethodFromCache(method);
        if (methodMetadata == null) {
            method.invoke(this, args);
        } else {
            //call proxy metho
        }
        return null;
    }
}
