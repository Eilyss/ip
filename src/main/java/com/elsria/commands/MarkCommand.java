package com.elsria.commands;

import com.elsria.core.ApplicationContext;
import com.elsria.core.Storage;
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
public class MarkCommand extends Command {
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
    public MarkCommand(ApplicationContext context, CommandRequest request) {
        super(context, request);
        this.taskList = context.getTaskList();
        this.storage = context.getStorage();
        this.arguments = request.getArgs();
    }

    @Override
    public String execute() {
        if (this.arguments.length == 0) {
            return "Wait which one?";
        }

        if (this.arguments.length > 1) {
            return "Woah woah woah, that's too many arguments! >:(";
        }

        int taskID;

        try {
            taskID = Integer.parseInt(this.arguments[0]) - 1;
        } catch (NumberFormatException e) {
            return "Hey, I need a number!";
        }

        if (!this.taskList.checkValidID(taskID)) {
            return "Woah buddy that task does not exist!";
        }

        if (taskList.get(taskID).isMarked()) {
            return "This task is already marked as done!";
        }

        taskList.markTask(taskID);

        StringBuilder sb = new StringBuilder();

        sb.append("Okay! I have marked that task as done.\n");
        sb.append(taskList.getTaskDescription(taskID));
        sb.append("\n");

        if (!storage.saveListToStorage(taskList)) {
            sb.append("Woah, hold on...\n");
            sb.append("I seem to be unable to save your changes.\n");
            sb.append("Could you run the Save command?\n");
        }


        return sb.toString();
    }
}
