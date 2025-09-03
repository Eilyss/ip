package com.elsria.task;

import com.elsria.exceptions.InvalidTaskSerializationException;

import java.util.Arrays;
import java.util.Locale;

public abstract class Task {
    private static final char markedSymbol = 'X';
    private static final char unmarkedSymbol = ' ';
    protected String description;
    protected boolean isMarked;

    public Task(String description) {
        this.description = description;
        this.isMarked = false;
    }

    public Task(String description, boolean isMarked) {
        this.description = description;
        this.isMarked = isMarked;
    }

    public void mark() {
        this.isMarked = true;
    }

    public void unmark() {
        this.isMarked = false;
    }

    public boolean isMarked() {
        return this.isMarked;
    }

    public String getDescription() {
        return this.description;
    }

    public abstract char taskType();

    public boolean containsKeyword(String keyword) {
        return this.description.toLowerCase().contains(keyword.toLowerCase());
    }

    @Override
    public String toString() {
        char markStatus = this.isMarked ? markedSymbol : unmarkedSymbol;
        return String.format("[%c][%c] %s", this.taskType(), markStatus, description);
    }

    public abstract String serialize();

    protected String baseSerialization() {
        int i = this.isMarked ? 1 : 0;
        return String.format("%c|%d|%s", this.taskType(), i, description);
    }

    public static Task deserialize(String serialization) throws InvalidTaskSerializationException {
        String[] tokens = serialization.split("\\|");

        if (tokens.length == 0 || tokens[0].length() != 1 || !TaskType.isValidTaskType(tokens[0].charAt(0))) {
            throw new InvalidTaskSerializationException(serialization);
        }

        TaskType taskType = TaskType.getTaskType(tokens[0].charAt(0));
        String[] args = Arrays.copyOfRange(tokens, 1, tokens.length);
        if (args.length != taskType.getArgCount()) {
            throw new InvalidTaskSerializationException(serialization);
        }

        return taskType.getDeserializationFunction().apply(args);
    }

    @Override
    public abstract boolean equals(Object other);
}
