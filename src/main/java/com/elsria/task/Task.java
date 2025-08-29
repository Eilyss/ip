package com.elsria;

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

    public abstract Task deserialize(String task);

    protected String baseSerialization() {
        return String.format("%c|%b|%s", this.taskType(), isMarked, task);
    }

    public static Task createTaskFromString(String input) {

    }
}
