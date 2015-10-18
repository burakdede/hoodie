import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.util.Map;

/**
 * Created by burakdede on 18.10.15.
 */
public interface HttpClient {

    public Response head(Map<String, String> headers, String url);

    public <T> T get(Map<String, String> headers, Map<String, String> queryParams, String url, Class c);

    public <T> T post(Map<String, String> headers, Entity entity, String url, Class c);

    public <T> T delete(Map<String, String> headers, String url, Class c);
}
