package com.elsria.commands.impl;

import com.elsria.core.ApplicationContext;

/**
 * TODO: Add support for undo/redo functionality (if implemented in subclasses),
 *       Add command logging, history, and macro capabilities</li>
 * Abstract base class representing an executable command.
 * <p>
 * The {@code Command} class encapsulates the possible interactions a user
 * can have with the Chatbot. This includes mutating the state of the program
 * and altering data in storage. Each concrete command subclass implements
 * specific actions while sharing common execution infrastructure.
 * </p>
 *
 * <p><b>Key Features:</b></p>
 * <ul>
 *   <li>Encapsulates execution logic based on the state of the program</li>
 *   <li>Provides separation between command creation and execution</li>
 * </ul>
 *
 * <p><b>Component Responsibilities:</b></p>
 * <ul>
 *   <li>{@link ApplicationContext}: Provides access to application state and services</li>
 *   <li>{@link CommandRequest}: Contains command-specific parameters and data</li>
 *   <li>{@link Command}: Encapsulates the execution logic</li>
 * </ul>
 *
 * @see ApplicationContext
 * @see CommandRequest
 */
public interface Command {
    /**
     * Executes the command.
     * <p>
     * Concrete subclasses must implement this method to perform the intended
     * action. The implementation should use the provided
     * {@link ApplicationContext} for accessing application state and the
     * {@link CommandRequest} for command-specific parameters.
     * </p>
     *
     * <p><b>Implementation Notes:</b></p>
     * <ul>
     *   <li>Should update application state through the context</li>
     *   <li>Should provide suitable responses to users through the UI handler</li>
     * </ul>
     */
    CommandResponse execute();
}
