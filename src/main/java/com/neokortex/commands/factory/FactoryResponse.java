package com.neokortex.commands.factory;

import com.neokortex.DialoguePath;
import com.neokortex.commands.Response;
import com.neokortex.commands.ResponseStatus;
import com.neokortex.commands.impl.Command;

public class FactoryResponse extends Response {
    private Command result;

    public FactoryResponse(DialoguePath directive, ResponseStatus responseType) {
        super(directive, responseType);
    }

    public void setResult(Command command) {
        this.result = command;
    }

    public Command getResult() {
        return this.result;
    }
}
