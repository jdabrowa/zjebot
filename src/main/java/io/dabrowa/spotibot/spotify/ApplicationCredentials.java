package io.dabrowa.spotibot.spotify;

import lombok.Value;

@Value
public class ApplicationCredentials {
    private final String clientId;
    private final String clientSecret;
}
