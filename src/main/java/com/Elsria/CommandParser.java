package com.Elsria;

import com.Elsria.Commands.AddCommand;
import com.Elsria.Commands.EchoCommand;
import com.Elsria.Commands.InvalidCommand;

public class CommandParser {
    private final String name;
    private final UIHandler uiHandler;
    private final TaskList taskList;
    private boolean endProgram;

    public CommandParser(String name, UIHandler uiHandler, TaskList taskList) {
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

        if (commandType == CommandType.invalid) {
            return new AddCommand(this.uiHandler, this.taskList, new ToDo(rawInput));
        }
        return commandType.create(context);
    }
}
