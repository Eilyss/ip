package com.elsria.exceptions;

public class MissingCommandException extends RuntimeException {
    public MissingCommandException(String message) {
        super(message);
    }
}
