package com.Elsria.Commands;

import com.Elsria.Deadline;
import com.Elsria.Task;
import com.Elsria.TaskList;
import com.Elsria.UiHandler;

public class DeadlineCommand extends AddToListCommand{
    public DeadlineCommand(UiHandler uiHandler, TaskList taskList, String rawArguments) {
        super(uiHandler, taskList, rawArguments);
    }

    @Override
    public Task createTask(String rawArguments) {
        if (rawArguments.isEmpty()) {
            super.errorMessage = "Erm... so what task?";
            return null;
        }

        String[] arguments = rawArguments.split(" /by ");

        if (arguments[0].isEmpty()) {
            super.errorMessage = "Erm... so what task?";
            return null;
        }

        if (arguments.length == 1) {
            super.errorMessage = "But when?";
            return null;
        }
        if (arguments.length > 2) {
            super.errorMessage = "Woah that's too many! Which one?";
            return null;
        }

        return new Deadline(arguments[0], arguments[1]);
    }
}