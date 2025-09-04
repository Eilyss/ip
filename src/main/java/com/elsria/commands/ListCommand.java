package com.elsria.commands;

import com.elsria.core.ApplicationContext;
import com.elsria.core.UiHandler;
import com.elsria.task.TaskList;

public class ListCommand extends Command {
    private final UiHandler uiHandler;
    private final TaskList taskList;

    public ListCommand(ApplicationContext context, CommandRequest request) {
        super(context, request);
        this.uiHandler = context.getUiHandler();
        this.taskList = context.getTaskList();
    }

    @Override
    public void execute() {
        if (this.taskList.isEmpty()) {
            uiHandler.say("Hmm... there's nothing in your list right now.");
            return;
        }

        this.uiHandler.queueMessage("Here are the tasks on your list:");
        for (int i = 0; i < taskList.size(); i++) {
            this.uiHandler.queueMessage(
                    String.format("%d. %s", i + 1, this.taskList.get(i).toString())
            );
        }
        this.uiHandler.sayMessages();
    }
}
