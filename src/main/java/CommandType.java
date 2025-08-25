import java.util.Arrays;
import java.util.Set;

public enum CommandType {
    greet("hello"),
    echo("echo"),
    help("help"),
    list("list"),
    add("add"),
    remove("remove"),
    mark("mark"),
    unmark("unmark"),
    farewell("bye"),
    invalid;

    private final Set<String> alias;

    CommandType(String... commands) {
        this.alias = Set.of(commands);
    }

    public int getArgCount() {
        return argCount;
    }

    public static CommandType interpretCommand(String command) {
        if (command == null || command.trim().isEmpty()) {
            return invalid;
        }

        String cleanCommand = command.toLowerCase().trim();

        return Arrays.stream(values())
                .filter(cmd -> cmd.alias.contains(cleanCommand))
                .findFirst()
                .orElse(invalid);
    }
}