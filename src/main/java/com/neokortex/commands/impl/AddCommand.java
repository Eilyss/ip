package com.neokortex.commands.impl;

import com.neokortex.DialoguePath;
import com.neokortex.commands.ResponseStatus;
import com.neokortex.core.Storage;
import com.neokortex.task.Task;
import com.neokortex.task.TaskList;

/**
 * TODO: Update Documentation
 * Abstract class representing a general Command that adds specific tasks to a list,
 * which extends from Command
 * <p>
 * This class provides a basic structure for commands that interact
 * with the TaskList
 */
public class AddCommand implements Command {
    /**
     * Error message to run in case of failure
     */
    protected String errorMessage;
    private final TaskList taskList;
    private final Storage storage;
    private Task task;


    /**
     * Default constructor for all AddToListCommands
     * It uses the exact same parameters as the default constructor for Command,
     * but it provides default implementation for extracting necessary items from
     * context and request.
     *
     * @param context The application context for the command
     * @param request The command data parsed from user input
     */
    public AddCommand(Storage storage, TaskList taskList, Task task) {
        this.storage = storage;
        this.taskList = taskList;
        this.task = task;
    }


    @Override
    public CommandResponse execute() {
        CommandResponse response;

        this.taskList.add(this.task);

        if (!storage.saveListToStorage(taskList)) {
            response = new CommandResponse(DialoguePath.ADD_TASK_STORAGE_FAILURE, ResponseStatus.SUCCESS);
            response.attachResults(new String[] {this.task.toString()});
            return response;
        }

        response = new CommandResponse(DialoguePath.SUCCESSFULLY_ADDED_TASK, ResponseStatus.SUCCESS);
        response.attachResults(new String[] {this.task.toString()});
        return response;

    }
}
