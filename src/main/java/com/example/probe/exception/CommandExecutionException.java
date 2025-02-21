package com.example.probe.exception;

/**
 * Custom exception class for command execution failures.
 */
public class CommandExecutionException extends RuntimeException {
    public CommandExecutionException(String message, Throwable cause) {
        super(message, cause);
    }
}
