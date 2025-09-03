package com.elsria;

import com.elsria.core.UiHandler;

public class Chatbot {
    private final String name;

    public Chatbot(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
    public void startupMessage(UiHandler uiHandler, boolean hasloadError) {
        uiHandler.queueMessage(String.format("Heya! It's me, %s!", name));
        if (hasloadError) {
            uiHandler.queueMessage("Hmm... there seems to be an error with loading your data.");
            uiHandler.queueMessage("Could you check it out please?");
            uiHandler.queueMessage("In any case, welcome!");
            uiHandler.sayMessages();
        }
        uiHandler.queueMessage("What do you wanna do today?");
        uiHandler.sayMessages();
    }

}
