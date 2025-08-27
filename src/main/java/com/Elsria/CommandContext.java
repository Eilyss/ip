package com.Elsria;

import java.util.List;

public class CommandContext {
    private final String name;
    private final UIHandler uiHandler;
    private final TaskList taskList;
    private final List<String> args;
    private String rawArgs;

    public CommandContext(String name, UIHandler uiHandler, TaskList taskList, String[] args) {
        this.name = name;
        this.uiHandler = uiHandler;
        this.taskList = taskList;
        this.args = List.of(args);
    }

    public CommandContext(String name, UIHandler uiHandler, TaskList taskList, String[] args, String rawArgs) {
        this.name = name;
        this.uiHandler = uiHandler;
        this.taskList = taskList;
        this.args = List.of(args);
        this.rawArgs = rawArgs;
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

    public String[] getArgs() {
        return this.args.toArray(new String[0]);
    }

    public String getArg(int index) {
        return this.args.get(index);
    }

    public String getRawArgs() {
        return this.rawArgs;
    }
}
