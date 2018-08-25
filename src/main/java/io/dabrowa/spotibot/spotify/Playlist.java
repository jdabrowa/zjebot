package io.dabrowa.spotibot.spotify;

import java.util.List;

public interface Playlist {
    List<Song> songs();
    void addSong(Song song);
    List<Playlist> split(int songsPerPlaylist);
    int size();
}
