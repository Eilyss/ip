package com.elsria.commands;

import com.elsria.core.ApplicationContext;
import com.elsria.core.UiHandler;
import com.elsria.task.Task;
import com.elsria.task.TaskList;

/**
 * Command for searching tasks by keyword in their descriptions.
 * <p>
 * The {@code FindCommand} searches through all tasks in the task list and displays
 * all tasks that contain the specified keyword in their description. The search
 * is not case-sensitive and result is formatted as a {@link TaskList}, similar to
 * in a {@link ListCommand}.
 * </p>
 *
 * <p><b>Command Format:</b></p>
 * <pre>
 * [find/search] [keyword]
 * </pre>
 *
 * <p><b>Search Behavior:</b></p>
 * <ul>
 *   <li>Case-insensitive keyword matching</li>
 *   <li>Searches within task descriptions</li>
 *   <li>Returns tasks containing the keyword</li>
 *   <li>Displays results as a {@link TaskList}</li>
 *   <li>Shows appropriate message when no matches are found</li>
 * </ul>
 *
 * <p><b>Output Format:</b></p>
 * <p>Input: find groce</p>
 * <pre>
 * Here you go!
 * 1. [T][ ] Buy groceries
 * 2. [D][X] grocery shopping by tomorrow
 * </pre>
 *
 * @see Command
 * @see TaskList#getTasksContainingKeyword(String)
 * @see Task#containsKeyword(String)
 */
public class FindCommand extends Command {
    private final UiHandler uiHandler;
    private final TaskList taskList;
    private String[] arguments;

    public FindCommand(ApplicationContext context, CommandRequest request) {
        super(context, request);
        this.uiHandler = context.getUiHandler();
        this.taskList = context.getTaskList();
        this.arguments = request.getArgs();
    }

    @Override
    public void execute() {
        TaskList uniqueList = this.taskList.getTasksContainingKeyword(arguments[0]);
        if (uniqueList.isEmpty()) {
            uiHandler.say("Hmm... there are no tasks that match your search");
            return;
        }

        this.uiHandler.queueMessage("Here you go!");
        for (int i = 0; i < uniqueList.size(); i++) {
            this.uiHandler.queueMessage(String.format("%d. %s", i + 1, uniqueList.get(i).toString()));
        }
        this.uiHandler.sayMessages();
    }
}
