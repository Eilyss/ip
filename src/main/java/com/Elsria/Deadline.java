package com.Elsria;

public class Deadline extends Task {
    private String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public char taskType() {
        return 'D';
    }

    public String getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return String.format("%s, by %s", super.toString(), deadline);
    }

    @Override
    public String serialize() {
        return String.format("%s|%s", super.baseSerialization(), deadline);
    }
}
