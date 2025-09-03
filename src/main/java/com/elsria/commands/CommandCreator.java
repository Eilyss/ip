package com.elsria.commands;

import com.elsria.core.ApplicationContext;

/**
 * A functional interface for creating a new Command
 * <p>
 * This interface is specifically for creating a new Command from an ApplicationContext and
 * CommandRequest. Used primarily in the CommandParser to facilitate Command Creation
 *
 * The interface contains one abstract method
 * {@code create(ApplicationContext context, CommandRequest request)},
 * which implements the operation specified.
 */

@FunctionalInterface
public interface CommandCreator {
    Command create(ApplicationContext context, CommandRequest request);
}
