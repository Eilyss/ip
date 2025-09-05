package com.elsria.task;

/**
 * Represents a simple todo task without time constraints.
 * <p>
 * A {@code ToDoTask} extends the base {@code Task} class and is the simplest
 * type of task, only requiring a description and completion status. It
 * extends directly from the base {@code Task} class without adding any
 * additional fields or constraints.
 * </p>
 *
 * <p><b>Serialization Format:</b></p>
 * <pre>
 * T|isMarked|description
 * </pre>
 *
 * @see Task
 */
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

    /**
     * Serializes this {@code ToDoTask} to a string representation for storage.
     * <p>
     * Format: {@code T|isMarked|description}
     * </p>
     * <p>
     * This uses the base serialization format since {@code ToDoTask}s don't have
     * additional fields beyond the base Task class.
     * </p>
     *
     * @return a string representation for storage
     * @see #createFromArgs(String[])
     * @see Task#baseSerialization()
     */
    @Override
    public String serialize() {
        return super.baseSerialization();
    }

    /**
     * Creates a {@code ToDoTask} from String arguments derived from its
     * serialization.
     * <p>
     * This method is used by the task {@link Task#deserialize(String)}
     * to reconstruct a {@code ToDoTask} from it's serialized form.
     * </p>
     *
     * @param args the deserialized arguments in the following order:
     *             <ol>
     *               <li>args[0]: completion status
     *                           ("0" for incomplete, "1" for completed)</li>
     *               <li>args[1]: task description</li>
     *             </ol>
     * @return a new reconstructed {@code ToDoTask}
     * @throws ArrayIndexOutOfBoundsException if args has fewer than 2 elements
     * @throws NumberFormatException if the completion status is not 0 or 1
     *
     * @see Task#deserialize(String)
     */
    public static Task createFromArgs(String[] args) {
        return new ToDoTask(args[1], Integer.parseInt(args[0]) != 0);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null) {
            return false;
        }

        if (other instanceof ToDoTask otherTask) {
            return this.description.equals(otherTask.description)
                    && this.isMarked() == otherTask.isMarked();
        }

        return false;
    }
}
