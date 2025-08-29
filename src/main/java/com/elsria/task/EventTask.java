package com.elsria.task;

public class EventTask extends Task {
    private String start;
    private String end;

    public EventTask(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public EventTask(String description, String start, String end, boolean isMarked) {
        super(description, isMarked);
        this.start = start;
        this.end = end;
    }

    @Override
    public char taskType() {
        return 'E';
    }

    @Override
    public String toString() {
        return String.format("%s, from %s to %s", super.toString(), start, end);
    }

    @Override
    public String serialize() {
        return String.format("%s|%s|%s", super.baseSerialization(), start, end);
    }

    public static Task createTask(String[] args) {
        return new EventTask(args[1], args[2], args[3], Integer.parseInt(args[0]) != 0);
    }
}
