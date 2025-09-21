package com.elsria.commands;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import com.elsria.commands.factory.AddCommandFactory;
import com.elsria.commands.factory.CommandFactory;
import com.elsria.commands.factory.DeleteCommandFactory;
import com.elsria.commands.factory.EchoCommandFactory;
import com.elsria.commands.factory.FarewellCommandFactory;
import com.elsria.commands.factory.FindCommandFactory;
import com.elsria.commands.factory.GreetCommandFactory;
import com.elsria.commands.factory.ListCommandFactory;
import com.elsria.commands.factory.MarkCommandFactory;
import com.elsria.commands.factory.SaveCommandFactory;
import com.elsria.commands.factory.UnmarkCommandFactory;
import com.elsria.commands.parsers.AddCommandParser;
import com.elsria.commands.parsers.CommandParser;
import com.elsria.commands.parsers.DeleteCommandParser;
import com.elsria.commands.parsers.EchoCommandParser;
import com.elsria.commands.parsers.FarewellCommandParser;
import com.elsria.commands.parsers.FindCommandParser;
import com.elsria.commands.parsers.GreetCommandParser;
import com.elsria.commands.parsers.ListCommandParser;
import com.elsria.commands.parsers.MarkCommandParser;
import com.elsria.commands.parsers.SaveCommandParser;
import com.elsria.commands.parsers.UnmarkCommandParser;
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
 * @see CommandFactory
 * @see ApplicationContext
 * @see CommandRequest
 */
public enum CommandType {
    GREET(Set.of("hello", "hi", "greet"), new GreetCommandParser(), new GreetCommandFactory()),
    ECHO(Set.of("echo"), new EchoCommandParser(), new EchoCommandFactory()),
    TODO(Set.of("todo"), new AddCommandParser(), new AddCommandFactory()),
    DEADLINE(Set.of("deadline"), new AddCommandParser(), new AddCommandFactory()),
    EVENT(Set.of("event"), new AddCommandParser(), new AddCommandFactory()),
    DELETE(Set.of("delete", "remove"), new DeleteCommandParser(), new DeleteCommandFactory()),
    LIST(Set.of("list"), new ListCommandParser(), new ListCommandFactory()),
    SAVE(Set.of("save"), new SaveCommandParser(), new SaveCommandFactory()),
    FIND(Set.of("find", "search"), new FindCommandParser(), new FindCommandFactory()),
    MARK(Set.of("mark"), new MarkCommandParser(), new MarkCommandFactory()),
    UNMARK(Set.of("unmark"), new UnmarkCommandParser(), new UnmarkCommandFactory()),
    FAREWELL(Set.of("bye", "farewell", "goodbye", "exit", "quit"),
            new FarewellCommandParser(), new FarewellCommandFactory()),
    INVALID(Set.of("invalid"), null, null);

    private final Set<String> alias;
    private final CommandParser parser;
    private final CommandFactory factory;

    CommandType(Set<String> alias, CommandParser parser, CommandFactory factory) {
        this.alias = alias;
        this.parser = parser;
        this.factory = factory;
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

    public CommandParser getParser() {
        return this.parser;
    }

    public CommandFactory getFactory() {
        return this.factory;
    }
}
