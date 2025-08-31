package com.elsria.task;

public class ToDoTask extends Task {

    public ToDoTask(String description) {
        super(description);
    }

    public ToDoTask(String description, boolean isMarked) {
        super(description, isMarked);
    }

    @Override
    public char taskType() {
        return 'T';
    }

    @Override
    public String serialize() {
        return super.baseSerialization();
    }

    public static Task createFromArgs(String[] args) {
        return new ToDoTask(args[1], Integer.parseInt(args[0]) != 0);
    }
}
