import annotation.Request;
import http.HttpClient;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Entity;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by burakdede on 15.10.15.
 */
public class MethodMetadata<T> {

    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(MethodMetadata.class.getSimpleName());

    private Method m;

    private Type returnType;

    private String path;

    private RequestType httpRequestType;

    private Map<Integer, String> queryParams = new HashMap<>();
    private Map<String, String> headers = new HashMap<>();
    private Map<Integer, String> pathParams = new HashMap<>();

    private Type body;

    private Class returnClass;

    public MethodMetadata(Method m, String path, RequestType httpRequestType) {
        this.m = m;
        this.path = path;
        this.httpRequestType = httpRequestType;
    }

    public Type getBody() {
        return body;
    }

    public void setBody(Type body) {
        this.body = body;
    }

    public void addNewPathParam(Integer integer, String name) {
        pathParams.putIfAbsent(integer, name);
    }

    public void addNewHeader(String key, String value) {
        headers.putIfAbsent(key, value);
    }

    public void addNewQueryParam(Integer index, String name) {
        queryParams.putIfAbsent(index, name);
    }

    public Type getReturnType() {
        return returnType;
    }

    public void setReturnType(Type returnType) {
        this.returnType = returnType;
    }


    public <T> Object invoke(String url, HttpClient httpClient, Object[] args) {

        Object t = null;
        String fullPath = url + path;

        switch (httpRequestType) {
            case GET:
                 t = httpClient.get(
                        headers,
                        HoodieMetadataParser.parseQueryParams(queryParams, args),
                        HoodieMetadataParser.replacePathParams(fullPath, pathParams, args),
                        returnClass);
                break;
            case POST:
                 t = httpClient.post(
                        headers,
                        Entity.json(body),
                        HoodieMetadataParser.replacePathParams(fullPath, pathParams, args),
                        returnClass
                    );
                break;
            case HEAD:
                t = httpClient.head(
                        headers,
                        HoodieMetadataParser.replacePathParams(fullPath, pathParams, args)
                    );
                break;
            case DELETE:
                httpClient.delete(
                        headers,
                        HoodieMetadataParser.replacePathParams(fullPath, pathParams, args),
                        returnClass
                    );
                break;
            default:
                logger.error("Unsupported http method type: " + httpRequestType);
                break;
        }

        return t;
    }
}
