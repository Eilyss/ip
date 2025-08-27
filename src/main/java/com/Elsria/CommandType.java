package com.Elsria;

import com.Elsria.Commands.FarewellCommand;
import com.Elsria.Commands.GreetCommand;
import com.Elsria.Commands.InvalidCommand;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

public enum CommandType {
    greet("hello") {
        @Override
        public GreetCommand create(CommandContext context) {
            return new GreetCommand(context.getUIHandler(), context.getName());
        }
    },
//    echo("echo"),
//    list("list"),
//    todo("todo"),
//    mark("mark"),
//    unmark("unmark"),
    farewell("bye") {
    @Override
        public FarewellCommand create(CommandContext context) {
            return new FarewellCommand(context.getUIHandler());
        }
    },
    invalid() {
        public InvalidCommand create(CommandContext context) {
            return new InvalidCommand(context.getUIHandler());
        }
    };

    private final Set<String> alias;

    CommandType(String... commands) {
        this.alias = Set.of(commands);
    }

    public abstract Command create(CommandContext c);

    public static CommandType getCommandType(String command) {
        String cleanCommand = command.toLowerCase().trim();

        return Arrays.stream(values())
                .filter(cmd -> cmd.alias.contains(cleanCommand))
                .findFirst()
                .orElse(invalid);
    }
}