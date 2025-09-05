package com.elsria.commands;

import com.elsria.core.ApplicationContext;

/**
 * Abstract class representing a general Command to the chatbot.
 * <p>
 * This class provides a basic structure for specific Commands
 * It defines common properties and behaviors, and provides
 * abstract methods that must be implemented by subclasses.
 * <p>
 * Subclasses should provide concrete implementations of
 * the abstract methods
 * such as {@link #execute()}.
 */

public abstract class Command {
    /**
     * Consists of class instances needed to run the
     * command
     */
    protected ApplicationContext context;

    /**
     * Consists of command type, command arguments and
     * raw arguments
     */
    protected CommandRequest request;


    /**
     * Default constructor for all Commands
     *
     * @param context The application context for the command
     * @param request The command data parsed from user input
     */
    public Command(ApplicationContext context, CommandRequest request) {
        this.context = context;
        this.request = request;
    }

    /**
     * Abstract method for executing the Command.
     * <p>
     * Subclasses must implement this method for command execution.
     */
    public abstract void execute();
}
