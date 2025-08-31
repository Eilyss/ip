package com.elsria.commands;

import com.elsria.core.ApplicationContext;
import com.elsria.task.DeadlineTask;
import com.elsria.task.Task;
import com.elsria.time.Time;

import java.time.LocalDateTime;

public class DeadlineCommand extends AddToListCommand{
    public DeadlineCommand(ApplicationContext context, CommandRequest request) {
        super(context, request);
    }

    @Override
    public Task createTask(String rawArguments) {
        if (rawArguments.isEmpty()) {
            super.errorMessage = "Erm... so what task?";
            return null;
        }

        String[] arguments = rawArguments.split(" /by ");

        if (arguments[0].isEmpty()|| rawArguments.startsWith("/")) {
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

        LocalDateTime time = Time.convertToTime(arguments[1]);

        if (time == null) {
            super.errorMessage = "That is not a valid time :P";
            return null;
        }

        return new DeadlineTask(arguments[0], Time.convertToTime(arguments[1]));
    }
}