package com.elsria.commands;

import com.elsria.core.Storage;
import com.elsria.core.UiHandler;
import com.elsria.task.TaskList;

/**
 * TODO: TO BE SCRAPPED, I'll try not to need something like this
 * A class that extracts common functionality
 * from similar Commands
 */
public class CommandUtils {
    /**
     * Handles the situation where a Command fails to store
     * the tasklist into storage
     *
     * @param storage storage handler
     * @param taskList task list to be stored
     * @param uiHandler the uiHandler to display messages
     */
    static void saveListToStorage(Storage storage, TaskList taskList, UiHandler uiHandler) {
        if (!storage.saveListToStorage(taskList)) {
            uiHandler.queueMessage("Woah, hold on...");
            uiHandler.queueMessage("I seem to be unable to save your changes.");
            uiHandler.queueMessage("Could you run the Save command?");
        }
    }
}
