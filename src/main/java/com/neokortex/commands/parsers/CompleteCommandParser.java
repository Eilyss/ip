package com.neokortex.commands.parsers;

import com.neokortex.DialoguePath;
import com.neokortex.commands.CommandType;
import com.neokortex.commands.ResponseStatus;
import com.neokortex.commands.impl.Command;
import com.neokortex.commands.impl.CommandRequest;

/**
 * Parses raw user input into structured command requests.
 * <p>
 * The {@code CommandParser} processes raw input from users and converts it
 * into a structured {@link CommandRequest} that can be used for creating
 * and executing commands. It handles command identification, argument
 * separation, and indicates when it receives a program terminating command.
 * </p>
 *
 * <p><b>Responsibilities:</b></p>
 * <ul>
 *   <li>Tokenizes raw input into command and arguments</li>
 *   <li>Identifies the {@link CommandType} from input tokens</li>
 *   <li>Indicates when it encounters a {@link Command} that should trigger program termination</li>
 *   <li>Handles empty and invalid input</li>
 *   <li>Constructs comprehensive {@link CommandRequest} objects</li>
 * </ul>
 *
 * <p><b>Input Parsing Rules:</b></p>
 * <ul>
 *   <li>First word is treated as the command keyword</li>
 *   <li>Remaining text is treated as arguments</li>
 *   <li>Command matching is not case-sensitive</li>
 *   <li>Empty input returns {@link CommandType#INVALID}</li>
 * </ul>
 *
 * Credit: Written with guidance from generative AI
 *
 * @see CommandRequest
 * @see CommandType
 * @see CommandType#getCommandType(String)
 */
public class CompleteCommandParser {
    /**
     * Constructs a new CommandParser with initial state.
     */
    public CompleteCommandParser() {

    }

    public boolean canParse(String input) {
        return CommandType.getCommandType(input.split(" ", 2)[0]) != CommandType.INVALID;
    }

    /**
     * Returns the CommandRequest that was obtained from
     * the raw user input
     *
     * @param rawInput raw user input.
     * @return a {@link CommandRequest} object containing details regarding
     *         the specific command
     */
    public ParserResponse parse(String rawInput) {
        if (rawInput == null || rawInput.trim().isEmpty()) {
            return new ParserResponse(DialoguePath.GENERIC_FAILURE, ResponseStatus.TOTAL_FAILURE);
        }

        String[] parts = rawInput.split("\\s+", 2);
        CommandType commandType = CommandType.getCommandType(parts[0]);
        return commandType.getParser().parse(commandType, rawInput);
    }
}
