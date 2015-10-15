import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by burakdede on 15.10.15.
 */
public class ReflectiveInvocationHandler implements InvocationHandler {

    private static Map<Method, MethodMetadata> methodCache = new HashMap<>();

    public static void putInCache(Method m, MethodMetadata metadata) {
        methodCache.putIfAbsent(m, metadata);
    }

    public static MethodMetadata getMethodFromCache(Method method) {
        return methodCache.getOrDefault(method, null);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}
