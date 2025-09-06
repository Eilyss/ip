package com.elsria.commands;

import com.elsria.core.ApplicationContext;


/**
 * Provides a functional interface for creating a new Command
 * <p>
 * The {@code CommandCreater} interface is specifically for creating a new {@code Command}
 * from an {@code ApplicationContext} and {@code CommandRequest}.
 * </p>
 *
 * Currently exclusively used by {@code CommandParser} to create new {@code Commands}
 *
 * <p><b>Functional Interface:</b></p>
 * This interface is annotated with {@link FunctionalInterface}, meaning it can
 * be used with lambda expressions, method references, or anonymous class implementations.
 *
 * @see Command
 * @see ApplicationContext
 * @see CommandRequest
 * @see java.util.function.Function
 * @see FunctionalInterface
 */
@FunctionalInterface
public interface CommandCreator {
    Command create(ApplicationContext context, CommandRequest request);
}
