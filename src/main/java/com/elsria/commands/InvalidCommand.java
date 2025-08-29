package com.Elsria.Commands;

import com.Elsria.Command;
import com.Elsria.UiHandler;

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
