package com.elsria.commands;

import com.elsria.Constants;
import com.elsria.task.TaskList;
import com.elsria.UiHandler;

public class DeleteCommand extends Command {
    private UiHandler uiHandler;
    private TaskList taskList;
    private String[] arguments;

    public DeleteCommand(UiHandler uiHandler, TaskList taskList, String[] arguments) {
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
        if (!this.taskList.writeToFile(Constants.LIST_STORAGE_DIRECTORY, Constants.TO_DO_LIST_FILENAME)) {
            this.uiHandler.queueMessage("Woah, hold on...");
            this.uiHandler.queueMessage("I seem to be unable to save your changes.");
            this.uiHandler.queueMessage("Could you run the Save command?");
        }
        this.uiHandler.sayMessages();
    }
}
