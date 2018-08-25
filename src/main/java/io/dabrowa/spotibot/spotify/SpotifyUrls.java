package io.dabrowa.spotibot.spotify;

public class SpotifyUrls {

    private final String userName;

    public SpotifyUrls(String userName) {
        this.userName = userName;
    }

    public String createPlaylistUrl() {
        return String.format(
                "https://api.spotify.com/v1/users/%s/playlists",
                userName
        );
    }

    public String songDetailsUrl(String songId) {
        return String.format("https://api.spotify.com/v1/tracks/%s", songId);
    }

    public String accessTokenUrl() {
        return "https://accounts.spotify.com/api/token";
    }

    public String refreshTokenUrl() {
        return "https://accounts.spotify.com/api/token";
    }
}
