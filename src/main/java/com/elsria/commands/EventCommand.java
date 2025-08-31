package com.elsria.commands;

import com.elsria.core.ApplicationContext;
import com.elsria.task.EventTask;
import com.elsria.task.Task;
import com.elsria.time.Time;

import java.time.LocalDateTime;

public class EventCommand extends AddToListCommand{
    public EventCommand(ApplicationContext context, CommandRequest request) {
        super(context, request);
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

        Time startTime = Time.parseTime(timings[0]);

        if (startTime == null) {
            super.errorMessage = "That is not a valid start time :P";
            return null;
        }

        Time endTime = Time.parseTime(timings[1]);

        if (endTime == null) {
            super.errorMessage = "That is not a valid end time :P";
            return null;
        }

        return new EventTask(arguments[0], startTime, endTime);
    }
}