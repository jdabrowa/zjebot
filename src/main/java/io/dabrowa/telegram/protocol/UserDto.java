package io.dabrowa.telegram.protocol;

import lombok.Data;

@Data
public class UserDto {
    private long id;
    private String username;
    private boolean isBot;
}
