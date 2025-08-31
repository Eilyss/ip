package com.elsria.exceptions;

public class InvalidDateSerializationException extends RuntimeException {
    public InvalidDateSerializationException(String serialization) {
        super(String.format("\"%s\" is not a valid serialization", serialization));
    }
}
