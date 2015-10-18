import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.util.Map;

/**
 * Created by burakdede on 18.10.15.
 */
public interface HttpClient {

    public Response head();

    public Response get(Map<String, String> queryParams);

    public Response post(Entity entity);

    public Response delete();
}
