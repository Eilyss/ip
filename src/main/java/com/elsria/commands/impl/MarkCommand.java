package com.elsria.commands.impl;

import com.elsria.DialoguePath;
import com.elsria.commands.ResponseStatus;
import com.elsria.core.Storage;
import com.elsria.task.Task;
import com.elsria.task.TaskList;

/**
 * Marks tasks as completed in the task list.
 * <p>
 * The {@code MarkCommand} allows users to mark specific tasks as done by their
 * index (which can be checked by calling the {@link ListCommand}). It validates
 * the task ID, updates the task status, and persists the updated list in storage.
 * </p>
 *
 * <p><b>Command Format:</b></p>
 * <pre>
 * mark [taskNumber]
 * </pre>
 *
 * <p><b>Execution Flow:</b></p>
 * <ol>
 *     <li>Validates argument count and format</li>
 *     <li>Parses and validates the task index</li>
 *     <li>Confirms the task exists in the list</li>
 *     <li>Marks the task as completed</li>
 *     <li>Persists changes to storage</li>
 *     <li>Respond to the user</li>
 * </ol>
 *
 * <p><b>Error Handling:</b></p>
 * <ul>
 *   <li>Missing arguments: Prompts user for task number</li>
 *   <li>Too many arguments: Informs user of argument overload</li>
 *   <li>Invalid number format: Requests numeric input</li>
 *   <li>Invalid task ID: Notifies user of non-existent task</li>
 * </ul>
 *
 * <p><b>Visual Change:</b></p>
 * <pre>
 * Before: [T][ ] Buy groceries
 * After:  [T][X] Buy groceries
 * </pre>
 *
 * @see Command
 * @see TaskList#markTask(int)
 * @see TaskList#checkValidID(int)
 */
public class MarkCommand implements Command {
    private final TaskList taskList;
    private final Storage storage;
    private final int taskId;

    /**
     * Constructs a new MarkCommand with the specified context and request.
     *
     * @param context the application context providing access to shared state and services.
     * @param request the command request containing the mark arguments.
     *                Expected to contain a single numeric argument representing the task index.
     * @throws NullPointerException if either context or request is null
     */
    public MarkCommand(Storage storage, TaskList taskList, int taskId) {
        this.storage = storage;
        this.taskList = taskList;
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
        if (task.isMarked()) {
            response = new CommandResponse(DialoguePath.TASK_ALREADY_MARKED, ResponseStatus.SUCCESS);
            response.attachResults(new String[]{Integer.toString(this.taskId)});
            return response;
        }
        taskList.markTask(taskId);

        if (!storage.saveListToStorage(taskList)) {
            response = new CommandResponse(DialoguePath.MARK_TASK_STORAGE_FAILURE, ResponseStatus.SUCCESS);
            response.attachResults(new String[] {task.toString()});
            return response;
        }

        response = new CommandResponse(DialoguePath.SUCCESSFULLY_MARKED_TASK, ResponseStatus.SUCCESS);
        response.attachResults(new String[] {task.toString()});
        return response;
    }
}
