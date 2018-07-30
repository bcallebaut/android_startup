package be.belgiplast.library.tasks;

import java.util.Comparator;

public final class TaskStateComparator implements Comparator<TaskStates> {

    @Override
    public int compare(TaskStates taskStates, TaskStates t1) {
        return taskStates.ordinal() - t1.ordinal();
    }
}
