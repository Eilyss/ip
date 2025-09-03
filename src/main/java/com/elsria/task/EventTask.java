package com.elsria.task;

import com.elsria.time.Time;

public class EventTask extends Task {
    private Time startTime;
    private Time endTime;

    public EventTask(String description, Time startTime, Time endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public EventTask(String description, Time startTime, Time endTime, boolean isMarked) {
        super(description, isMarked);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public char taskType() {
        return 'E';
    }

    @Override
    public String toString() {
        return String.format("%s, from %s to %s", super.toString(), startTime.serialize(), endTime.serialize());
    }

    @Override
    public String serialize() {
        return String.format("%s|%s|%s", super.baseSerialization(), startTime.serialize(), endTime.serialize());
    }

    public static Task createFromArgs(String[] args) {
        return new EventTask(args[1], Time.deserialize(args[2]), Time.deserialize(args[3]), Integer.parseInt(args[0]) != 0);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null) {
            return false;
        }

        if (other instanceof EventTask otherTask) {
            return this.description.equals(otherTask.description)
                    && this.isMarked() == otherTask.isMarked()
                    && this.startTime.equals(otherTask.startTime)
                    && this.endTime.equals(otherTask.endTime);
        }

        return false;
    }
}
