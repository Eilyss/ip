package com.elsria.commands.parsers;

import com.elsria.DialoguePath;
import com.elsria.commands.impl.CommandRequest;
import com.elsria.commands.Response;
import com.elsria.commands.ResponseStatus;

public class ParserResponse extends Response {
    private CommandRequest request;

    public ParserResponse(DialoguePath directive, ResponseStatus responseStatus) {
        super(directive, responseStatus);
    }

    public void setCommandRequest(CommandRequest request) {
        this.request = request;
    }

    public CommandRequest getResult() {
        return request;
    }
}
