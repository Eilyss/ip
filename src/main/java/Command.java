public abstract class Command {
    private ChatbotUI ui;

    public Command(ChatbotUI ui, String args) {
        this.ui = ui;
    }

    public abstract void execute();
    public abstract void parseArgs(String args);

    public class GreetCommand extends Command {
        private String name;

        public GreetCommand(ChatbotUI ui, String name) {
            super(ui, name);
            parseArgs(name);
        }

        @Override
        public void execute() {
            super.ui.queueMessage(String.format("Heya! It's me, %s!", this.name));
            super.ui.queueMessage("What do you wanna do today?");
            super.ui.sayMessages();
        }

        @Override
        public void parseArgs(String args) {
            this.name = args;
        }
    }

    public class EchoCommand extends Command {
        private String echo;

        public EchoCommand(ChatbotUI ui, String args) {
            super(ui, args);
            parseArgs(args);
        }

        @Override
        public void execute() {
            super.ui.say(this.echo);
        }

        @Override
        public void parseArgs(String args) {
            this.echo = args;
        }
    }
}
