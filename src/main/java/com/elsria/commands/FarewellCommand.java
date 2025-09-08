package com.elsria.commands;

import com.elsria.core.ApplicationContext;
import com.elsria.core.UiHandler;

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
 * <p><b>Requirements:</b></p>
 * <ul>
 *     <li>{@link UiHandler}</li>
 * </ul>
 *
 * @see Command
 * @see ApplicationContext
 * @see UiHandler
 */
public class FarewellCommand extends Command {
    private final UiHandler uiHandler;

    /**
     * Constructs a new {@code FarewellCommand} with the specified context and request.
     *
     * @param context the {@link ApplicationContext} providing access to shared state and services.
     * @param request the {@link CommandRequest}
     * @throws NullPointerException if either context or request is null
     */
    public FarewellCommand(ApplicationContext context, CommandRequest request) {
        super(context, request);
        this.uiHandler = context.getUiHandler();
        context.stopProgram();
    }

    @Override
    public void execute() {
        this.uiHandler.say("Okey dokey, see you soon!");
    }
}
