import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Entity;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by burakdede on 15.10.15.
 */
public class MethodMetadata {

    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(MethodMetadata.class.getSimpleName());

    private Method m;

    private Type returnType;

    private String path;

    private String httpRequestType;

    private Map<Integer, String> queryParams = new HashMap<>();
    private Map<Integer, String> headers = new HashMap<>();
    private Class returnClass;

    public MethodMetadata(Method m, String path, String httpRequestType) {
        this.m = m;
        this.path = path;
        this.httpRequestType = httpRequestType;
    }

    public void addNewHeader(Integer integer, String name) {
        headers.putIfAbsent(integer, name);
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

    public Method getM() {
        return m;
    }

    public void setM(Method m) {
        this.m = m;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getHttpRequestType() {
        return httpRequestType;
    }

    public void setHttpRequestType(String httpRequestType) {
        this.httpRequestType = httpRequestType;
    }

    public Map<Integer, String> getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(Map<Integer, String> queryParams) {
        this.queryParams = queryParams;
    }

    public void setReturnClass(Class returnClass) {
        this.returnClass = returnClass;
    }

    public Class getReturnClass() {
        return returnClass;
    }


    public <T> Object invoke(String url, HttpClient httpClient, Object[] args) {

        Object t = null;

        switch (httpRequestType.toLowerCase()) {
            case "get":
                 t = httpClient.get(
                        HoodieMetadataParser.parseHeaders(headers, args),
                        HoodieMetadataParser.parseQueryParams(queryParams, args),
                        url + path,
                        returnClass);
                break;
            case "post":
                // not yet implemented
                break;
            case "head":
                // not yet implemented
                break;
            case "delete":
                // not yet implemented
                break;
            default:
                logger.error("Unsupported http method type: " + httpRequestType);
                break;
        }

        return t;
    }
}
