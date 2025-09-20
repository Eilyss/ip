package com.elsria.commands.impl;

import com.elsria.commands.Command;
import com.elsria.commands.CommandRequest;
import com.elsria.core.ApplicationContext;

/**
 * Bids farewell the user
 * <p>
 * The {@code FarewellCommand} bids farewell to the user
 * </p>
 *
 * <p><b>Command Format:</b></p>
 * <pre>
 * [exit/bye]
 * </pre>
 *
 * @see Command
 * @see ApplicationContext
 */
public class FarewellCommand extends Command {
    /**
     * Constructs a new {@code FarewellCommand} with the specified context and request.
     *
     * @param context the {@link ApplicationContext} providing access to shared state and services.
     * @param request the {@link CommandRequest}
     * @throws NullPointerException if either context or request is null
     */
    public FarewellCommand(ApplicationContext context, CommandRequest request) {
        context.stopProgram();
    }

    @Override
    public String execute() {
        return "Okey dokey, see you soon!";
    }
}
