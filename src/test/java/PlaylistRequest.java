import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * Created by burakdede on 22.10.15.
 */
public class PlaylistRequest {

    private String name;

    private boolean _public;

    public PlaylistRequest(String name, boolean _public) {
        this.name = name;
        this._public = _public;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean is_public() {
        return _public;
    }

    public void set_public(boolean _public) {
        this._public = _public;
    }
}
