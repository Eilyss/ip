package com.elsria.task;

import com.elsria.time.Time;

/**
 * Represents a task with a specific deadline.
 * <p>
 * A {@code DeadlineTask} extends the base {@code Task} class with an extra
 * time component that represents the deadline. The deadline is represented
 * by a {@link Time} object.
 * </p>
 *
 * <p><b>Serialization Format:</b></p>
 * <pre>
 * D|isMarked|description|timeSerialization
 * </pre>
 * Where {@code timeSerialization} is the result of {@link Time#serialize()}.
 *
 * @see Task
 * @see Time
 */
public class DeadlineTask extends Task {
    private final Time deadline;

    /**
     * Constructs a DeadlineTask with the specified description and deadline.
     * The task is initially unmarked (not completed).
     *
     * @param description the task description.
     * @param deadline the deadline for this task.
     * @throws NullPointerException if deadline is null.
     */
    public DeadlineTask(String description, Time deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Constructs a new DeadlineTask with the specified
     * description, deadline, and completion status.
     *
     * @param description the task description.
     * @param deadline the deadline for this task.
     * @param isMarked the completion status,
     *                 (true for completed, false for incomplete)
     * @throws NullPointerException if deadline is null
     */
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

    /**
     * Returns a formatted string representation of this {@Code DeadlineTask}.
     * <p>
     * Format: {@code [D][markStatus] description, by deadline}
     * </p>
     *
     * @return a formatted string representation which includes the task
     *         description and deadline.
     */
    @Override
    public String toString() {
        return String.format("%s, by %s", super.toString(), deadline.toString());
    }

    /**
     * Serializes this {@code DeadlineTask} to a string representation for storage.
     * <p>
     * Format: {@code D|isMarked|description|timeSerialization}
     * </p>
     * <p>
     * Where {@code timeSerialization} is the result of {@link Time#serialize()}.
     * </p>
     *
     * @return a string representation of this {@code DeadlineTask} for storage
     * @see Time#serialize()
     * @see #createFromArgs(String[])
     * @see Task#baseSerialization()
     */
    @Override
    public String serialize() {
        return String.format("%s|%s", super.baseSerialization(), deadline.serialize());
    }

    /**
     * Creates a {@code DeadlineTask} from String arguments derived from its
     * serialization.
     * <p>
     * This method is used by the task {@link Task#deserialize(String)}
     * to reconstruct a {@code DeadlineTask} from it's serialized form.
     * </p>
     *
     * @param args the deserialized arguments in the following order:
     *             <ol>
     *               <li>args[0]: completion status
     *                            ("0" for incomplete, "1" for completed)</li>
     *               <li>args[1]: task description</li>
     *               <li>args[2]: serialized {@code Time} string</li>
     *             </ol>
     * @return a new reconstructed {@code DeadlineTask}
     * @throws ArrayIndexOutOfBoundsException if args has fewer than 3 elements
     * @throws NumberFormatException if the completion status cannot be parsed as an integer
     * @throws IllegalArgumentException if the Time string cannot be deserialized
     *
     * @see Task#deserialize(String)
     * @see Time#deserialize(String)
     */
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
