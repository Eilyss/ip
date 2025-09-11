package com.elsria.commands;

import com.elsria.core.ApplicationContext;
import com.elsria.core.Storage;
import com.elsria.task.DeadlineTask;
import com.elsria.task.EventTask;
import com.elsria.task.Task;
import com.elsria.task.TaskList;
import com.elsria.task.ToDoTask;
import com.elsria.time.Time;

/**
 * TODO: Update Documentation
 * Abstract class representing a general Command that adds specific tasks to a list,
 * which extends from Command
 * <p>
 * This class provides a basic structure for commands that interact
 * with the TaskList
 */
public class AddCommand extends Command {
    /**
     * Error message to run in case of failure
     */
    protected String errorMessage;
    private final TaskList taskList;
    private final Storage storage;
    private Task task;


    /**
     * Default constructor for all AddToListCommands
     * It uses the exact same parameters as the default constructor for Command,
     * but it provides default implementation for extracting necessary items from
     * context and request.
     *
     * @param context The application context for the command
     * @param request The command data parsed from user input
     */
    public AddCommand(ApplicationContext context, CommandRequest request) {
        super(context, request);
        this.taskList = context.getTaskList();
        this.storage = context.getStorage();
        try {
            this.task = createTask(request.getCommandType(), request.getRawArgs());
        } catch (IllegalArgumentException e) {
            this.errorMessage = "Woah, something went wrong...";
        }
    }


    @Override
    public String execute() {
        if (task == null) {
            return this.errorMessage;
        }
        this.taskList.add(this.task);

        StringBuilder sb = new StringBuilder();
        sb.append("added: ");
        sb.append(this.task);
        sb.append("\n");

        if (!storage.saveListToStorage(taskList)) {
            sb.append("Woah, hold on...\n");
            sb.append("I seem to be unable to save your changes.\n");
            sb.append("Could you run the Save command?\n");
        }

        return sb.toString();

    }

    /**
     * Creates the correct task from the {@link CommandType} and arguments
     * provided by the user.
     *
     * @param type the type of Command, can only be TODO, DEADLINE and EVENT
     * @param args arguments provided in the command
     * @return the correct {@link Task}
     */
    public Task createTask(CommandType type, String args) {
        if (args.isEmpty()) {
            this.errorMessage = "Erm... so what task?";
            return null;
        }

        return switch(type) {
        case TODO -> new ToDoTask(args);
        case DEADLINE -> createDeadlineTask(args);
        case EVENT -> createEventTask(args);
        default -> null;
        };
    }

    private Task createDeadlineTask(String rawArguments) {
        String[] arguments = rawArguments.split(" /by ");

        if (arguments[0].trim().isEmpty()) {
            this.errorMessage = "Erm... so what task?";
            return null;
        }

        if (arguments.length == 1) {
            this.errorMessage = "But when?";
            return null;
        }
        if (arguments.length > 2) {
            this.errorMessage = "Woah that's too many! Which one?";
            return null;
        }

        Time time = Time.parseTime(arguments[1]);

        if (time == null) {
            this.errorMessage = "That is not a valid time :P";
            return null;
        }

        return new DeadlineTask(arguments[0], Time.parseTime(arguments[1]));
    }

    private Task createEventTask(String rawArguments) {
        String[] arguments = rawArguments.split(" /from ");

        if (arguments[0].isEmpty()) {
            this.errorMessage = "Erm... so what task?";
            return null;
        }

        if (arguments.length == 1) {
            this.errorMessage = "But from when?";
            return null;
        }

        if (arguments.length > 2) {
            this.errorMessage = "Woah that's too many start times! Which one?";
            return null;
        }

        String[] timings = arguments[1].split(" /to ");

        if (timings.length == 1) {
            this.errorMessage = "But to when?";
            return null;
        }

        if (timings.length > 2) {
            this.errorMessage = "Woah that's too many end times! Which one?";
            return null;
        }

        Time startTime = Time.parseTime(timings[0]);

        if (startTime == null) {
            this.errorMessage = "That is not a valid start time :P";
            return null;
        }

        Time endTime = Time.parseTime(timings[1]);

        if (endTime == null) {
            this.errorMessage = "That is not a valid end time :P";
            return null;
        }

        return new EventTask(arguments[0], startTime, endTime);
    }
}
