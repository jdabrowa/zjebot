package io.dabrowa.spotibot.spotify.commands;

import io.dabrowa.spotibot.command.Command;
import io.dabrowa.spotibot.command.ExecutionResult;
import io.dabrowa.spotibot.spotify.Playlist;

import java.util.regex.Pattern;

public class AddSongCommand implements Command {


    private final Playlist playlist;

    public AddSongCommand(Playlist playlist) {
        this.playlist = playlist;
    }

    @Override
    public boolean matches(String rawCommand) {
        return false;
    }

    @Override
    public ExecutionResult execute(String rawCommand) {
        return null;
    }
}
