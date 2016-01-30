import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

/**
 * Created by burakdede on 21.10.15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpotifyArtistAlbums {

    private String href;

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Item {
        String album_type;

        String[] available_markets;

        String href;

        String id;

        List<Map<String, String>> images;

        Map<String, String> external_urls;

        public Item() {
        }

        public String getAlbum_type() {
            return album_type;
        }

        public void setAlbum_type(String album_type) {
            this.album_type = album_type;
        }

        public String[] getAvailable_markets() {
            return available_markets;
        }

        public void setAvailable_markets(String[] available_markets) {
            this.available_markets = available_markets;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<Map<String, String>> getImages() {
            return images;
        }

        public void setImages(List<Map<String, String>> images) {
            this.images = images;
        }

        public Map<String, String> getExternal_urls() {
            return external_urls;
        }

        public void setExternal_urls(Map<String, String> external_urls) {
            this.external_urls = external_urls;
        }
    }

    private List<Item> items;

    private Integer limit;

    private String next;

    private Integer offset;

    private String previous;

    private Integer total;

    public SpotifyArtistAlbums() {
    }


    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
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

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
