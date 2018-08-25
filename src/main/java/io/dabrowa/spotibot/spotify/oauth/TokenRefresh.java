package io.dabrowa.spotibot.spotify.oauth;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TokenRefresh {

    private static final Duration REFRESH_BEFORE_EXPIRE = Duration.of(60, ChronoUnit.SECONDS);

    private final OAuthToken token;
    private final OAuthServer oAuthServer;
    private final ScheduledExecutorService executor;
    private final String refreshToken;

    public TokenRefresh(OAuthToken token, OAuthServer oAuthServer, String refreshToken) {
        this.token = token;
        this.oAuthServer = oAuthServer;
        this.refreshToken = refreshToken;
        this.executor = Executors.newSingleThreadScheduledExecutor();
    }

    public void start(OAuthTokens tokens) {
        token.refresh(tokens.getToken());
        scheduleRefresh(tokens);
    }

    private void scheduleRefresh(OAuthTokens tokens) {
        Instant refreshAt = tokens.getExpiresAt().minus(REFRESH_BEFORE_EXPIRE);
        long secondsTillRefresh = refreshAt.isAfter(Instant.now()) ? (refreshAt.getEpochSecond() - Instant.now().getEpochSecond()) : 0;
        log.info("Scheduling token refresh in {} seconds", secondsTillRefresh);
        executor.schedule(this::refresh, secondsTillRefresh, TimeUnit.SECONDS);
    }

    private void refresh() {
        try {
            log.info("Refreshing Spotify authentication tokens");
            OAuthTokens newTokens = oAuthServer.refreshTokens(refreshToken);
            token.refresh(newTokens.getToken());
            scheduleRefresh(newTokens);
        } catch (Exception e) {
            log.warn("Token refresh failed", e);
        }
    }
}
