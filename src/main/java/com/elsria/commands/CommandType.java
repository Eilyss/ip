package com.elsria.commands;

import java.util.Arrays;
import java.util.Set;

import com.elsria.core.ApplicationContext;

/**
 * An Enum consisting all CommandTypes available in the project
 */
public enum CommandType {
    GREET(Set.of("hello", "hi"), GreetCommand::new),
    ECHO(Set.of("echo"), EchoCommand::new),
    TODO(Set.of("todo"), ToDoCommand::new),
    DEADLINE(Set.of("deadline"), DeadlineCommand::new),
    EVENT(Set.of("event"), EventCommand::new),
    DELETE(Set.of("delete", "remove"), DeleteCommand::new),
    LIST(Set.of("list"), ListCommand::new),
    MARK(Set.of("mark"), MarkCommand::new),
    UNMARK(Set.of("unmark"), UnmarkCommand::new),
    FAREWELL(Set.of("bye", "farewell", "goodbye", "exit", "quit"), FarewellCommand::new),
    INVALID(Set.of("invalid"), InvalidCommand::new);

    private final Set<String> alias;
    private final CommandCreator creator;

    CommandType(Set<String> alias, CommandCreator creator) {
        this.alias = alias;
        this.creator = creator;
    }

    public Command create(ApplicationContext context, CommandRequest request) {
        return this.creator.create(context, request);
    }

    public static CommandType getCommandType(String command) {
        String cleanCommand = command.toLowerCase().trim();

        return Arrays.stream(values())
                .filter(cmd -> cmd.alias.contains(cleanCommand))
                .findFirst()
                .orElse(INVALID);
    }
}
