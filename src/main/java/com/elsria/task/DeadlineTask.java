package com.elsria.task;

import com.elsria.time.Time;

public class DeadlineTask extends Task {
    private Time deadline;

    public DeadlineTask(String description, Time deadline) {
        super(description);
        this.deadline = deadline;
    }

    public DeadlineTask(String description, Time deadline, boolean isMarked) {
        super(description, isMarked);
        this.deadline = deadline;
    }

    @Override
    public char taskType() {
        return 'D';
    }

    public Time getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {

        return String.format("%s, by %s", super.toString(), deadline.toString());
    }

    @Override
    public String serialize() {
        return String.format("%s|%s", super.baseSerialization(), deadline.serialize());
    }

    public static Task createFromArgs(String[] args) {
        return new DeadlineTask(args[1], Time.parseTime(args[2]), Integer.parseInt(args[0]) != 0);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null) {
            return false;
        }

        if (other instanceof DeadlineTask otherTask) {
            return this.description.equals(otherTask.description)
                    && this.isMarked() == otherTask.isMarked()
                    && this.deadline.equals(otherTask.deadline);
        }

        return false;
    }
}
