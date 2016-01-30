import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by burakdede on 22.10.15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Playlist {

    private String id;

    private String name;

    private String type;

    private String uri;

    public Playlist() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
