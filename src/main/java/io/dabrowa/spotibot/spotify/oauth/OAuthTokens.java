package io.dabrowa.spotibot.spotify.oauth;

import lombok.Builder;
import lombok.Value;

import java.time.Instant;

@Value
@Builder
public class OAuthTokens {
    private final String token;
    private final String refreshToken;
    private final String tokenType;
    private final Instant expiresAt;
    private final String scope;
}
