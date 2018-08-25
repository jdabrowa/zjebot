package io.dabrowa.spotibot.spotify.oauth;

import io.dabrowa.spotibot.spotify.ApplicationCredentials;
import io.dabrowa.spotibot.spotify.SpotifyUrls;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;

public class SpotifyOAuthServer implements OAuthServer {

    private final RestTemplate restTemplate;
    private final String redirectUri;
    private final SpotifyUrls spotifyUrls;
    private final ApplicationCredentials applicationCredentials;

    public SpotifyOAuthServer(RestTemplate restTemplate, SpotifyUrls spotifyUrls, String redirectUri, ApplicationCredentials applicationCredentials) {
        this.restTemplate = restTemplate;
        this.spotifyUrls = spotifyUrls;
        this.redirectUri = redirectUri;
        this.applicationCredentials = applicationCredentials;
    }

    @Override
    @SuppressWarnings("unchecked")
    public OAuthTokens requestTokens(String authorizationCode) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> requestContent = new LinkedMultiValueMap<>();
        requestContent.set("grant_type", "authorization_code");
        requestContent.set("code", authorizationCode);
        requestContent.set("redirect_uri", redirectUri);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestContent, headers);

        Map<String, String> response = restTemplate.postForEntity(spotifyUrls.accessTokenUrl(), requestEntity, Map.class).getBody();

        return OAuthTokens.builder()
                .token(response.get("access_token"))
                .refreshToken(response.get("refresh_token"))
                .expiresAt(Instant.now().plus(Long.valueOf(response.get("expires_in")), ChronoUnit.SECONDS))
                .scope(response.get("scope"))
                .tokenType(response.get("token_type"))
                .build();
    }

    @Override
    @SuppressWarnings("unchecked")
    public OAuthTokens refreshTokens(String refreshToken) {
        MultiValueMap<String, String> requestContent = new LinkedMultiValueMap<>();
        requestContent.set("grant_type", "refresh_token");
        requestContent.set("refresh_token", refreshToken);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", new BasicAuthentication(applicationCredentials.getClientId(), applicationCredentials.getClientSecret()).headerValue());
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestContent, headers);

        Map<String, String> response = restTemplate.postForEntity(spotifyUrls.refreshTokenUrl(), requestEntity, Map.class).getBody();

        return OAuthTokens.builder()
                .token(response.get("access_token"))
                .refreshToken(refreshToken)
                .expiresAt(Instant.now().plus(Long.valueOf(response.get("expires_in")), ChronoUnit.SECONDS))
                .scope(response.get("scope"))
                .tokenType(response.get("token_type"))
                .build();
    }
}
