package com.neokortex.exceptions;

public class MissingCommandException extends RuntimeException {
    public MissingCommandException(String message) {
        super(message);
    }
}
