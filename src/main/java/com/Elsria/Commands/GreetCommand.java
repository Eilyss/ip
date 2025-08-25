package com.Elsria.Commands;

import com.Elsria.Command;
import com.Elsria.ChatbotContext;
import com.Elsria.UIHandler;

public class GreetCommand extends Command {
    private String name;

    public GreetCommand(ChatbotContext chatbotContext, String args) {
        super(chatbotContext, args);
        parseArgs(args);
    }

    @Override
    public void execute() {
        UIHandler uiHandler = super.chatbotContext.getUIHandler();
        uiHandler.queueMessage(String.format("Heya! It's me, %s!", this.name));
        uiHandler.queueMessage("What do you wanna do today?");
        uiHandler.sayMessages();
    }

    @Override
    public void parseArgs(String args) {
        this.name = args;
    }
}