package com.elsria.commands;

import com.elsria.core.ApplicationContext;

public abstract class Command {
    protected ApplicationContext context;
    protected CommandRequest arguments;

    public Command(ApplicationContext context, CommandRequest arguments) {
        this.context = context;
        this.arguments = arguments;
    }
    public abstract void execute();
}
