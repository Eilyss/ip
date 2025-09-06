package com.elsria.commands;

import com.elsria.core.ApplicationContext;
import com.elsria.core.Storage;
import com.elsria.core.UiHandler;
import com.elsria.task.TaskList;

/**
 * Unmarks tasks in the task list.
 * <p>
 * The {@code UnmarkCommand} allows users to unmark specific tasks as done by their
 * index (which can be checked by calling the {@link ListCommand}). It validates
 * the task ID, updates the task status, and persists the updated list in storage.
 * It acts as the inverse to {@link MarkCommand}.
 * </p>
 *
 * <p><b>Command Format:</b></p>
 * <pre>
 * unmark [taskNumber]
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
 * Before: [T][X] Buy groceries
 * After:  [T][ ] Buy groceries
 * </pre>
 *
 * @see Command
 * @see TaskList#markTask(int)
 * @see TaskList#checkValidID(int)
 * @see CommandUtils#saveListToStorage(Storage, TaskList, UiHandler)
 */
public class UnmarkCommand extends Command {
    private final UiHandler uiHandler;
    private final TaskList taskList;
    private final Storage storage;
    private final String[] arguments;

    /**
     * Constructs a new MarkCommand with the specified context and request.
     *
     * @param context the application context providing access to shared state and services.
     * @param request the command request containing the mark arguments.
     *                Expected to contain a single numeric argument representing the task index.
     * @throws NullPointerException if either context or request is null
     */
    public UnmarkCommand(ApplicationContext context, CommandRequest request) {
        super(context, request);
        this.uiHandler = context.getUiHandler();
        this.taskList = context.getTaskList();
        this.storage = context.getStorage();
        this.arguments = request.getArgs();
    }

    @Override
    public void execute() {
        if (this.arguments.length == 0) {
            this.uiHandler.say("Wait which one?");
            return;
        }

        if (this.arguments.length > 1) {
            this.uiHandler.say("Woah woah woah, that's too many arguments! >:(");
            return;
        }

        int taskID;

        try {
            taskID = Integer.parseInt(this.arguments[0]) - 1;
        } catch (NumberFormatException e) {
            this.uiHandler.say("Hey, I need a number!");
            return;
        }

        if (!this.taskList.checkValidID(taskID)) {
            this.uiHandler.say("Woah buddy that task does not exist!");
            return;
        }

        taskList.unmarkTask(taskID);
        this.uiHandler.queueMessage("Okay! That task is no longer marked as done");
        this.uiHandler.queueMessage(taskList.getTaskDescription(taskID));

        CommandUtils.saveListToStorage(this.storage, this.taskList, this.uiHandler);


        this.uiHandler.sayMessages();
    }
}
