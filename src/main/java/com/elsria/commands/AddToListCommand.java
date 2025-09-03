package com.elsria.commands;

import com.elsria.core.ApplicationContext;
import com.elsria.core.Storage;
import com.elsria.task.Task;
import com.elsria.task.TaskList;
import com.elsria.core.UiHandler;

public abstract class AddToListCommand extends Command {
    private final UiHandler uiHandler;
    private final TaskList taskList;
    private final Storage storage;
    private Task task;
    protected String errorMessage;

    public AddToListCommand(ApplicationContext applicationContext, CommandRequest commandRequest) {
        super(applicationContext, commandRequest);
        this.taskList = applicationContext.getTaskList();
        this.uiHandler = applicationContext.getUIHandler();
        this.storage = applicationContext.getStorage();
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

        CommandUtils.saveListToStorage(this.storage, this.taskList, this.uiHandler);

        this.uiHandler.sayMessages();

    }


    public abstract Task createTask(String args);
}
