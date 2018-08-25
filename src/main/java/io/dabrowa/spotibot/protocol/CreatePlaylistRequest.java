package io.dabrowa.spotibot.protocol;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreatePlaylistRequest {

    private String name;
    private String description;

    @JsonProperty("public")
    private boolean isPublic;
}
