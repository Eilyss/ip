package com.elsria.commands;

import com.elsria.core.ApplicationContext;
import com.elsria.task.TaskList;
import com.elsria.UiHandler;

public class ListCommand extends Command {
    private UiHandler uiHandler;
    private TaskList taskList;

    public ListCommand(ApplicationContext context, CommandRequest request) {
        super(context, request);
        this.uiHandler = context.getUIHandler();
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
            this.uiHandler.queueMessage(String.format("%d. %s", i + 1, this.taskList.get(i).toString()));
        }
        this.uiHandler.sayMessages();
    }
}
