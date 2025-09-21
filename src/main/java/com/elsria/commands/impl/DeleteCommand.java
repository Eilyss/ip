package com.elsria.commands.impl;

import com.elsria.DialoguePath;
import com.elsria.commands.ResponseStatus;
import com.elsria.core.ApplicationContext;
import com.elsria.core.Storage;
import com.elsria.task.Task;
import com.elsria.task.TaskList;

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
public class DeleteCommand implements Command {
    private final TaskList taskList;
    private final Storage storage;
    private final int taskId;

    /**
     * Constructs a new DeleteCommand with the specified context and request.
     *
     * @param context the {@link ApplicationContext} providing access to shared state and services.
     * @param request the {@link ApplicationContext} containing the deletion arguments.
     *                Expected to contain a single numeric argument representing the task index.
     * @throws NullPointerException if either context or request is null
     */
    public DeleteCommand(Storage storage, TaskList taskList, int taskId) {
        this.taskList = taskList;
        this.storage = storage;
        this.taskId = taskId;
    }

    @Override
    public CommandResponse execute() {
        CommandResponse response;

        if (this.taskId > this.taskList.size()) {
            response = new CommandResponse(DialoguePath.TASK_ID_OOB, ResponseStatus.SUCCESS);
            response.attachResults(new String[]{Integer.toString(this.taskId)});
            return response;
        }

        Task task = this.taskList.get(this.taskId);
        taskList.remove(taskId);

        if (!storage.saveListToStorage(taskList)) {
            response = new CommandResponse(DialoguePath.DELETE_TASK_STORAGE_FAILURE, ResponseStatus.SUCCESS);
            response.attachResults(new String[] {task.toString()});
            return response;
        }

        response = new CommandResponse(DialoguePath.SUCCESSFULLY_DELETED_TASK, ResponseStatus.SUCCESS);
        response.attachResults(new String[] {task.toString()});
        return response;

    }
}
