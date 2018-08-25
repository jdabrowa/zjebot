package io.dabrowa.spotibot.telegram.protocol;

import lombok.Data;

@Data
public class MessageDto {
    private long messageId;
    private UserDto from;
    private long date;
    private String text;
}
