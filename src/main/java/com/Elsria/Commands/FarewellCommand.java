package com.Elsria.Commands;

import com.Elsria.Command;
import com.Elsria.UiHandler;

public class FarewellCommand extends Command {
    UiHandler handler;

    public FarewellCommand(UiHandler handler) {
        this.handler = handler;
    }

    @Override
    public void execute() {
        this.handler.say("Okey dokey, see you soon!");
    }
}
