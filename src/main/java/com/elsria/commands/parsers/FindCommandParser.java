package com.elsria.commands.parsers;

import com.elsria.DialoguePath;
import com.elsria.commands.CommandType;
import com.elsria.commands.ResponseStatus;
import com.elsria.commands.impl.CommandRequest;

public class FindCommandParser extends CommandParser {
    @Override
    public ParserResponse parse(CommandType commandType, String input) {
        CommandBody body = CommandParser.extractCommandBody(input);
        String[] tokens;
        ParserResponse response;

        if (body.getBody().trim().isEmpty()) {
            return new ParserResponse(DialoguePath.NO_TASK_ID_SPECIFIED, ResponseStatus.TOTAL_FAILURE);
        }

        tokens = new String[] {
                body.getBody()
        };

        response = new ParserResponse(DialoguePath.INTERMEDIARY, ResponseStatus.SUCCESS);
        response.setCommandRequest(new CommandRequest(body.getCommandType(), input, tokens));
        return response;

    }
}
