import com.burakdede.Hoodie;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by burakdede on 21.10.15.
 */
public class SpotifyApiTest {

    private Spotify spotify;

    @Before
    public void registerTarget() {
        spotify = Hoodie.registerNewTarget(Spotify.class, "https://api.spotify.com");
    }

    @Test
    public void testGetArtist() {
        SpotifyArtist artist = spotify.getArtist("0OdUWJ0sBjDrqHygGUXeCF");
        assertNotNull(artist);
    }

    @Test
    public void testGetArtistHead() {
        Response response = spotify.getArtistHeadReq("0OdUWJ0sBjDrqHygGUXeCF");

        assertEquals(response.getStatus(), 200);
    }

    @Test
    public void testGetArtistAlbums() {
        SpotifyArtistAlbums albums = spotify.getArtistAlbums("1vCWHaC5f2uS3yhpwWbIA6");
        assertNotNull(albums);
    }

    @Test
    public void testSearchTrack() throws UnsupportedEncodingException {

        SpotifySearchArtists searchResults = spotify.searchTrack("tania%20bowra", "artist");
        assertNotNull(searchResults);
    }
}
