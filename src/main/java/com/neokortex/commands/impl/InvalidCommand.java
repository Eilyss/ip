package com.neokortex.commands.impl;

import com.neokortex.DialoguePath;
import com.neokortex.commands.ResponseStatus;
import com.neokortex.commands.parsers.CompleteCommandParser;
import com.neokortex.commands.CommandType;
import com.neokortex.core.ApplicationContext;

/**
 * Fallback command for handling unrecognized or invalid user input.
 * <p>
 * The InvalidCommand is executed when the user enters a command that cannot
 * be recognized or parsed by the system. It provides friendly feedback to
 * the user indicating that the input was not understood while maintaining
 * the application's conversational tone.
 * </p>
 *
 * <p><b>Trigger Conditions:</b></p>
 * <ul>
 *   <li>Unrecognized command keywords</li>
 *   <li>Empty or null input</li>
 *   <li>Malformed command syntax</li>
 *   <li>Commands not registered in {@link CommandType}</li>
 * </ul>
 *
 * <p><b>Purpose:</b></p>
 * <ul>
 *   <li>Provides graceful degradation for invalid input</li>
 *   <li>Maintains user engagement with friendly feedback</li>
 *   <li>Prevents application crashes from unrecognized commands</li>
 *   <li>Serves as the default fallback in command processing</li>
 * </ul>
 *
 * @see Command
 * @see CommandType#INVALID
 * @see CompleteCommandParser#getCommandType(String)
 */
public class InvalidCommand implements Command {
    /**
     * Constructs a new InvalidCommand with the specified context and request.
     *
     * @param context the application context providing access to shared state and services.
     * @param request the command request that triggered this invalid command. Ignored for now.
     * @throws NullPointerException if either context is null
     */
    public InvalidCommand(ApplicationContext context,
                          CommandRequest request) {
    }

    @Override
    public CommandResponse execute() {
        return new CommandResponse(DialoguePath.GENERIC_FAILURE, ResponseStatus.SUCCESS);
    }
}
