package io.dabrowa.spotibot;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import io.dabrowa.spotibot.spotify.ApplicationCredentials;
import io.dabrowa.spotibot.spotify.AuthorizationController;
import io.dabrowa.spotibot.spotify.SpotifyService;
import io.dabrowa.spotibot.spotify.SpotifyUrls;
import io.dabrowa.spotibot.spotify.oauth.OAuthServer;
import io.dabrowa.spotibot.spotify.oauth.OAuthToken;
import io.dabrowa.spotibot.spotify.oauth.SpotifyOAuthServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfiguration {

    @Bean
    @Primary
    public ObjectMapper mapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new AfterburnerModule());
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }

    @Bean
    public OAuthToken oAuthToken() {
        return new OAuthToken();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ApplicationCredentials applicationCredentials(
            @Value("${spotify.application.clientId}") String clientId,
            @Value("${spotify.application.clientSecret}") String clientSecret
    ) {
        return new ApplicationCredentials(clientId, clientSecret);
    }

    @Bean
    public OAuthServer oAuthServer(RestTemplate restTemplate,
                                   @Value("${spotify.account.userName}") String spotifyUserName,
                                   ApplicationCredentials applicationCredentials) {
        return new SpotifyOAuthServer(restTemplate, new SpotifyUrls(spotifyUserName), AuthorizationController.REDIRECT_URL, applicationCredentials);
    }

    @Bean
    public SpotifyService spotifyService(OAuthToken oAuthToken, OAuthServer oAuthServer) {
        return new SpotifyService(oAuthToken, oAuthServer);
    }
}
