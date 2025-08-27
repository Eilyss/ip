package com.Elsria;

import java.util.Arrays;
import java.util.Optional;

public class CommandParser {
    private final String name;
    private final UIHandler uiHandler;
    private final TaskList taskList;

    public CommandParser(String name, UIHandler uiHandler, TaskList taskList) {
        this.name = name;
        this.uiHandler = uiHandler;
        this.taskList = taskList;
    }

    public Command parse(String rawInput) {
        String[] tokens = rawInput.split(" ");
        String command = tokens[0];
        String[] arguments = Arrays.copyOfRange(tokens, 1, tokens.length);
        CommandContext context = new CommandContext(this.name, this.uiHandler, this.taskList, arguments);

        return CommandType.getCommandType(command).create(context);
    }
}
