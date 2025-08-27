package com.Elsria.Commands;

import com.Elsria.Command;
import com.Elsria.UIHandler;

public class InvalidCommand extends Command {
    UIHandler handler;

    public InvalidCommand(UIHandler handler) {
        this.handler = handler;
    }

    @Override
    public void execute() {
        this.handler.say("Sorry wut??");
    }

    @Override
    public void parseArgs(String[] args) {
    }
}
