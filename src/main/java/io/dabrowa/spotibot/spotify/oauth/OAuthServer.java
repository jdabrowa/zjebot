package io.dabrowa.spotibot.spotify.oauth;

public interface OAuthServer {
    OAuthTokens requestTokens(String authorizationCode);
    OAuthTokens refreshTokens(String refreshToken);
}
