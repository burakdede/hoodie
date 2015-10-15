import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by burakdede on 15.10.15.
 */
public class MethodMetadata {

    private Method m;

    private Type returnType;

    private String path;

    private String httpRequestType;

    private Map<Integer, String> queryParams = new HashMap<>();

    public MethodMetadata(Method m, String path, String httpRequestType) {
        this.m = m;
        this.path = path;
        this.httpRequestType = httpRequestType;
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
}
