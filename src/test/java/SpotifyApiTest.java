import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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
