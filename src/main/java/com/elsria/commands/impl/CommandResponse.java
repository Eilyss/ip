package com.elsria.commands.impl;

import com.elsria.DialoguePath;
import com.elsria.commands.Response;
import com.elsria.commands.ResponseStatus;

public class CommandResponse extends Response {
    public CommandResponse(DialoguePath directive, ResponseStatus responseType) {
        super(directive, responseType);
    }
}
