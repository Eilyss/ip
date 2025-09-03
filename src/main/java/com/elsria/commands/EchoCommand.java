package com.elsria.commands;

import com.elsria.core.UiHandler;
import com.elsria.core.ApplicationContext;

public class EchoCommand extends Command {
    private final String echo;
    private final UiHandler uiHandler;

    public EchoCommand(ApplicationContext context, CommandRequest request) {
        super(context, request);
        this.uiHandler = context.getUIHandler();
        this.echo = stripQuotes(request.getRawArgs());
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