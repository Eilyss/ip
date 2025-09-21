package com.neokortex.commands.impl;

import com.neokortex.DialoguePath;
import com.neokortex.commands.ResponseStatus;
import com.neokortex.core.ApplicationContext;

/**
 * Greets the user
 * <p>
 * The {@code GreetCommand} greets the user.
 * </p>
 *
 * <p><b>Command Format:</b></p>
 * <pre>
 * [hello/hi/greet]
 * </pre>
 *
 * @see Command
 * @see ApplicationContext
 */
public class GreetCommand implements Command {
    /**
     * Constructs a new {@code GreetCommand} with the specified context and request.
     *
     * @param context the {@link ApplicationContext} providing access to shared state and services.
     * @param request the {@link CommandRequest}
     * @throws NullPointerException if either context or request is null
     */
    public GreetCommand() {
    }

    @Override
    public CommandResponse execute() {
        return new CommandResponse(DialoguePath.GREET, ResponseStatus.SUCCESS);
    }
}
