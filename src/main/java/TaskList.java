import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {
    public void markTask(int i) {
        super.get(i).mark();
    }

    public void unmarkTask(int i) {
        super.get(i).unmark();
    }

    public String getTaskDescription(int i) {
        return super.get(i).toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < super.size(); i++) {
            sb.append(String.format("%d. %s\n", i + 1, super.get(i)));
        }
        return sb.toString();
    }
}
