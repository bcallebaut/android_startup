package be.belgiplast.dashboard;

import be.belgiplast.library.tasks.TaskList;

public abstract class ScrumFragment<T extends TaskList> extends NamedFragment {
    private T taskList;

    protected ScrumFragment(String name) {
        super(name);
    }

    public ScrumFragment() {
    }

    public T getTaskList() {
        return taskList;
    }

    public void setTaskList(T taskList) {
        this.taskList = taskList;
    }
}
