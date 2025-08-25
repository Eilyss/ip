package com.Elsria;

public abstract class Task {
    protected String task;
    protected boolean isDone;

    public Task(String description) {
        this.task = description;
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getTask() {
        return this.task;
    }

    public abstract char taskType();

    @Override
    public String toString() {
        char markStatus = this.isDone ? 'X' : ' ';
        return String.format("[%c][%c] %s", this.taskType(), markStatus, task);
    }
}
