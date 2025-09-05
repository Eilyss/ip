package com.elsria.task;

import com.elsria.time.Time;

/**
 * Represents an event with a specific start and end time.
 * <p>
 * An {@code DeadlineTask} extends the base {@code Task} class with additional
 * time components, representing when the event start and ends. Both times are
 * represented by {@link Time} objects.
 * </p>
 *
 * <p><b>Serialization Format:</b></p>
 * <pre>
 * E|isMarked|description|startTimeSerialization|endTimeSerialization
 * </pre>
 * Where {@code startTimeSerialization} and {@code endTimeSerialization} are
 * the results of {@link Time#serialize()}.
 *
 * @see Task
 * @see Time
 */
public class EventTask extends Task {
    private final Time startTime;
    private final Time endTime;

    /**
     * Constructs a new {@code EventTask} with the specified description,
     * start time, and end time. The task is initially unmarked (not completed).
     *
     * @param description the event description.
     * @param startTime the start time of the event.
     * @param endTime the end time of the event.
     * @throws NullPointerException if startTime or endTime is null.
     */
    public EventTask(String description, Time startTime, Time endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Constructs a new EventTask with the specified description,
     * start time, end time, and completion status.
     *
     * @param description the event description
     * @param startTime the start time of the event.
     * @param endTime the end time of the event.
     * @param isMarked the initial completion status
     *                 (true for completed, false for incomplete)
     * @throws NullPointerException if startTime or endTime is null
     */
    public EventTask(String description, Time startTime,
                     Time endTime, boolean isMarked) {
        super(description, isMarked);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public char taskType() {
        return 'E';
    }

    /**
     * Returns a formatted string representation of this {@code EventTask}.
     * <p>
     * Format: {@code [E][markStatus] description, from startTime to endTime}
     * </p>
     *
     * @return a formatted string representation including the event details and time range
     */
    @Override
    public String toString() {
        return String.format(
                "%s, from %s to %s",
                super.toString(),
                startTime.serialize(),
                endTime.serialize()
        );
    }

    /**
     * Serializes this {@code EventTask} to a string representation for storage.
     * <p>
     * Format: {@code E|isMarked|description|startTimeSerialization|endTimeSerialization}
     * </p>
     * <p>
     * Where {@code startTimeSerialization} and {@code endTimeSerialization} are
     * the results of {@link Time#serialize()}.
     * </p>
     *
     * @return a string representation of this {@code EventTask} for storage
     * @see Time#serialize()
     * @see #createFromArgs(String[])
     * @see Task#baseSerialization()
     */
    @Override
    public String serialize() {
        return String.format(
                "%s|%s|%s",
                super.baseSerialization(),
                startTime.serialize(),
                endTime.serialize()
        );
    }

    /**
     * Creates an {@code EventTask} from String arguments derived from its
     * serialization.
     * <p>
     * This method is used by the task {@link Task#deserialize(String)}
     * to reconstruct a {@code EventTask} from it's serialized form.
     * </p>
     *
     * @param args the deserialized arguments in the following order:
     *             <ol>
     *               <li>args[0]: completion status
     *                            ("0" for incomplete, "1" for completed)</li>
     *               <li>args[1]: event description</li>
     *               <li>args[2]: serialized starting {@code Time} string</li>
     *               <li>args[3]: serialized ending {@code Time} string</li>
     *             </ol>
     * @return a new reconstructed {@code EventTask}
     * @throws ArrayIndexOutOfBoundsException if args has fewer than 4 elements
     * @throws NumberFormatException if the completion status cannot be parsed as an integer
     * @throws IllegalArgumentException if the Time strings cannot be deserialized
     *
     * @see Task#deserialize(String)
     * @see Time#deserialize(String)
     */
    public static Task createFromArgs(String[] args) {
        return new EventTask(
                args[1],
                Time.deserialize(args[2]),
                Time.deserialize(args[3]),
                Integer.parseInt(args[0]) != 0
        );
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
