package com.elsria.commands;

import com.elsria.UiHandler;

public class InvalidCommand extends Command {
    UiHandler uiHandler;

    public InvalidCommand(UiHandler uiHandler) {
        this.uiHandler = uiHandler;
    }

    @Override
    public void execute() {
        this.uiHandler.say("Sorry wut??");
    }
}
