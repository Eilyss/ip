package com.elsria.exceptions;

public class InvalidTaskSerializationException extends Exception {
    public InvalidTaskSerializationException(String serialization) {
        super(String.format("\"%s\" is not a valid serialization", serialization));
    }
}
