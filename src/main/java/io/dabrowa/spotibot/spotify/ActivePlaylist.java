package io.dabrowa.spotibot.spotify;

import java.util.List;

public class ActivePlaylist implements Playlist {

    @Override
    public List<Song> songs() {
        return null;
    }

    @Override
    public void addSong(Song song) {

    }

    @Override
    public List<Playlist> split(int songsPerPlaylist) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
