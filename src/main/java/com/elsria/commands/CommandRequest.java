package com.elsria.commands;

/**
 * A class that encapsulates all details regarding
 * a user's command
 */
public class CommandRequest {
    private final CommandType commandType;
    private final String[] args;
    private final String rawArguments;
    private final String originalCommand;

    /**
     * Default constructor for a CommandRequest
     *
     * @param commandType the commandType of the command based
     *                    on the enum
     * @param args the arguments of the command, equivalent to
     *             breaking the original input into tokens
     * @param rawArguments strippes the command word from user
     *                     input
     * @param originalCommand the full original user input
     */
    public CommandRequest(CommandType commandType, String[] args,
                          String rawArguments, String originalCommand) {
        this.commandType = commandType;
        this.args = args;
        this.rawArguments = rawArguments;
        this.originalCommand = originalCommand;
    }

    /**
     * Secondary comman constructor for commands that do not have
     * as many details. Primarily used for the invalid command.
     * It reduced bloat in parameters
     *
     * @param commandType the commandType of the command based
     *                    on the enum
     * @param originalCommand the full original user input
     */
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
