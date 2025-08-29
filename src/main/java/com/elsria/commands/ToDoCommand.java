package com.elsria.commands;

import com.elsria.task.Task;
import com.elsria.task.TaskList;
import com.elsria.UiHandler;
import com.elsria.task.ToDoTask;

public class ToDoCommand extends AddToListCommand{

    public ToDoCommand(UiHandler uiHandler, TaskList taskList, String rawArguments) {
        super(uiHandler, taskList, rawArguments);
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
