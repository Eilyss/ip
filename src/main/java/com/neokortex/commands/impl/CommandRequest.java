package com.neokortex.commands.impl;

import com.neokortex.commands.CommandType;

/**
 * Represents a container class that stores the details of the user's command.
 * <p>
 * The {@code CommandRequest} class holds all necessary details provided by
 * the user when they attempt to run a command, like the commandType and
 * its arguments, raw or tokenized.
 * </p>
 *
 * <p><b>Purpose:</b></p>
 * <ul>
 *     <li>Encapsulate a users command input</li>
 *     <li>Contain both tokenized arguments and raw arguments</li>
 * </ul>
 *
 * <p><b>Components:</b></p>
 * <ul>
 *     <li>{@link #getCommandType()}: Type of {@code Command} to run</li>
 *     <li>{@link #getTokens()}: Tokenized arguments for {@code Command} execution</li>
 *     <li>{@link #getRawArgs()}: Tokenized arguments for {@code Command} execution</li>
 *     <li>{@link #getRawInput()}: Original Command for logging or niche command usage</li>
 * </ul>
 *
 * @see Command
 * @see CommandType
 */
public class CommandRequest {
    private final CommandType commandType;
    private final String rawInput;
    private final String[] tokens;

    /**
     * Constructs a new {@code CommandRequest} with the specified components
     *
     * @param commandType the commandType of the command based
     *                    on the enum
     * @param tokens the arguments of the command, equivalent to
     *             breaking the original input into tokens
     * @param rawArguments the arguments of the command stripped
     *                     of the command word
     * @param originalCommand the full original user input
     */
    public CommandRequest(CommandType commandType, String rawInput, String[] tokens) {
        this.commandType = commandType;
        this.rawInput = rawInput;
        this.tokens = tokens;
    }

    /**
     * Secondary constructor for a {@code commandRequest} with fewer required
     * components. Suitable for commands that do not have many details.
     *
     * <p>
     * Primarily used for the invalid command.
     * It reduced bloat in parameters.
     * </p>
     *
     * @param originalCommand the full original user input
     */
    public CommandRequest(String originalCommand) {
        this.commandType = CommandType.INVALID;
        this.rawInput = originalCommand;
        this.tokens = new String[0];
    }

    public CommandType getCommandType() {
        return this.commandType;
    }

    public String[] getTokens() {
        return this.tokens;
    }

    public String getRawInput() {
        return this.rawInput;
    }
}
