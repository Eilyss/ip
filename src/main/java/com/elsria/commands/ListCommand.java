package com.elsria.commands;

import com.elsria.core.ApplicationContext;
import com.elsria.task.TaskList;

/**
 * Displays all tasks in the task list.
 * <p>
 * The {@code ListCommand} shows a numbered list of all tasks currently stored.
 * Has special dialogue if the list is empty.
 * </p>
 *
 * <p><b>Command Format:</b></p>
 * <pre>
 * list
 * </pre>
 *
 * <p><b>Output Format:</b></p>
 * <pre>
 * Here are the tasks on your list:
 * 1. [T][ ] Buy groceries
 * 2. [D][X] Submit report by tomorrow
 * 3. [E][ ] Meeting from 2pm to 3pm
 * </pre>
 *
 * <p><b>Behavior:</b></p>
 * <ul>
 *   <li>Displays all tasks in insertion order</li>
 *   <li>Numbers tasks sequentially starting from 1</li>
 *   <li>Shows task type, completion status, and description</li>
 *   <li>Special dialogue when list is empty</li>
 * </ul>
 *
 * @see Command
 * @see TaskList
 */
public class ListCommand extends Command {
    private final TaskList taskList;

    /**
     * Constructs a new ListCommand with the specified context and request.
     *
     * @param context the {@link ApplicationContext} providing access to shared state and services.
     * @param request the {@link CommandRequest}. {@code ListCommand} takes no arguments, hence
     *                this is ignored.
     * @throws NullPointerException if context is null
     */
    public ListCommand(ApplicationContext context, CommandRequest request) {
        super(context, request);
        this.taskList = context.getTaskList();
    }

    @Override
    public String execute() {
        if (this.taskList.isEmpty()) {
            return "Hmm... there's nothing in your list right now.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks on your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            sb.append(
                    String.format("%d. %s\n", i + 1, this.taskList.get(i).toString())
            );
        }


        return sb.toString();
    }
}
