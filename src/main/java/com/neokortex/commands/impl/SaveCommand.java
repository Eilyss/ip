package com.neokortex.commands.impl;

import com.neokortex.DialoguePath;
import com.neokortex.commands.ResponseStatus;
import com.neokortex.core.ApplicationContext;
import com.neokortex.core.Storage;
import com.neokortex.task.TaskList;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

/**
 * Deletes a task from the task list based on their index.
 * <p>
 * The {@code DeleteCommand} removes a specific task from the task list based on its
 * position on the list. It prompts the user to try again if the index provided was
 * out-of-bounds. It saves the list to storage after the deletion.
 * </p>
 *
 * <p><b>Command Format:</b></p>
 * <pre>
 * delete [taskNumber]
 * </pre>
 *
 * <p><b>Execution Flow:</b></p>
 * <ol>
 *   <li>Validates argument count and format</li>
 *   <li>Parses and validates the task index</li>
 *   <li>Confirms the task exists in the list</li>
 *   <li>Removes the task from the task list</li>
 *   <li>Persists changes to storage</li>
 *   <li>Provides comprehensive user feedback</li>
 * </ol>
 *
 * <p><b>Error Handling:</b></p>
 * <ul>
 *   <li>Missing arguments: Prompts user for task number</li>
 *   <li>Too many arguments: Informs user of argument overload</li>
 *   <li>Invalid number format: Requests numeric input</li>
 *   <li>Invalid task ID: Notifies user of non-existent task</li>
 *   <li>Storage failures: Alerts user about save issues</li>
 * </ul>
 *
 * Credit: Written with guidance from generative AI
 *
 * @see Command
 * @see ApplicationContext
 * @see CommandRequest
 * @see TaskList#remove(int)
 * @see TaskList#checkValidID(int)
 * @see Storage#saveListToStorage(TaskList)
 */
public class SaveCommand implements Command {
    private final TaskList taskList;
    private final Storage storage;
    private final String path;

    /**
     * Constructs a new DeleteCommand with the specified context and request.
     *
     * @param context the {@link ApplicationContext} providing access to shared state and services.
     * @param request the {@link ApplicationContext} containing the deletion arguments.
     *                Expected to contain a single numeric argument representing the task index.
     * @throws NullPointerException if either context or request is null
     */
    public SaveCommand(Storage storage, TaskList taskList, String path) {
        this.taskList = taskList;
        this.storage = storage;
        this.path = path;
    }

    @Override
    public CommandResponse execute() {
        if (!this.path.equals("")) {
            try {
                storage.setFilePath(Path.of(this.path));
            } catch (InvalidPathException e) {
                return new CommandResponse(DialoguePath.INVALID_PATH, ResponseStatus.TOTAL_FAILURE);
            }
        }
        CommandResponse response;

        try {
            this.storage.saveListToStorage(this.taskList);
        } catch (IOException e) {
            return new CommandResponse(DialoguePath.UNABLE_TO_SAVE_TO_STORAGE, ResponseStatus.SUCCESS);
        }

        return new CommandResponse(DialoguePath.SUCCESSFULLY_SAVED_TASK, ResponseStatus.SUCCESS);
    }
}
