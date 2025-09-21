package com.elsria.commands.factory;

import com.elsria.DialoguePath;
import com.elsria.commands.Response;
import com.elsria.commands.ResponseStatus;
import com.elsria.commands.impl.Command;

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
