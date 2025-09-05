package com.elsria.commands;

import com.elsria.core.ApplicationContext;
import com.elsria.core.Storage;
import com.elsria.core.UiHandler;
import com.elsria.task.Task;
import com.elsria.task.TaskList;

import java.util.Set;

public class FindCommand extends Command {
    private final UiHandler uiHandler;
    private final TaskList taskList;
    private String[] arguments;

    public FindCommand(ApplicationContext context, CommandRequest request) {
        super(context, request);
        this.uiHandler = context.getUiHandler();
        this.taskList = context.getTaskList();
        this.arguments = request.getArgs();
    }

    @Override
    public void execute() {
        TaskList uniqueList = this.taskList.getTasksContainingKeyword(arguments[0]);
        if (uniqueList.isEmpty()) {
            uiHandler.say("Hmm... there are no tasks that match your search");
            return;
        }

        this.uiHandler.queueMessage("Here you go!");
        for (int i = 0; i < uniqueList.size(); i++) {
            this.uiHandler.queueMessage(String.format("%d. %s", i + 1, uniqueList.get(i).toString()));
        }
        this.uiHandler.sayMessages();
    }
}
