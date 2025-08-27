package com.Elsria.Commands;

import com.Elsria.Command;
import com.Elsria.UIHandler;

public class FarewellCommand extends Command {
    UIHandler handler;

    public FarewellCommand(UIHandler handler) {
        this.handler = handler;
    }

    @Override
    public void execute() {
        this.handler.say("Okey dokey, see you soon!");
    }

    @Override
    public void parseArgs(String[] args) {
    }
}
