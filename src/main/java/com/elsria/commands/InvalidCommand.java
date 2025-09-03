package com.elsria.commands;

import com.elsria.core.UiHandler;
import com.elsria.core.ApplicationContext;

public class InvalidCommand extends Command {
    UiHandler uiHandler;

    public InvalidCommand(ApplicationContext context, CommandRequest request) {
        super(context, request);
        this.uiHandler = context.getUIHandler();
    }

    @Override
    public void execute() {
        this.uiHandler.say("Sorry wut??");
    }
}
