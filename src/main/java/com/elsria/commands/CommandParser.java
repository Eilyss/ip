package com.elsria.commands;

public class CommandParser {
    private boolean endProgram;

    public CommandParser() {
        this.endProgram = false;
    }

    public boolean endProgram() {
        return this.endProgram;
    }

    public CommandRequest getCommandType(String rawInput) {
        if (rawInput == null || rawInput.trim().isEmpty()) {
            return new CommandRequest(CommandType.INVALID, rawInput);
        }

        String[] parts = rawInput.split("\\s+", 2);
        String command = parts[0];

        String[] arguments = new String[0];
        String rawArgument = "";
        if (parts.length > 1) {
            arguments = parts[1].split("\\s+");
            rawArgument = parts[1];
        }

        CommandType commandType = CommandType.getCommandType(command);

        if (commandType == CommandType.FAREWELL) {
            this.endProgram = true;
        }

        return new CommandRequest(commandType, arguments, rawArgument, rawInput);
    }
}
