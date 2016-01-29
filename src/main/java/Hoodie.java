import http.HttpClient;
import org.glassfish.jersey.client.ClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Proxy;

/**
 * Created by burakdede on 15.10.15.
 */
public class Hoodie {

    private final static Logger LOGGER = LoggerFactory.getLogger(Hoodie.class.getSimpleName());

    public static <T> T registerNewTarget(Class<T> clazz, String baseUrl) {
        T target;

        HoodieMetadataParser.parse(clazz);
        target = (T) Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[]{clazz},
                new ReflectiveInvocationHandler(baseUrl));

        return target;
    }

    public static <T> T registerNewTarget(Class<T> clazz, String baseUrl, HttpClient httpClient) {
        T target;

        HoodieMetadataParser.parse(clazz);
        target = (T) Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[]{clazz},
                new ReflectiveInvocationHandler(baseUrl, httpClient));

        return target;
    }
}
