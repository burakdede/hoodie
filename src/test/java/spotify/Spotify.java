package spotify;

import annotation.*;

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


    @Header("Authorization: Bearer BQDrGfA3Urfnqn3o7V-pbJn7qOB4A5LaxwzHWbKX9SHwrYaRTSWM1W6qByQ2Z3Gpq_-0vWacIch276yFZrMQamX-pUA7jnrromnvF0jl6i1Vd-QXGx8eaxJ9frZU0VCjpBvXET5YMFDL0OugHL_7S-emoDKPJQ")
    @Request("POST /v1/users/114895047/playlists")
    Playlist createNewPlaylist(@Body PlaylistRequest playlistRequest);


    @Request("GET /v1/search")
    SpotifySearchArtists searchTrack(@QueryParam("q") String songName,
                                     @QueryParam("type") String type);
}
