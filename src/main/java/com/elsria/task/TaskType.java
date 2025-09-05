package com.elsria.task;

import java.util.function.Function;

import com.elsria.exceptions.NoSuchTaskException;

/**
 * Represents the different types of tasks supported by this program as an enum.
 * <p>
 * The {@code TaskType} enum associates each unique {@code Task} subclass with
 * a character identifier, an expected argument count for deserialization,
 * and a deserialization function to create task instances from processed arguments.
 * This enum serves as the central registry for all task types and their respective
 * deserialization logic.
 * </p>
 *
 * <p><b>Supported Task Types:</b></p>
 * <ul>
 *   <li>{@code 'T'} - {@link ToDoTask} (To-do tasks)</li>
 *   <li>{@code 'D'} - {@link DeadlineTask} (Tasks with deadlines)</li>
 *   <li>{@code 'E'} - {@link EventTask} (Events with start and end times)</li>
 * </ul>
 *
 * @see ToDoTask
 * @see DeadlineTask
 * @see EventTask
 * @see NoSuchTaskException
 */
public enum TaskType {
    TODO('T', 2, ToDoTask::createFromArgs),
    DEADLINE('D', 3, DeadlineTask::createFromArgs),
    EVENT('E', 4, EventTask::createFromArgs);

    private final char taskType;
    private final int argCount;
    private final Function<String[], Task> deserializationFunction;

    TaskType(char taskType, int argCount,
             Function<String[], Task> deserializationFunction) {
        this.taskType = taskType;
        this.argCount = argCount;
        this.deserializationFunction = deserializationFunction;
    }

    /**
     * Matches the given character identifier to the corresponding {@code TaskType}
     *
     * @param taskType the character identifier to match
     * @return the corresponding {@code TaskType}
     * @throws NoSuchTaskException if no TaskType exists with the given character identifier
     *
     * @see #isValidTaskType(char)
     */
    public static TaskType getTaskType(char taskType)
            throws NoSuchTaskException {
        for (TaskType t : TaskType.values()) {
            if (t.taskType == taskType) {
                return t;
            }
        }

        throw new NoSuchTaskException(taskType);
    }

    public Function<String[], Task> getDeserializationFunction() {
        return deserializationFunction;
    }

    public int getArgCount() {
        return argCount;
    }

    /**
     * TODO: Scrap this
     * Checks if the given character represents a valid task type.
     *
     * @param taskType the character to validate
     * @return true if the character corresponds to a registered task type, false otherwise
     *
     * @see #getTaskType(char)
     */
    public static boolean isValidTaskType(char taskType) {
        for (TaskType t : TaskType.values()) {
            if (t.taskType == taskType) {
                return true;
            }
        }
        return false;
    }
}
