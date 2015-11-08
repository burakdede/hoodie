import annotation.Request;

import javax.ws.rs.core.Response;

/**
 * Created by burakdede on 9.11.15.
 */
public interface HoodieRest {
    @Request("GET /rest")
    String get();

    @Request("POST /rest")
    String post();

    @Request("HEAD /rest")
    Response head();

    @Request("DELETE /rest")
    String delete();
}
