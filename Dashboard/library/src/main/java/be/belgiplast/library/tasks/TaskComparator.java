package be.belgiplast.library.tasks;

import java.util.Comparator;

public class TaskComparator implements Comparator<Task> {
   private TaskStateComparator cmp = new TaskStateComparator();

    @Override
    public int compare(Task task, Task t1) {
        int result = cmp.compare(task.getState(),t1.getState());
        if (result != 0)
            return result;
        if (task.getState().equals(TaskStates.FINISHED)){
            long spend1 = task.getFinishDate().getTime() - task.getStartDate().getTime();
            long spend2 = t1.getFinishDate().getTime() - t1.getStartDate().getTime();
            return (int)(spend1 - spend2);
        }
        return task.getSize() - t1.getSize();
    }
}
