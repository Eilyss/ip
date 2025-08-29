package com.Elsria.Commands;

import com.Elsria.Command;
import com.Elsria.TaskList;
import com.Elsria.UiHandler;

public class ListCommand extends Command {
    private UiHandler uiHandler;
    private TaskList taskList;

    public ListCommand(UiHandler uiHandler, TaskList taskList) {
        this.uiHandler = uiHandler;
        this.taskList = taskList;
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
