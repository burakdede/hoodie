import annotation.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by burakdede on 28.10.15.
 */
public enum RequestType {
    HEAD, GET, POST, DELETE, UNSUPPORTED;

    public static RequestType findRequestType(String httpMethod) {
        switch (httpMethod.toLowerCase()) {
            case "get":
                return GET;
            case "post":
                return POST;
            case "delete":
                return DELETE;
            case "head":
                return HEAD;
            default:
                return UNSUPPORTED;
        }
    }
}
