package io.dabrowa.spotibot.spotify

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Profile
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringBootTest
@ActiveProfiles("integration")
class ApplicationSpec extends Specification {

    def "context starts"() {
        expect:
            1 == 1
    }
}
