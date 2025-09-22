package com.neokortex.commands.impl;

import com.neokortex.DialoguePath;
import com.neokortex.commands.ResponseStatus;
import com.neokortex.core.ApplicationContext;

/**
 * Bids farewell the user
 * <p>
 * The {@code FarewellCommand} bids farewell to the user
 * </p>
 *
 * @see Command
 * @see ApplicationContext
 */
public class FarewellCommand implements Command {
    /**
     * Constructs a new {@code FarewellCommand} using the specified context
     *
     * @param context the {@link ApplicationContext} providing access to shared state and services.
     */
    public FarewellCommand(ApplicationContext context) {
        context.stopProgram();
    }

    @Override
    public CommandResponse execute() {
        return new CommandResponse(DialoguePath.FAREWELL, ResponseStatus.EXIT_PROGRAM);
    }
}
