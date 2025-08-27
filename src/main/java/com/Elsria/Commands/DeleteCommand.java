package com.Elsria.Commands;

import com.Elsria.Command;
import com.Elsria.Task;
import com.Elsria.TaskList;
import com.Elsria.UIHandler;

public class DeleteCommand extends Command {
    private UIHandler uiHandler;
    private TaskList taskList;
    private String[] arguments;

    public DeleteCommand(UIHandler uiHandler, TaskList taskList, String[] arguments) {
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


        this.uiHandler.queueMessage(String.format("Okay! I got rid of task %d", taskID));
        this.uiHandler.queueMessage(taskList.getTaskDescription(taskID));
        taskList.remove(taskID);
        this.uiHandler.queueMessage(String.format("Now you've got %d tasks in the list!", taskID));
        this.uiHandler.sayMessages();
    }
}
