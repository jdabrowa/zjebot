package io.dabrowa.spotibot.spotify;

public class RemoteBackendPlaylists implements Playlists {

    private final SpotifyAPI spotifyAPI;

    public RemoteBackendPlaylists(SpotifyAPI spotifyAPI) {
        this.spotifyAPI = spotifyAPI;
    }

    @Override
    public Playlist createNew(String name, String description) {
        String newPlaylistId = spotifyAPI.newPlaylistId(name, description);
        return new RemoteBackendPlaylist(newPlaylistId, spotifyAPI);
    }

    @Override
    public Playlist get(String id) {
        return null;
    }

    @Override
    public void delete(Playlist playlist) {

    }
}
