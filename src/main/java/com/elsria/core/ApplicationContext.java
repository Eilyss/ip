package com.elsria.core;

import com.elsria.task.TaskList;
import com.elsria.UiHandler;
import com.elsria.time.dateparser.DateParser;

import java.time.LocalDate;
import java.util.List;

public class ApplicationContext {
    private final String name;
    private final UiHandler uiHandler;
    private final TaskList taskList;
    private final DateParser dateParser;

    public ApplicationContext(String name) {
        this.name = name;
        this.uiHandler = new UiHandler();
        this.taskList = new TaskList();
        this.dateParser = null;
    }

    public ApplicationContext(String name, UiHandler uiHandler, TaskList taskList, DateParser dateParser) {
        this.name = name;
        this.uiHandler = uiHandler;
        this.taskList = taskList;
        this.dateParser = dateParser;
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

    public DateParser getDateParser() { return this.dateParser; }
}
