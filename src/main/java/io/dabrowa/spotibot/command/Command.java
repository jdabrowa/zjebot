package io.dabrowa.spotibot.command;

public interface Command {

    boolean matches(String rawCommand);
    ExecutionResult execute(String rawCommand);
}
