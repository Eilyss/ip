package com.Elsria.Commands;

import com.Elsria.*;

public class AddCommand extends Command {
    private UIHandler ui;
    private TaskList taskList;
    private Task task;

    public AddCommand(UIHandler uiHandler, TaskList taskList, Task task) {
        this.taskList = taskList;
        this.ui = uiHandler;
        this.task = task;
    }


    @Override
    public void execute() {
        this.taskList.add(task);
        this.ui.say("added: " + task);
    }
}
