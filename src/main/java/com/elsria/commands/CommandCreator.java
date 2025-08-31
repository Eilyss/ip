package com.elsria.commands;

import com.elsria.core.ApplicationContext;

@FunctionalInterface
public interface CommandCreator {
    Command create(ApplicationContext context, CommandRequest request);
}
