package com.elsria.commands.factory;

import com.elsria.DialoguePath;
import com.elsria.commands.ResponseStatus;
import com.elsria.commands.impl.Command;
import com.elsria.commands.impl.CommandRequest;
import com.elsria.commands.impl.GreetCommand;
import com.elsria.core.ApplicationContext;

public class GreetCommandFactory extends CommandFactory {
    @Override
    public FactoryResponse create(ApplicationContext context, CommandRequest request) {
        Command command = new GreetCommand();
        FactoryResponse response = new FactoryResponse(DialoguePath.INTERMEDIARY, ResponseStatus.SUCCESS);
        response.setResult(command);
        return response;
    }
}
