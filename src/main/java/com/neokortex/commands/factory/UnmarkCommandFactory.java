package com.neokortex.commands.factory;

import com.neokortex.DialoguePath;
import com.neokortex.commands.ResponseStatus;
import com.neokortex.commands.impl.Command;
import com.neokortex.commands.impl.CommandRequest;
import com.neokortex.commands.impl.UnmarkCommand;
import com.neokortex.core.ApplicationContext;

public class UnmarkCommandFactory extends CommandFactory {
    @Override
    public FactoryResponse create(ApplicationContext context, CommandRequest request) {
        Command command = new UnmarkCommand(context.getStorage(), context.getTaskList(),
                Integer.parseInt(request.getTokens()[0]));
        FactoryResponse response = new FactoryResponse(DialoguePath.INTERMEDIARY, ResponseStatus.SUCCESS);
        response.setResult(command);
        return response;
    }
}
