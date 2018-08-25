package io.dabrowa.spotibot.command;

import java.util.Optional;

public interface ExecutionResult {
    boolean isSuccess();
    Optional<String> result();
}
