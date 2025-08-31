package com.elsria.commands;

import com.elsria.core.ApplicationContext;
import com.elsria.task.Task;
import com.elsria.task.ToDoTask;

public class ToDoCommand extends AddToListCommand{

    public ToDoCommand(ApplicationContext context, CommandRequest request) {
        super(context, request);
    }

    @Override
    public Task createTask(String args) {
        if (args.isEmpty()) {
            super.errorMessage = "Erm... so what task?";
            return null;
        }
        return new ToDoTask(args);
    }
}
