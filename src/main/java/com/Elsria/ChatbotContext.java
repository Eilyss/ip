package com.Elsria;

public class ChatbotContext {
    private final UIHandler uiHandler;
    private final TaskList taskList;

    public ChatbotContext(UIHandler uiHandler, TaskList taskList) {
        this.uiHandler = uiHandler;
        this.taskList = taskList;
    }

    public UIHandler getUIHandler() {
        return uiHandler;
    }

    public TaskList getTaskList() {
        return taskList;
    }
}
