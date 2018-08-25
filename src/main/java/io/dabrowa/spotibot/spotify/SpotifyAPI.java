package io.dabrowa.spotibot.spotify;

import io.dabrowa.spotibot.protocol.CreatePlaylistRequest;
import io.dabrowa.spotibot.protocol.CreatePlaylistResponse;
import io.dabrowa.spotibot.protocol.SongDetailsResponse;
import io.dabrowa.spotibot.spotify.oauth.OAuthToken;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class SpotifyAPI {

    private final RestTemplate restTemplate;
    private final OAuthToken oAuthToken;
    private final SpotifyUrls spotifyUrls;

    public SpotifyAPI(RestTemplate restTemplate, OAuthToken oAuthToken, SpotifyUrls spotifyUrls) {
        this.restTemplate = restTemplate;
        this.oAuthToken = oAuthToken;
        this.spotifyUrls = spotifyUrls;
    }

    public String newPlaylistId(String name, String description) {
        HttpHeaders headers = spotifyHeaders();
        CreatePlaylistRequest request = CreatePlaylistRequest.builder()
                .description(description)
                .name(name)
                .isPublic(true)
                .build();
        HttpEntity<CreatePlaylistRequest> requestHttpEntity = new HttpEntity<>(request, headers);
        ResponseEntity<CreatePlaylistResponse> response = restTemplate.exchange(spotifyUrls.createPlaylistUrl(), HttpMethod.POST, requestHttpEntity, CreatePlaylistResponse.class);
        return response.getBody().getId();
    }

    public SongMetadata songMetadata(String songId) {
        String url = spotifyUrls.songDetailsUrl(songId);
        HttpHeaders headers = spotifyHeaders();
        HttpEntity<CreatePlaylistRequest> requestHttpEntity = new HttpEntity<>(headers);
        ResponseEntity<SongDetailsResponse> response = restTemplate.exchange(url, HttpMethod.GET, null, SongDetailsResponse.class);
        return new SongMetadata(response.getBody().getUri());
    }

    private HttpHeaders spotifyHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.set("Authorization", "Bearer " + oAuthToken.stringToken());
        return headers;
    }
}
