package com.neokortex.core;

import com.neokortex.task.TaskList;

/**
 * TODO: Make the Chatbot part of context
 * Represents a container class that holds the core application state and components.
 * <p>
 * the {@code ApplicationContext} serves as a central repository for all fundamental
 * application components, providing a single access point for shared state and services.
 * </p>
 *
 * <p><b>Purpose:</b></p>
 * <ul>
 *     <li>Encapsulates the application's state during runtime</li>
 *     <li>Centralizes access to core application components</li>
 *     <li>Provides access to application state</li>
 * </ul>
 *
 * <p><b>Components:</b></p>
 * <ul>
 *     <li>{@link #getName()}: Application identifier for personalization</li>
 *     <li>{@link #getTaskList()}: Central task storage and management</li>
 *     <li>{@link #getStorage()}: Persistent data storage</li>
 * </ul>
 *
 * Credit: JavaDoc was written with guidance from generative AI
 *
 * @see UiHandler
 * @see TaskList
 * @see Storage
 */
public class ApplicationContext {
    private final String name;
    private final TaskList taskList;
    private final Storage storage;
    private boolean shouldKeepRunning = true;

    /**
     * Constructs a new ApplicationContext with the specified components.
     *
     * @param name the application name identifier.
     * @param taskList the task list containing application tasks.
     * @param storage the storage component for persistence.
     * @throws NullPointerException if any parameter is null
     */
    public ApplicationContext(String name, TaskList taskList, Storage storage) {
        this.name = name;
        this.taskList = taskList;
        this.storage = storage;
    }

    public String getName() {
        return this.name;
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    public Storage getStorage() {
        return this.storage;
    }

    public boolean shouldKeepRunning() {
        return this.shouldKeepRunning;
    }

    public void stopProgram() {
        this.shouldKeepRunning = false;
    }
}
