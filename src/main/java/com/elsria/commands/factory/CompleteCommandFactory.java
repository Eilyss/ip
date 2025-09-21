package com.elsria.commands.factory;

import com.elsria.commands.CommandType;
import com.elsria.commands.impl.CommandRequest;
import com.elsria.core.ApplicationContext;

public class CompleteCommandFactory {
    private ApplicationContext context;
    /**
     * Constructs a new CommandParser with initial state.
     */
    public CompleteCommandFactory(ApplicationContext context) {
        this.context = context;
    }

    /**
     * Returns the CommandRequest that was obtained from
     * the raw user input
     *
     * @param rawInput raw user input.
     * @return a {@link CommandRequest} object containing details regarding
     *         the specific command
     */
    public FactoryResponse handle(CommandRequest request) {
        CommandType commandType = request.getCommandType();
        return commandType.getFactory().create(context, request);
    }
}
