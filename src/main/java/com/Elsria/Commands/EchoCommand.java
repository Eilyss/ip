package com.Elsria.Commands;

import com.Elsria.Command;
import com.Elsria.UIHandler;

public class EchoCommand extends Command {
    private String echo;
    private UIHandler uiHandler;

    public EchoCommand(UIHandler uiHandler, String input) {
        this.uiHandler = uiHandler;
        this.echo = stripQuotes(input);
    }

    private static String stripQuotes(String input) {
        if (input.length() >= 2
                && (input.charAt(0) == '\'' && input.charAt(input.length() - 1) == '\''
                || input.charAt(0) == '\"' && input.charAt(input.length() - 1) == '\"')) {
            return input.substring(1, input.length() - 1);
        }
        return input;
    }

    @Override
    public void execute() {
        this.uiHandler.say(this.echo);
    }
}