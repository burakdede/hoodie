package http;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.util.Map;

/**
 * Created by burakdede on 18.10.15.
 */
public class JerseyClient implements HttpClient {

    private static Client client;

    public JerseyClient() {
        client = ClientBuilder.newClient().register(JacksonFeature.class);
    }

    public JerseyClient(ClientConfig clientConfig) {
        client = ClientBuilder.newClient(clientConfig).register(JacksonFeature.class);;
    }

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
    public <T> T get(Map<String, String> headers,
                        Map<String, String> queryParams,
                        String url,
                        Class c) {
        WebTarget target = client.target(url);
        target = addHeaders(headers, target);
        for (Map.Entry param : queryParams.entrySet()) {
            target = target.queryParam((String) param.getKey(), param.getValue());
        }
        T t = (T) target.request().get(c);

        return t;
    }

    @Override
    public <T> T post(Map<String, String> headers,
                         Entity entity,
                         String url,
                         Class c) {
        WebTarget target = client.target(url);
        target = addHeaders(headers, target);
        T t = (T) target.request().post(entity, c);

        return t;
    }

    @Override
    public <T> T delete(Map<String, String> headers, String url, Class c) {
        WebTarget target = client.target(url);
        target = addHeaders(headers, target);
        T t = (T) target.request().delete(c);

        return t;
    }
}
