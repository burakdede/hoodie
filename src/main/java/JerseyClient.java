import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.util.Map;

/**
 * Created by burakdede on 18.10.15.
 */
public class JerseyClient implements HttpClient {

    private final static Client client = ClientBuilder.newClient();

    private WebTarget addHeaders(Map<String, String> headers,
                                          WebTarget target) {
        for (Map.Entry<String, String> header : headers.entrySet()) {
            target.request().header(header.getKey(), header.getValue());
        }
        return target;
    }

    @Override
    public Response head(Map<String, String> headers, String url) {
        WebTarget target = client.target(url);
        target = addHeaders(headers, target);
        Response response = target.request().head();
        return response;
    }

    @Override
    public Response get(Map<String, String> headers,
                        Map<String, String> queryParams,
                        String url) {
        WebTarget target = client.target("");
        target = addHeaders(headers, target);
        for (Map.Entry param : queryParams.entrySet()) {
            target.queryParam((String) param.getKey(), param.getValue());
        }
        Response response = target.request().get();

        return response;
    }

    @Override
    public Response post(Map<String, String> headers,
                         Entity entity,
                         String url) {
        WebTarget target = client.target("");
        target = addHeaders(headers, target);
        Response response = target.request().post(entity);

        return response;
    }

    @Override
    public Response delete(Map<String, String> headers, String url) {
        WebTarget target = client.target("");
        target = addHeaders(headers, target);
        Response response = target.request().delete();
        return response;
    }
}
