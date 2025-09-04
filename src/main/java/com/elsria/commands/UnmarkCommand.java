package com.elsria.commands;

import com.elsria.core.ApplicationContext;
import com.elsria.core.Storage;
import com.elsria.core.UiHandler;
import com.elsria.task.TaskList;

public class UnmarkCommand extends Command {
    private final UiHandler uiHandler;
    private final TaskList taskList;
    private final Storage storage;
    private final String[] arguments;

    public UnmarkCommand(ApplicationContext context, CommandRequest request) {
        super(context, request);
        this.uiHandler = context.getUiHandler();
        this.taskList = context.getTaskList();
        this.storage = context.getStorage();
        this.arguments = request.getArgs();
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

        CommandUtils.saveListToStorage(this.storage, this.taskList, this.uiHandler);


        this.uiHandler.sayMessages();
    }
}
