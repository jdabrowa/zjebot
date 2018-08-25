package io.dabrowa.spotibot.spotify

import spock.lang.Specification
import spock.lang.Unroll

class SongUrlPatternSpec extends Specification {

    @Unroll
    void "matches valid urls: #url"(String url) {
        expect:
            new SongUrlPattern().matches(url)

        where:
            url << [
                    "http://open.spotify.com/track/f3nwjwf3NVISX",
                    "https://open.spotify.com/track/f3nwjwf3NVISX",
                    "open.spotify.com/track/f3nwjwf3NVISX",
                    "https://open.spotify.com/track/1yUZ5wgRr6SbZCwqwvbkmc",
                    "open.spotify.com/track/1yUZ5wgRr6SbZCwqwvbkmc"
            ]
    }

    @Unroll
    void "does not match invalid urls: #url"() {

        expect:
            !new SongUrlPattern().matches(url)

        where:
            url << [
                    "",
                    "test",
                    "ftp://open.spotify.com/track/345j34khn5",
                    "s://open.spotify.com/track/345j34khn5",
                    "://open.spotify.com/track/345j34khn5"
            ]
    }
}
