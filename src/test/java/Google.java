/**
 * Created by burakdede on 16.10.15.
 */
public interface Google {

    @Request("GET /")
    String gethomePage();

    @Request("GET /")
    String gethomePageWithQueryParms(@QueryParam("q") String query);

    @Request("GET /{username}")
    String getGithubPageForUser(@PathParam("username") String username);
}
