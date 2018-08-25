package io.dabrowa.spotibot.telegram.protocol;

import lombok.Data;

@Data
public class UpdateDto {

    private long updateId;
    private MessageDto message;
}
