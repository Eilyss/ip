package com.elsria.task;

import com.elsria.exceptions.InvalidTaskSerializationException;
import com.elsria.exceptions.NoSuchTaskException;
import com.elsria.exceptions.WrongTaskTypeException;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isMarked) {
        super(description, isMarked);
    }

    @Override
    public char taskType() {
        return 'T';
    }

    @Override
    public String serialize() {
        return super.baseSerialization();
    }

    public static Task createTask(String[] args) {
        return new Task(args[1], Integer.parseInt(args[0]).bool)
    }
}
