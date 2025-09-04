package com.elsria.commands;

import com.elsria.core.ApplicationContext;
import com.elsria.core.UiHandler;

public class FarewellCommand extends Command {
    private final UiHandler uiHandler;

    public FarewellCommand(ApplicationContext context, CommandRequest request) {
        super(context, request);
        this.uiHandler = context.getUiHandler();
    }

    @Override
    public void execute() {
        this.uiHandler.say("Okey dokey, see you soon!");
    }
}
