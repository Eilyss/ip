package com.neokortex.commands.parsers;

import com.neokortex.DialoguePath;
import com.neokortex.commands.CommandType;
import com.neokortex.commands.ResponseStatus;
import com.neokortex.commands.impl.CommandRequest;

public class GreetCommandParser extends CommandParser {
    @Override
    public ParserResponse parse(CommandType commandType, String input) {
        CommandBody body = CommandParser.extractCommandBody(input);
        ParserResponse response;
        response = new ParserResponse(DialoguePath.INTERMEDIARY, ResponseStatus.SUCCESS);
        response.setCommandRequest(new CommandRequest(body.getCommandType(), input, new String[] {body.getBody()}));
        return response;
    }
}
