package com.elsria.commands;

import com.elsria.task.EventTask;
import com.elsria.task.Task;
import com.elsria.task.TaskList;
import com.elsria.UiHandler;

public class EventCommand extends AddToListCommand{
    public EventCommand(UiHandler uiHandler, TaskList taskList, String rawArguments) {
        super(uiHandler, taskList, rawArguments);
    }

    @Override
    public Task createTask(String rawArguments) {
        if (rawArguments.isEmpty()) {
            super.errorMessage = "Erm... so what task?";
            return null;
        }

        String[] arguments = rawArguments.split(" /from ");

        if (arguments[0].isEmpty()) {
            super.errorMessage = "Erm... so what task?";
            return null;
        }

        if (arguments.length == 1) {
            super.errorMessage = "But from when?";
            return null;
        }

        if (arguments.length > 2) {
            super.errorMessage = "Woah that's too many start times! Which one?";
            return null;
        }

        String[] timings = arguments[1].split(" /to ");

        if (timings.length == 1) {
            super.errorMessage = "But to when?";
            return null;
        }

        if (timings.length > 2) {
            super.errorMessage = "Woah that's too many end times! Which one?";
            return null;
        }

        return new EventTask(arguments[0], timings[0], timings[1]);
    }
}