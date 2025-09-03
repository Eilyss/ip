package com.elsria.commands;

/**
 * Utility class that facilitates parsing the user input to create
 * a command
 */
public class CommandParser {
    /**
     * Is true if the command should terminate the program
     */
    private boolean endProgram;

    public CommandParser() {
        this.endProgram = false;
    }

    public boolean endProgram() {
        return this.endProgram;
    }

    /**
     * Returns the CommandRequest that was obtained from
     * the raw user input
     *
     * @param rawInput raw user input.
     * @return Information regarding the command in the
     *         form of a CommandRequest object
     */
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
