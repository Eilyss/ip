package com.Elsria;

import java.util.ArrayList;

public class UiHandler {
    private static final String lineSeparator = "____________________________________________________________";
    private final ArrayList<String> messages = new ArrayList<>();

    public UiHandler() {
    }

    public void queueMessage(String message) {
        this.messages.add(message);
    }

    public void resetMessages() {
        this.messages.clear();
    }

    public void sayMessages() {
        System.out.println("\t" + lineSeparator);
        for (String s : this.messages) {
            System.out.println("\t" + s);
        }
        System.out.println("\t" + lineSeparator);
        this.resetMessages();
    }

    public void say(String message) {
        System.out.println("\t" + lineSeparator);
        System.out.println("\t" + message);
        System.out.println("\t" + lineSeparator);
    }

}
