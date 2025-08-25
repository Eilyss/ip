package com.Elsria;

public abstract class Command {
    protected ChatbotContext chatbotContext;

    public Command(ChatbotContext chatbotContext, String args) {
        this.ui = ui;
    }

    public abstract void execute();
    public abstract void parseArgs(String args);

    public class EchoCommand extends Command {
        private String echo;

        public EchoCommand(ChatbotContext chatbotContext, String args) {
            super(chatbotContext, args);
            parseArgs(args);
        }

        @Override
        public void execute() {
            super.chatbotContext.getUIHandler().say(this.echo);
        }

        @Override
        public void parseArgs(String args) {
            this.echo = args;
        }
    }

    public class ListCommand extends Command {

        public ListCommand(ChatbotContext chatbotContext, String args) {
            super(chatbotContext, args);
        }

        @Override
        public void execute() {
            TaskList taskList = chatbotContext.getTaskList();
            UIHandler uiHandler = chatbotContext.getUIHandler();

            if (taskList.isEmpty()) {
                uiHandler.say("Hmm... there's nothing in your list right now.");
                return;
            }

            uiHandler.queueMessage("Here are the tasks on your list:");
            for (int i = 0; i < taskList.size(); i++) {
                uiHandler.queueMessage(String.format("%d. %s", i + 1, taskList.get(i).toString()));
            }
            uiHandler.sayMessages();
        }

        @Override
        public void parseArgs(String args) {

        }
    }
}
