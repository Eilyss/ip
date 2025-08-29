package com.elsria.task;

import com.elsria.exceptions.InvalidTaskSerializationException;
import com.elsria.exceptions.NoSuchTaskException;

import java.util.Arrays;

public abstract class Task {
    private static final char markedSymbol = 'X';
    private static final char unmarkedSymbol = ' ';
    protected String task;
    protected boolean isMarked;

    public Task(String description) {
        this.task = description;
        this.isMarked = false;
    }

    public Task(String description, boolean isMarked) {
        this.task = description;
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

    public String getTask() {
        return this.task;
    }

    public abstract char taskType();

    @Override
    public String toString() {
        char markStatus = this.isMarked ? markedSymbol : unmarkedSymbol;
        return String.format("[%c][%c] %s", this.taskType(), markStatus, task);
    }

    public abstract String serialize();

    protected String baseSerialization() {
        int i = this.isMarked ? 1 : 0;
        return String.format("%c|%d|%s", this.taskType(), i, task);
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
}
