package com.elsria.commands;

import com.elsria.UiHandler;

public class GreetCommand extends Command {
    private String name;
    private UiHandler uiHandler;

    public GreetCommand(UiHandler uiHandler, String name) {
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