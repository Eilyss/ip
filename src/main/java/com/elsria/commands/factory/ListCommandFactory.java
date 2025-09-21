package com.elsria.commands.factory;

import com.elsria.DialoguePath;
import com.elsria.commands.ResponseStatus;
import com.elsria.commands.impl.Command;
import com.elsria.commands.impl.CommandRequest;
import com.elsria.commands.impl.ListCommand;
import com.elsria.core.ApplicationContext;

public class ListCommandFactory extends CommandFactory {
    @Override
    public FactoryResponse create(ApplicationContext context, CommandRequest request) {
        Command command = new ListCommand(context.getTaskList());
        FactoryResponse response = new FactoryResponse(DialoguePath.INTERMEDIARY, ResponseStatus.SUCCESS);
        response.setResult(command);
        return response;
    }
}
