package com.Elsria.Commands;

import com.Elsria.Command;
import com.Elsria.UIHandler;

public class InvalidCommand extends Command {
    UIHandler uiHandler;

    public InvalidCommand(UIHandler uiHandler) {
        this.uiHandler = uiHandler;
    }

    @Override
    public void execute() {
        this.uiHandler.say("Sorry wut??");
    }
}
