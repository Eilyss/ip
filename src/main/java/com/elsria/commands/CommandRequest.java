package com.elsria.commands;

public class CommandRequest {
    private final CommandType commandType;
    private final String[] args;
    private final String rawArguments;
    private final String originalCommand;

    public CommandRequest(CommandType commandType, String[] args, String rawArguments, String originalCommand) {
        this.commandType = commandType;
        this.args = args;
        this.rawArguments = rawArguments;
        this.originalCommand = originalCommand;
    }

    public CommandRequest(CommandType commandType, String originalCommand) {
        this.commandType = commandType;
        this.args = new String[0];
        this.rawArguments = "";
        this.originalCommand = originalCommand;
    }

    public CommandType getCommandType() {
        return this.commandType;
    }

    public String[] getArgs() {
        return this.args;
    }

    public String getRawArgs() {
        return this.rawArguments;
    }
}
