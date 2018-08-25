package io.dabrowa.spotibot.spotify;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Slf4j
@Controller
@RequestMapping("/api/v1/spotify")
public class AuthorizationController {

    // TODO: Parse from HttpRequest
    private static final String REDIRECT_URL = "https://dabrowa.io/api/v1/spotify/accept-code";

    private final String clientId;
    private final String spotifyAuthorizeUrl;
    private final String authorizationScope;
    private final SpotifyService spotifyService;

    @Autowired
    public AuthorizationController(
            @Value("spotify.clientId") String clientId,
            @Value("spotify.authorizeUrl") String spotifyAuthorizeUrl,
            @Value("spotify.authorizationScope") String authorizationScope,
            SpotifyService spotifyService
    ) {
        this.clientId = clientId;
        this.spotifyAuthorizeUrl = spotifyAuthorizeUrl;
        this.authorizationScope = authorizationScope;
        this.spotifyService = spotifyService;
    }

    @GetMapping("/authorize")
    public RedirectView redirectToSpotifyAuthorizationPage() {
        String redirectUrl = String.format("%s?client_id=%s&response_type=code&redirect_uri=%s&scope=%s",
                spotifyAuthorizeUrl, clientId, REDIRECT_URL, authorizationScope);
        log.info("Redirecting to spotify: {}", redirectUrl);
        return new RedirectView(redirectUrl);
    }

    @GetMapping(path = "/accept-code", params = "code")
    public ResponseEntity<String> acceptAuthorizationCode(@RequestParam("code") String authorizationCode) {
        log.info("Received code, initializing spotify authorization process");
        spotifyService.initAuthorization(authorizationCode);
        log.info("Application was authorized");
        return ResponseEntity.ok("<h2>Application was authorized</h2>");
    }
}
