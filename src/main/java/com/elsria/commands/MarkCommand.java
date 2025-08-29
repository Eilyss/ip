package com.elsria.commands;

import com.elsria.task.TaskList;
import com.elsria.UiHandler;

public class MarkCommand extends Command {
    private UiHandler uiHandler;
    private TaskList taskList;
    private String[] arguments;

    public MarkCommand(UiHandler uiHandler, TaskList taskList, String[] arguments) {
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

        taskList.markTask(taskID);
        this.uiHandler.queueMessage("Okay! I have marked that task as done.");
        this.uiHandler.queueMessage(taskList.getTaskDescription(taskID));
        this.uiHandler.sayMessages();
    }
}
