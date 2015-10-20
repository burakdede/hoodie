/**
 * Created by burakdede on 21.10.15.
 */
public interface Spotify {

    @Request("GET /v1/artists/{id} ")
    SpotifyArtist getArtist(@PathParam("id") String id);


    @Request("GET /v1/artists/{id}/albums")
    SpotifyArtistAlbums getArtistAlbums(@PathParam("id") String id);


    @Request("GET /v1/search")
    SpotifySearchArtists searchTrack(@QueryParam("q") String songName,
                                     @QueryParam("type") String type);
}
