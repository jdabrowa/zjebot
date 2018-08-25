package io.dabrowa.spotibot.spotify;

public interface Playlists {
    Playlist createNew(String name, String description);
    Playlist get(String id);
    void delete(Playlist playlist);
}
