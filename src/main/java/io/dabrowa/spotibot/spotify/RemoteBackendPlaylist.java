package io.dabrowa.spotibot.spotify;

import java.util.List;

public class RemoteBackendPlaylist implements Playlist {

    private final SpotifyAPI spotifyAPI;
    private final String playlistId;

    public RemoteBackendPlaylist(String playlistId, SpotifyAPI spotifyAPI) {
        this.spotifyAPI = spotifyAPI;
        this.playlistId = playlistId;
    }

    @Override
    public List<Song> songs() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addSong(Song song) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Playlist> split(int songsPerPlaylist) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException();
    }
}
