package com.elsria.exceptions;

public class NoSuchTaskException extends RuntimeException {
    public NoSuchTaskException(char taskType) {
        super(String.format("There are no such task of type '%c'", taskType));
    }
}
