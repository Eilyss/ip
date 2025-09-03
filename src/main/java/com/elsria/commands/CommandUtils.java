package com.elsria.commands;

import com.elsria.core.Storage;
import com.elsria.core.UiHandler;
import com.elsria.task.TaskList;

public class CommandUtils {
    static void saveListToStorage(Storage storage, TaskList taskList, UiHandler uiHandler) {
        if (!storage.saveListToStorage(taskList)) {
            uiHandler.queueMessage("Woah, hold on...");
            uiHandler.queueMessage("I seem to be unable to save your changes.");
            uiHandler.queueMessage("Could you run the Save command?");
        }
    }
}
