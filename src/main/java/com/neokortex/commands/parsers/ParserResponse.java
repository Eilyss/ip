package com.neokortex.commands.parsers;

import com.neokortex.DialoguePath;
import com.neokortex.commands.impl.CommandRequest;
import com.neokortex.commands.Response;
import com.neokortex.commands.ResponseStatus;

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
