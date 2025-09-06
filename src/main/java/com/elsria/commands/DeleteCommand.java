package com.elsria.commands;

import com.elsria.core.ApplicationContext;
import com.elsria.core.Storage;
import com.elsria.core.UiHandler;
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
public class DeleteCommand extends Command {
    private final UiHandler uiHandler;
    private final TaskList taskList;
    private final Storage storage;
    private final String[] arguments;

    /**
     * Constructs a new DeleteCommand with the specified context and request.
     *
     * @param context the {@link ApplicationContext} providing access to shared state and services.
     * @param request the {@link ApplicationContext} containing the deletion arguments.
     *                Expected to contain a single numeric argument representing the task index.
     * @throws NullPointerException if either context or request is null
     */
    public DeleteCommand(ApplicationContext context, CommandRequest request) {
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


        this.uiHandler.queueMessage(String.format("Okay! I got rid of task %d", taskID));
        this.uiHandler.queueMessage(taskList.getTaskDescription(taskID));
        taskList.remove(taskID);
        this.uiHandler.queueMessage(String.format("Now you've got %d tasks in the list!", taskID));
        if (!this.storage.saveListToStorage(this.taskList)) {
            this.uiHandler.queueMessage("Woah, hold on...");
            this.uiHandler.queueMessage("I seem to be unable to save your changes.");
            this.uiHandler.queueMessage("Could you run the Save command?");
        }
        this.uiHandler.sayMessages();
    }
}
