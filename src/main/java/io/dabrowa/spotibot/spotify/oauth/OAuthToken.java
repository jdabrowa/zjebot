package io.dabrowa.spotibot.spotify.oauth;

import java.util.concurrent.atomic.AtomicReference;

public class OAuthToken {

    private final AtomicReference<String> tokenReference = new AtomicReference<>();

    public String stringToken() {
        if(tokenReference.get() == null) {
            throw new IllegalStateException("Token was not yet initialized");
        }
        return tokenReference.get();
    }

    void refresh(String newToken) {
        tokenReference.set(newToken);
    }
}
