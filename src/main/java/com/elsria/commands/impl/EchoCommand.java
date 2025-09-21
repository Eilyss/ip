package com.elsria.commands.impl;

import com.elsria.DialoguePath;
import com.elsria.commands.ResponseStatus;
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
public class EchoCommand implements Command {
    private final String echo;

    /**
     * Constructs a new EchoCommand with the text to echo.
     *
     * @param echo the text to echo
     * @throws NullPointerException if either context or request is null
     */
    public EchoCommand(String echo) {
        this.echo = echo;
    }


    @Override
    public CommandResponse execute() {
        CommandResponse response = new CommandResponse(DialoguePath.ECHO, ResponseStatus.SUCCESS);
        response.attachResults(new String[]{this.echo});
        return response;
    }
}
