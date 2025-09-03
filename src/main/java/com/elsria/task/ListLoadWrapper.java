package com.elsria.task;

import java.util.List;

public class ListLoadWrapper {
    private TaskList taskList;
    private List<String> failedSerializations;

    public ListLoadWrapper(TaskList taskList, List<String> failedSerializations) {
        this.taskList = taskList;
        this.failedSerializations = failedSerializations;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public List<String> getFailedSerializations() {
        return failedSerializations;
    }
}
