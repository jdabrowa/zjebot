package io.dabrowa.spotibot.telegram;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dabrowa.spotibot.telegram.protocol.UpdateDto;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/telegram")
@Slf4j
public class WebhookController {

    private final String token;

    @Autowired
    public WebhookController(@org.springframework.beans.factory.annotation.Value("${app.gateways.telegram.token}") String token) {
        log.info("Initializing controller with token: {}", token);
        this.token = token;
    }

    @ResponseBody
    @RequestMapping(path = "/webhook/{token}", method = RequestMethod.POST, consumes = "application/json", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Response> executeCallback(@PathVariable("token") String token, @RequestBody UpdateDto payload) throws JsonProcessingException {
        log.info("Got request with token: {}", token);
        if(token.equals(this.token)) {
            handle(payload);
            return ResponseEntity.ok(new Response("success"));
        }
        log.warn("Token comparison failed: '{}' vs '{}'", token, this.token);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Response("Invalid token"));
    }

    private void handle(UpdateDto payload) throws JsonProcessingException {
        log.info("Got request: {}", new ObjectMapper().writeValueAsString(payload));
    }

    @Value
    public static class Response {
        private final String status;
    }

}
