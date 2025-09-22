package com.neokortex.commands.factory;

import com.neokortex.DialoguePath;
import com.neokortex.commands.ResponseStatus;
import com.neokortex.commands.impl.Command;
import com.neokortex.commands.impl.CommandRequest;
import com.neokortex.commands.impl.GreetCommand;
import com.neokortex.core.ApplicationContext;

public class GreetCommandFactory extends CommandFactory {
    @Override
    public FactoryResponse create(ApplicationContext context, CommandRequest request) {
        Command command = new GreetCommand();
        FactoryResponse response = new FactoryResponse(DialoguePath.INTERMEDIARY, ResponseStatus.SUCCESS);
        response.setResult(command);
        return response;
    }
}
