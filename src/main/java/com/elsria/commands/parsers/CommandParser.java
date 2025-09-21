package com.elsria.commands.parsers;

import com.elsria.commands.CommandType;
import com.elsria.exceptions.MissingCommandException;

public abstract class CommandParser {
    public abstract ParserResponse parse(CommandType commandType, String input);

    static CommandBody extractCommandBody(String input) throws MissingCommandException {
        String[] parts = input.split("\\s+", 2);

        if (parts[0].trim().isEmpty()) {
            throw new MissingCommandException("No command found");
        }

        CommandType commandType = CommandType.getCommandType(parts[0].trim());
        String body = "";
        if (parts.length == 2) {
            body = parts[1].trim();
        }

        return new CommandBody(commandType, body);
    }
}
