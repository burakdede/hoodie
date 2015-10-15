import org.junit.Test;

/**
 * Created by burakdede on 16.10.15.
 */
public class HoodieTest {

    @Test
    public void testInvocation() {
        Google google = Hoodie.registerNewTarget(Google.class, "http://www.google.com.tr");
        google.gethomePage();
    }
}
