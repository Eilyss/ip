package com.Elsria;

import java.util.List;

public class CommandContext {
    private final String name;
    private final UIHandler uiHandler;
    private final TaskList taskList;
    private final List<String> args;

    public CommandContext(String name, UIHandler uiHandler, TaskList taskList, String[] args) {
        this.name = name;
        this.uiHandler = uiHandler;
        this.taskList = taskList;
        this.args = List.of(args);
    }

    public String getName() {
        return this.name;
    }

    public UIHandler getUIHandler() {
        return this.uiHandler;
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    public String getArg(int index) {
        return this.args.get(index);
    }
}
