package com.elsria.commands;

import com.elsria.Constants;
import com.elsria.core.ApplicationContext;
import com.elsria.task.Task;
import com.elsria.task.TaskList;
import com.elsria.UiHandler;

public abstract class AddToListCommand extends Command {
    private final UiHandler uiHandler;
    private final TaskList taskList;
    private Task task;
    protected String errorMessage;

    public AddToListCommand(ApplicationContext applicationContext, CommandRequest commandRequest) {
        super(applicationContext, commandRequest);
        this.taskList = applicationContext.getTaskList();
        this.uiHandler = applicationContext.getUIHandler();
        try {
            this.task = createTask(commandRequest.getRawArgs());
        } catch (IllegalArgumentException e) {
            this.errorMessage = "Woah, something went wrong...";
        }
    }


    @Override
    public void execute() {
        if (task == null) {
            this.uiHandler.say(this.errorMessage);
            return;
        }
        this.taskList.add(this.task);
        this.uiHandler.queueMessage("added: " + this.task);
        if (!this.taskList.writeToFile(Constants.LIST_STORAGE_DIRECTORY, Constants.TO_DO_LIST_FILENAME)) {
            this.uiHandler.queueMessage("Woah, hold on...");
            this.uiHandler.queueMessage("I seem to be unable to save your changes.");
            this.uiHandler.queueMessage("Could you run the Save command?");
        }
        this.uiHandler.sayMessages();

    }


    public abstract Task createTask(String args);
}
