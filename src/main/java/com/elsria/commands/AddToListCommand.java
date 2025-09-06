package com.elsria.commands;

import com.elsria.core.ApplicationContext;
import com.elsria.core.Storage;
import com.elsria.core.UiHandler;
import com.elsria.task.Task;
import com.elsria.task.TaskList;

/**
 * TODO: Scrap AddToListCommands, just have an AddCommand, unnecessary inheritance
 * Abstract class representing a general Command that adds specific tasks to a list,
 * which extends from Command
 * <p>
 * This class provides a basic structure for commands that interact
 * with the TaskList
 *
 * <p>
 * Subclasses should provide concrete implementations of the
 * abstract methods such as {@link #createTask(String args)}.
 */
public abstract class AddToListCommand extends Command {
    /**
     * Error message to run in case of failure
     */
    protected String errorMessage;
    private final UiHandler uiHandler;
    private final TaskList taskList;
    private final Storage storage;
    private Task task;


    /**
     * Default constructor for all AddToListCommands
     * It uses the exact same parameters as the default constructor for Command,
     * but it provides default implemenation for extracting necessary items from
     * context and request.
     *
     * @param context The application context for the command
     * @param request The command data parsed from user input
     */
    public AddToListCommand(ApplicationContext context, CommandRequest request) {
        super(context, request);
        this.taskList = context.getTaskList();
        this.uiHandler = context.getUiHandler();
        this.storage = context.getStorage();
        try {
            this.task = createTask(request.getRawArgs());
        } catch (IllegalArgumentException e) {
            this.errorMessage = "Woah, something went wrong...";
        }
    }


    @Override
    public void execute() {
        if (task == null) {
            this.uiHandler.say(this.errorMessage);
            return;
        }
        this.taskList.add(this.task);
        this.uiHandler.queueMessage("added: " + this.task);

        CommandUtils.saveListToStorage(this.storage, this.taskList, this.uiHandler);

        this.uiHandler.sayMessages();

    }

    public abstract Task createTask(String args);
}
