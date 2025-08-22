public class Task {
    protected String task;
    protected boolean isDone;

    public Task(String description) {
        this.task = description;
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getTask() {
        return this.task;
    }

    public String getMark() {
        return this.isDone ? "X" : " ";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getMark(), task);
    }
}
