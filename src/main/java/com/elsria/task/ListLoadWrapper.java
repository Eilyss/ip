package com.elsria.task;

import java.util.ArrayList;

public class ListLoadWrapper {
    private TaskList taskList;
    private ArrayList<String> failedSerializations;

    public ListLoadWrapper(TaskList taskList, ArrayList<String> failedSerializations) {
        this.taskList = taskList;
        this.failedSerializations = failedSerializations;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public ArrayList<String> getFailedSerializations() {
        return failedSerializations;
    }
}
