package com.elsria.core;

import com.elsria.task.TaskList;
import com.elsria.time.dateparser.DateParser;

public class ApplicationContext {
    private final String name;
    private final UiHandler uiHandler;
    private final TaskList taskList;
    private final Storage storage;

    public ApplicationContext(String name, UiHandler uiHandler, TaskList taskList, Storage storage) {
        this.name = name;
        this.uiHandler = uiHandler;
        this.taskList = taskList;
        this.storage = storage;
    }

    public String getName() {
        return this.name;
    }

    public UiHandler getUIHandler() {
        return this.uiHandler;
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    public Storage getStorage() {
        return this.storage;
    }
}
