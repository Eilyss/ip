package com.neokortex.exceptions;

public class InvalidTimeSerializationException extends RuntimeException {
    public InvalidTimeSerializationException(String serialization) {
        super(String.format("\"%s\" is not a valid serialization", serialization));
    }
}
