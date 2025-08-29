package com.elsria.commands;

import com.elsria.task.TaskList;
import com.elsria.UiHandler;

public class CommandParser {
    private final String name;
    private final UiHandler uiHandler;
    private final TaskList taskList;
    private boolean endProgram;

    public CommandParser(String name, UiHandler uiHandler, TaskList taskList) {
        this.name = name;
        this.uiHandler = uiHandler;
        this.taskList = taskList;
        this.endProgram = false;
    }

    public boolean endProgram() {
        return endProgram;
    }

    public Command parse(String rawInput) {
        if (rawInput == null || rawInput.trim().isEmpty()) {
            return new InvalidCommand(this.uiHandler);
        }


        String[] tokens = rawInput.split("\\s+", 2);
        String command = tokens[0];

        String[] arguments = new String[0];
        String rawArgument = "";
        if (tokens.length > 1) {
            arguments = tokens[1].split("\\s+");
            rawArgument = tokens[1];
        }

        CommandContext context = new CommandContext(this.name, this.uiHandler, this.taskList, arguments, rawArgument);
        CommandType commandType = CommandType.getCommandType(command);

        if (commandType == CommandType.farewell) {
            this.endProgram = true;
        }

        return commandType.create(context);
    }
}
