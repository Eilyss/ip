package com.Elsria.Commands;

import com.Elsria.*;

public abstract class AddToListCommand extends Command {
    private UIHandler ui;
    private TaskList taskList;
    private Task task;
    protected String errorMessage;

    public AddToListCommand(UIHandler uiHandler, TaskList taskList, String rawArguments) {
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
        this.ui.say("added: " + this.task);
    }

    public abstract Task createTask(String args);
}
