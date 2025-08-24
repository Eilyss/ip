import java.util.Arrays;
import java.util.Set;

public enum Command {
    greet("hello"),
    help("help"),
    list("list"),
    add("add"),
    remove("remove"),
    mark("mark"),
    unmark("unmark"),
    farewell("bye"),
    invalid;

    private final Set<String> alias;

    Command(String... commands) {
        this.alias = Set.of(commands);
    }

    public static Command interpretCommand(String command) {
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