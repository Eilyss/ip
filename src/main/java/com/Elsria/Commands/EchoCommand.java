package com.Elsria.Commands;

import com.Elsria.Command;
import com.Elsria.UIHandler;

import java.util.Arrays;

public class EchoCommand extends Command {
    private String echo;
    private UIHandler handler;

    public EchoCommand(UIHandler handler, String input) {
        this.handler = handler;
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
        this.handler.say(this.echo);
    }
}