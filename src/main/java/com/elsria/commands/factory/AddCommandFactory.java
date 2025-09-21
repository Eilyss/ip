package com.elsria.commands.factory;

import com.elsria.DialoguePath;
import com.elsria.commands.ResponseStatus;
import com.elsria.commands.impl.AddCommand;
import com.elsria.commands.impl.Command;
import com.elsria.commands.impl.CommandRequest;
import com.elsria.core.ApplicationContext;
import com.elsria.task.DeadlineTask;
import com.elsria.task.EventTask;
import com.elsria.task.Task;
import com.elsria.task.ToDoTask;
import com.elsria.time.Time;

public class AddCommandFactory extends CommandFactory {
    @Override
    public FactoryResponse create(ApplicationContext context, CommandRequest request) {
        return switch(request.getCommandType()) {
        case TODO -> createTodo(context, request);
        case DEADLINE -> createDeadline(context, request);
        case EVENT -> createEvent(context, request);
        default -> new FactoryResponse(DialoguePath.CATASTROPHIC_FAILURE, ResponseStatus.CATASTROPHIC_FAILURE);
        };
    }

    private FactoryResponse createTodo(ApplicationContext context, CommandRequest request) {
        String[] arguments = request.getTokens();
        Task todoTask = new ToDoTask(arguments[0].strip().replaceAll("\\s+", " "));
        Command command = new AddCommand(context.getStorage(), context.getTaskList(), todoTask);
        FactoryResponse response = new FactoryResponse(DialoguePath.INTERMEDIARY, ResponseStatus.SUCCESS);
        response.setResult(command);
        return response;
    }

    private FactoryResponse createDeadline(ApplicationContext context, CommandRequest request) {
        String[] arguments = request.getTokens();

        String description = arguments[0].strip().replaceAll("\\s+", " ");
        Time deadline = Time.parseTime(arguments[1]);

        if (deadline == null) {
            return new FactoryResponse(DialoguePath.INVALID_TIME_SPECIFIED, ResponseStatus.TOTAL_FAILURE);
        }

        Task deadLineTask = new DeadlineTask(description, deadline);
        Command command = new AddCommand(context.getStorage(), context.getTaskList(), deadLineTask);
        FactoryResponse response = new FactoryResponse(DialoguePath.INTERMEDIARY, ResponseStatus.SUCCESS);
        response.setResult(command);
        return response;
    }

    private FactoryResponse createEvent(ApplicationContext context, CommandRequest request) {
        String[] arguments = request.getTokens();

        String description = arguments[0].strip().replaceAll("\\s+", " ");
        Time startTime = Time.parseTime(arguments[1]);
        Time endTime = Time.parseTime(arguments[2]);

        if (startTime == null || endTime == null) {
            return new FactoryResponse(DialoguePath.INVALID_TIME_SPECIFIED, ResponseStatus.TOTAL_FAILURE);
        }

        Task eventTask = new EventTask(description, startTime, endTime);
        Command command = new AddCommand(context.getStorage(), context.getTaskList(), eventTask);
        FactoryResponse response = new FactoryResponse(DialoguePath.INTERMEDIARY, ResponseStatus.SUCCESS);
        response.setResult(command);
        return response;
    }

}
