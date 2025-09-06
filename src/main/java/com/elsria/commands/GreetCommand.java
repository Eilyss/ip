package com.elsria.commands;

import com.elsria.core.ApplicationContext;
import com.elsria.core.UiHandler;

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
 * <p><b>Requirements:</b></p>
 * <ul>
 *     <li>{@link UiHandler}</li>
 * </ul>
 *
 * @see Command
 * @see ApplicationContext
 * @see UiHandler
 */
public class GreetCommand extends Command {
    private final String name;
    private final UiHandler uiHandler;

    /**
     * Constructs a new {@code GreetCommand} with the specified context and request.
     *
     * @param context the {@link ApplicationContext} providing access to shared state and services.
     * @param request the {@link CommandRequest}
     * @throws NullPointerException if either context or request is null
     */
    public GreetCommand(ApplicationContext context, CommandRequest request) {
        super(context, request);
        this.name = context.getName();
        this.uiHandler = context.getUiHandler();
    }

    @Override
    public void execute() {
        this.uiHandler.queueMessage(String.format("Heya! It's me, %s!", this.name));
        this.uiHandler.queueMessage("What do you wanna do today?");
        this.uiHandler.sayMessages();
    }
}
