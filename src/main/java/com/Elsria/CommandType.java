package com.Elsria;

import com.Elsria.Commands.*;

import java.util.Arrays;
import java.util.Set;

public enum CommandType {
    greet("hello") {
        @Override
        public GreetCommand create(CommandContext context) {
            return new GreetCommand(context.getUIHandler(), context.getName());
        }
    },
    echo("echo") {
        @Override
        public EchoCommand create(CommandContext context) {
            return new EchoCommand(context.getUIHandler(), context.getRawArgs());
        }
    },
    todo("todo") {
        @Override
        public ToDoCommand create(CommandContext context) {
            return new ToDoCommand(context.getUIHandler(), context.getTaskList(), context.getRawArgs());
        }
    },
    deadline("deadline") {
        @Override
        public DeadlineCommand create(CommandContext context) {
            return new DeadlineCommand(context.getUIHandler(), context.getTaskList(), context.getRawArgs());
        }
    },
    event("event") {
        @Override
        public EventCommand create(CommandContext context) {
            return new EventCommand(context.getUIHandler(), context.getTaskList(), context.getRawArgs());
        }
    },
    list("list") {
        @Override
        public ListCommand create(CommandContext context) {
            return new ListCommand(context.getUIHandler(), context.getTaskList());
        }
    },
    mark("mark") {
        @Override
        public Command create(CommandContext context) {
            return new MarkCommand(context.getUIHandler(), context.getTaskList(), context.getArgs());
        }
    },
    unmark("unmark") {
        @Override
        public Command create(CommandContext context) {
            return new UnmarkCommand(context.getUIHandler(), context.getTaskList(), context.getArgs());
        }
    },
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