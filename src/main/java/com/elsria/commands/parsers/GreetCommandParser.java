package com.elsria.commands.parsers;

import com.elsria.DialoguePath;
import com.elsria.commands.CommandType;
import com.elsria.commands.ResponseStatus;
import com.elsria.commands.impl.CommandRequest;

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
