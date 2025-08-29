package com.elsria.commands;

import com.elsria.Constants;
import com.elsria.task.Task;
import com.elsria.task.TaskList;
import com.elsria.UiHandler;

public abstract class AddToListCommand extends Command {
    private UiHandler ui;
    private TaskList taskList;
    private Task task;
    protected String errorMessage;

    public AddToListCommand(UiHandler uiHandler, TaskList taskList, String rawArguments) {
        this.taskList = taskList;
        this.ui = uiHandler;
        try {
            this.task = createTask(rawArguments);
        } catch (IllegalArgumentException e) {
            this.errorMessage = "Woah, something went wrong...";
        }
    }


    @Override
    public void execute() {
        if (task == null) {
            this.ui.say(this.errorMessage);
            return;
        }
        this.taskList.add(this.task);
        this.ui.queueMessage("added: " + this.task);
        if (!this.taskList.writeToFile(Constants.LIST_STORAGE_DIRECTORY, Constants.TO_DO_LIST_FILENAME)) {
            this.ui.queueMessage("Woah, hold on...");
            this.ui.queueMessage("I seem to be unable to save your changes.");
            this.ui.queueMessage("Could you run the Save command?");
        }
        this.ui.sayMessages();

    }


    public abstract Task createTask(String args);
}
