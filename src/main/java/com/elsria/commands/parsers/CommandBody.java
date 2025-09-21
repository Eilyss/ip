package com.elsria.commands.parsers;

import com.elsria.commands.CommandType;

public class CommandBody {
    private CommandType commandType;
    private String body;

    public CommandBody(CommandType commandType, String body) {
        this.commandType = commandType;
        this.body = body;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public String getBody() {
        return body;
    }
}
