package com.neokortex.commands.impl;

import com.neokortex.DialoguePath;
import com.neokortex.commands.Response;
import com.neokortex.commands.ResponseStatus;

public class CommandResponse extends Response {
    public CommandResponse(DialoguePath directive, ResponseStatus responseType) {
        super(directive, responseType);
    }
}
