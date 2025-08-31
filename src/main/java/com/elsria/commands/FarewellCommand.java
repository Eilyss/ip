package com.elsria.commands;

import com.elsria.UiHandler;
import com.elsria.core.ApplicationContext;

public class FarewellCommand extends Command {
    UiHandler uiHandler;

    public FarewellCommand(ApplicationContext context, CommandRequest request) {
        super(context, request);
        this.uiHandler = context.getUIHandler();
    }

    @Override
    public void execute() {
        this.uiHandler.say("Okey dokey, see you soon!");
    }
}
