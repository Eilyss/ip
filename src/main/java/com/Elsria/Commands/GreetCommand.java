package com.Elsria.Commands;

import com.Elsria.Command;
import com.Elsria.UIHandler;

public class GreetCommand extends Command {
    private String name;
    private UIHandler uiHandler;

    public GreetCommand(UIHandler uiHandler, String name) {
        this.name = name;
        this.uiHandler = uiHandler;
    }

    @Override
    public void execute() {
        this.uiHandler.queueMessage(String.format("Heya! It's me, %s!", this.name));
        this.uiHandler.queueMessage("What do you wanna do today?");
        this.uiHandler.sayMessages();
    }
}