package com.elsria.commands;

import com.elsria.core.ApplicationContext;

/**
 * Echoes the user's input text back.
 * <p>
 * The {@code EchoCommand} takes user input and displays it back to the user.
 * Single and double quotes are automatically stripped.
 * </p>
 *
 * <p><b>Command Format:</b></p>
 * <pre>
 * echo [text]
 * </pre>
 *
 * @see Command
 * @see ApplicationContext
 * @see CommandRequest
 */
public class EchoCommand extends Command {
    private final String echo;

    /**
     * Constructs a new EchoCommand with the specified context and request.
     *
     * @param context the {@link ApplicationContext} providing access to shared state and services.
     * @param request the {@link CommandRequest} containing the text to echo, stored in rawArgs.
     * @throws NullPointerException if either context or request is null
     */
    public EchoCommand(ApplicationContext context, CommandRequest request) {
        this.echo = stripQuotes(request.getRawArgs());
    }

    private static String stripQuotes(String input) {
        if (input.length() >= 2
                && (input.charAt(0) == '\'' && input.charAt(input.length() - 1) == '\''
                || input.charAt(0) == '\"' && input.charAt(input.length() - 1) == '\"')) {
            return input.substring(1, input.length() - 1);
        }
        return input;
    }

    @Override
    public String execute() {
        return this.echo;
    }
}
