package com.elsria.commands;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import com.elsria.core.ApplicationContext;

/**
 * Represents all available {@link Command}s as an enum with support for multiple
 * name aliases, and links each enum to the constructor of each {@link Command}.
 * <p>
 * The {@code CommandType} enum serves as a central {@link Command} registry that
 * maps command keywords to their respective {@link Command}. This provides a
 * unified interface for command instantiation.
 * </p>
 *
 * <p><b>Responsibilities:</b></p>
 * <ul>
 *   <li>Maps command keywords and command implementation</li>
 *   <li>Facilitate command creation</li>
 *   <li>Handles case-insensitive command lookup</li>
 *   <li>Returns {@link #INVALID} for unrecognized commands</li>
 *   <li>Supports multiple aliases for each command type</li>
 * </ul>
 *
 * <p><b>Supported Commands:</b></p>
 * <ul>
 *   <li>{@code GREET} - Greeting commands: "hello", "hi"</li>
 *   <li>{@code ECHO} - Echo commands: "echo"</li>
 *   <li>{@code TODO} - Todo task commands: "todo"</li>
 *   <li>{@code DEADLINE} - Deadline task commands: "deadline"</li>
 *   <li>{@code EVENT} - Event task commands: "event"</li>
 *   <li>{@code DELETE} - Task deletion commands: "delete", "remove"</li>
 *   <li>{@code LIST} - List display commands: "list"</li>
 *   <li>{@code FIND} - Search commands: "find", "search"</li>
 *   <li>{@code MARK} - Task completion marking: "mark"</li>
 *   <li>{@code UNMARK} - Task unmarking: "unmark"</li>
 *   <li>{@code FAREWELL} - Exit commands: "bye", "farewell", "goodbye", "exit", "quit"</li>
 *   <li>{@code INVALID} - Fallback for unrecognized commands</li>
 * </ul>
 *
 * Credit: JavaDoc was written with guidance from generative AI
 *
 * @see Command
 * @see CommandCreator
 * @see ApplicationContext
 * @see CommandRequest
 */
public enum CommandType {
    GREET(Set.of("hello", "hi"), GreetCommand::new),
    ECHO(Set.of("echo"), EchoCommand::new),
    TODO(Set.of("todo"), AddCommand::new),
    DEADLINE(Set.of("deadline"), AddCommand::new),
    EVENT(Set.of("event"), AddCommand::new),
    DELETE(Set.of("delete", "remove"), DeleteCommand::new),
    LIST(Set.of("list"), ListCommand::new),
    FIND(Set.of("find", "search"), FindCommand::new),
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

    public static Set<String> getAllAliases() {
        return Arrays.stream(values())
                .flatMap(cmd -> cmd.alias.stream())
                .collect(Collectors.toSet());
    }

    public static boolean isValidCommand(String command) {
        return getCommandType(command) != INVALID;
    }
}
