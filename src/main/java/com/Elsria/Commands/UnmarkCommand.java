package com.Elsria.Commands;

import com.Elsria.Command;
import com.Elsria.TaskList;
import com.Elsria.UIHandler;

public class UnmarkCommand extends Command {
    private UIHandler uiHandler;
    private TaskList taskList;
    private String[] arguments;

    public UnmarkCommand(UIHandler uiHandler, TaskList taskList, String[] arguments) {
        this.uiHandler = uiHandler;
        this.taskList = taskList;
        this.arguments = arguments;
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
        this.uiHandler.sayMessages();
    }
}
