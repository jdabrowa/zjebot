package io.dabrowa.spotibot.spotify;

import io.dabrowa.spotibot.spotify.oauth.OAuthServer;
import io.dabrowa.spotibot.spotify.oauth.OAuthToken;
import io.dabrowa.spotibot.spotify.oauth.OAuthTokens;
import io.dabrowa.spotibot.spotify.oauth.TokenRefresh;

public class SpotifyService {

    private final OAuthServer oAuthServer;
    private final OAuthToken oAuthToken;

    public SpotifyService(OAuthToken oAuthToken, OAuthServer oAuthServer) {
        this.oAuthServer = oAuthServer;
        this.oAuthToken = oAuthToken;
    }

    public void initAuthorization(String authorizationCode) {
        OAuthTokens oAuthTokens = oAuthServer.requestTokens(authorizationCode);
        new TokenRefresh(oAuthToken, oAuthServer, oAuthTokens.getRefreshToken()).start(oAuthTokens);
        // TODO: send dummy request to API to verfiy it's working before processing
    }
}
