package com.elsria.commands;

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
public class CommandParser {

    /**
     * Constructs a new CommandParser with initial state.
     */
    public CommandParser() {

    }

    /**
     * Returns the CommandRequest that was obtained from
     * the raw user input
     *
     * @param rawInput raw user input.
     * @return a {@link CommandRequest} object containing details regarding
     *         the specific command
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

        return new CommandRequest(commandType, arguments, rawArgument, rawInput);
    }
}
