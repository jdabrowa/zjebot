package io.dabrowa.spotibot.spotify;

import java.util.regex.Pattern;

public class SongUrlPattern {

    private final Pattern songUrlPattern = Pattern.compile("((http)s?://)?open.spotify.com/track/[a-zA-Z0-9]+?");

    public boolean matches(String songUrlCandidate) {
        return songUrlPattern.matcher(songUrlCandidate).matches();
    }
}
