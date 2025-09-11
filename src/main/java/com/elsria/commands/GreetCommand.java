package com.elsria.commands;

import com.elsria.core.ApplicationContext;

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
public class GreetCommand extends Command {
    private final String name;

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
    }

    @Override
    public String execute() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Heya! It's me, %s!\n", this.name));
        sb.append("What do you wanna do today?");

        return sb.toString();
    }
}
