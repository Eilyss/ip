package com.Elsria;

public class ToDo extends Task{

    public ToDo(String description) {
        super(description);
    }

    @Override
    public char taskType() {
        return 'T';
    }
}
