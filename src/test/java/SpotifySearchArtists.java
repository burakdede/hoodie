import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by burakdede on 21.10.15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpotifySearchArtists {

    private String href;

    private List<SpotifyArtist> items;

    private Integer limit;

    private String next;

    private String previous;

    private Integer offset;

    private Integer total;

    public SpotifySearchArtists() {
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<SpotifyArtist> getItems() {
        return items;
    }

    public void setItems(List<SpotifyArtist> items) {
        this.items = items;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
