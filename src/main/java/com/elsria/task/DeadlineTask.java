package com.elsria.task;

import java.time.LocalDateTime;

public class DeadlineTask extends Task {
    private String deadline;
    private LocalDateTime deadlineDateTime;

    public DeadlineTask(String description, LocalDateTime deadline) {
        super(description);
        this.deadlineDateTime = deadline;
    }

    public DeadlineTask(String description, String deadline, boolean isMarked) {
        super(description, isMarked);
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

        return String.format("%s, by %s", super.toString(), deadlineDateTime);
    }

    @Override
    public String serialize() {
        return String.format("%s|%s", super.baseSerialization(), deadline);
    }

    public static Task createTask(String[] args) {
        return new DeadlineTask(args[1], args[2], Integer.parseInt(args[0]) != 0);
    }
}
