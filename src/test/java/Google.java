import annotation.PathParam;
import annotation.QueryParam;
import annotation.Request;

import javax.ws.rs.core.Response;


/**
 * Created by burakdede on 16.10.15.
 */
public interface Google {

    @Request("GET /")
    String gethomePage();

    @Request("HEAD /")
    Response gethomeHead();

    @Request("GET /")
    String gethomePageWithQueryParms(@QueryParam("q") String query);

    @Request("GET /{username}")
    String getGithubPageForUser(@PathParam("username") String username);
}
