import javax.ws.rs.core.Response;

/**
 * Created by burakdede on 21.10.15.
 */
public interface Spotify {

    @Request("GET /v1/artists/{id} ")
    SpotifyArtist getArtist(@PathParam("id") String id);

    @Request("HEAD /v1/artists/{id} ")
    Response getArtistHeadReq(@PathParam("id") String id);


    @Request("GET /v1/artists/{id}/albums")
    SpotifyArtistAlbums getArtistAlbums(@PathParam("id") String id);

    @Request("DELETE /v1/artists/{id}/albums")
    SpotifyArtistAlbums deleteArtistAlbum(@PathParam("id") String id);


    @Request("GET /v1/search")
    SpotifySearchArtists searchTrack(@QueryParam("q") String songName,
                                     @QueryParam("type") String type);
}
