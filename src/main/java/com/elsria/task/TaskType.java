package com.elsria.task;

import java.util.function.Function;

import com.elsria.exceptions.NoSuchTaskException;

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

    public static boolean isValidTaskType(char taskType) {
        for (TaskType t : TaskType.values()) {
            if (t.taskType == taskType) {
                return true;
            }
        }
        return false;
    }
}
