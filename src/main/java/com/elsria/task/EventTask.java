package com.elsria.task;

public class Event extends Task {
    private String start;
    private String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public Event(String description, String start, String end, boolean isMarked) {
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
}
