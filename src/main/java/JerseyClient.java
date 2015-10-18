import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.Map;

/**
 * Created by burakdede on 18.10.15.
 */
public class JerseyClient implements HttpClient {

    private final static Client client = ClientBuilder.newClient();

    @Override
    public Response head() {
        WebTarget target = client.target("");
        Response response = target.request().head();
        return response;
    }

    @Override
    public Response get(Map<String, String> queryParams) {
        WebTarget target = client.target("");
        for (Map.Entry param : queryParams.entrySet()) {
            target.queryParam((String) param.getKey(), param.getValue());
        }
        Response response = target.request().get();

        return response;
    }

    @Override
    public Response post(Entity entity) {
        WebTarget target = client.target("");
        Response response = target.request().post(entity);

        return response;
    }

    @Override
    public Response delete() {
        WebTarget target = client.target("");
        Response response = target.request().delete();
        return response;
    }
}
