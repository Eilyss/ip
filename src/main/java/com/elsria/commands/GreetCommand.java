package com.elsria.commands;

import com.elsria.core.ApplicationContext;
import com.elsria.core.UiHandler;

public class GreetCommand extends Command {
    private final String name;
    private final UiHandler uiHandler;

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
