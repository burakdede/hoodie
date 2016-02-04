# Hoodie - Type safe wrapper for [jersey client](https://jersey.java.net/documentation/latest/client.html)

**Hoodie** is a wrapper around the Jersey Client that makes your rest call definitions annotation based and type safe

# Quick Intro

## 1 - Define your api calls

![Define Api Calls](https://i.imgur.com/JczNXBs.png?1)

## 2 - Register your interface

![Register Interface](https://i.imgur.com/Md71Pw9.png?1)

## 3 - Make api calls

![Make api calls](https://i.imgur.com/lGbshCh.png)

# Documentation

## Setting up

If its simple http request with no complicated return types you can always use **String**
    
    public interface Google {
    
        @Request("GET /")
        String gethomePage();
    }
    
    
Before calling http method you need to register class
    
    Google google = Hoodie.registerNewTarget(Google.class, "http://www.google.com.tr");
    String homepage = google.gethomePage();

Yes, its not that impressive. It comes handy when you have shared domain classes, especially between your microservices.
Lets take more complicated example with Spotify API.

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
    
### @Request

Your first job is to use @Request annotation with signature
    
    @Request("HTTP_METHOD RELATIVE_PATH)
    
    @Request("GET /v1/search")
    
    
### @Header

If you have additional header along with your request just give them with @Header annotation
    
    @Header("KEY: VALUE")
    
    @Header("Authorization: Bearer BQDrGfA3Urfnqn3o7V-pbJn7qOB4A5LaxwzHWbKX9SHw")

### @PathParam
    
If your path need additional path parameter like id, query string etc. put them in relative path with curly
brackets and give values as method parameter wth **@PathParam** annotation.
    
    @Request(HTTP_METHOD /relative/path/{path_param})
    
    @Request("GET /v1/artists/{id} ")
    getArtist(@PathParam("id") String id);

### @QueryParam

If you want to send your parameters along with your url use @QueryParam as parameter within method signature.
All your query parameters will be added to end of url encoded.

    @QueryParam("PARAM_NAME") TYPE VAR_NAME
    
    SpotifySearchArtists searchTrack(@QueryParam("q") String songName,
                                             @QueryParam("type") String type);


### @Body

You may want to send complicated object with request body so use @Body. 

    RETURN_TYPE METHOD_NAME(@Body Object name)

    Playlist createNewPlaylist(@Body PlaylistRequest playlistRequest);
    spotify.createNewPlaylist(new PlaylistRequest());

Check other examples at test folder.


# Whats New

* ~~Added support for all http methods~~
* ~~Added support for url path parameters~~
* ~~Fix the bug related to headers~~

# Download

Get it from maven central

    <dependency>
        <groupId>com.burakdd</groupId>
        <artifactId>hoodie</artifactId>
        <version>0.0.1</version>
    </dependency>

# License
 	Copyright (C) Burak Dede.
 
 	Licensed under the Apache License, Version 2.0 (the "License");
 	you may not use this file except in compliance with the License.
 	You may obtain a copy of the License at
 
    	   http://www.apache.org/licenses/LICENSE-2.0
 	
 	Unless required by applicable law or agreed to in writing, software
 	distributed under the License is distributed on an "AS IS" BASIS,
 	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 	See the License for the specific language governing permissions and
 	limitations under the License.

  
