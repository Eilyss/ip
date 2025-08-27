package com.Elsria.Commands;

import com.Elsria.Command;
import com.Elsria.UIHandler;

import java.util.Arrays;

public class GreetCommand extends Command {
    private String name;
    private UIHandler handler;

    public GreetCommand(UIHandler handler, String name) {
        this.name = name;
        this.handler = handler;
    }

    @Override
    public void execute() {
        this.handler.queueMessage(String.format("Heya! It's me, %s!", this.name));
        this.handler.queueMessage("What do you wanna do today?");
        this.handler.sayMessages();
    }

    @Override
    public void parseArgs(String[] args) {
    }
}