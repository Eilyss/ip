package com.Elsria.Commands;

import com.Elsria.Task;
import com.Elsria.TaskList;
import com.Elsria.ToDo;
import com.Elsria.UIHandler;

import java.util.Arrays;

public class ToDoCommand extends AddToListCommand{

    public ToDoCommand(UIHandler uiHandler, TaskList taskList, String rawArguments) {
        super(uiHandler, taskList, rawArguments);
    }

    @Override
    public Task createTask(String args) {
        if (args.isEmpty()) {
            super.errorMessage = "Erm... so what task?";
            return null;
        }
        return new ToDo(args);
    }
}
