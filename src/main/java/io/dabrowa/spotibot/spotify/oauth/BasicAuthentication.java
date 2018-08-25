package io.dabrowa.spotibot.spotify.oauth;

import java.util.Base64;

public class BasicAuthentication {

    private final String user;
    private final String password;

    public BasicAuthentication(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String headerValue() {
        String encodePortion = user + ":" + password;
        String encoded = Base64.getEncoder().encodeToString(encodePortion.getBytes());
        return "Basic " + encoded;
    }
}
