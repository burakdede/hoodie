import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.assertNotNull;

/**
 * Created by burakdede on 16.10.15.
 */
public class HoodieTest {

    @Test
    public void testInvocation() {
        Google google = Hoodie.registerNewTarget(Google.class, "http://www.google.com.tr");
        String homepage = google.gethomePage();
        assertNotNull(homepage);
    }

    @Test
    public void testMethodCache() throws NoSuchMethodException {
        Google google = Hoodie.registerNewTarget(Google.class, "http://www.google.com");

        Method m = Google.class.getMethod("gethomePage", null);
        MethodMetadata metadataCached = ReflectiveInvocationHandler.getMethodFromCache(m);

        assertNotNull(metadataCached);
    }
}
