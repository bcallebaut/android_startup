package be.belgiplast.library.tasks;

import android.databinding.Observable;
import android.databinding.ObservableInt;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskList {
    private long id = 0;
    private Map<Long,WeakReference<Task>> taskMap = new HashMap();
    private List<Task> tasks = new ArrayList<>();
    private List<Task> discardedTasks = new ArrayList<>();
    private ObservableInt ongoingIndex = new ObservableInt(0);
    private ObservableInt finishedIndex = new ObservableInt(0);

    private TaskComparator cmp = new TaskComparator();

    /** Adds the task at the corerct location in the list.
     *
     */
    public long addTask(Task t){
        taskMap.put(id,new WeakReference(t));
        id++;
        if (t.getState() == TaskStates.BACKLOG){
            int i = 0;
            for ( ; (i < ongoingIndex.get()) && cmp.compare(t,tasks.get(i)) < 0; i++);
            if (i == ongoingIndex.get()) {
                finishedIndex.set(finishedIndex.get() + 1);
                ongoingIndex.set(ongoingIndex.get() + 1);
            }
            tasks.add(i,t);
        }else if (t.getState() == TaskStates.ONGOING){
            int i = ongoingIndex.get();
            for ( ; (i < finishedIndex.get()) && cmp.compare(t,tasks.get(i)) < 0; i++);
            if (i == finishedIndex.get())
                finishedIndex.set(finishedIndex.get() + 1);
            tasks.add(i,t);
        } else{
            int i = finishedIndex.get();
            for ( ; (i < tasks.size()) && cmp.compare(t,tasks.get(i)) < 0; i++);
            if (i>=tasks.size())
                tasks.add(t);
            else
                tasks.add(i,t);
        }
        return id - 1;
    }

    public Task getTaskById(long id){
        return taskMap.get(id).get();
    }

    public void discard(Task t){
        int ndx = tasks.indexOf(t);
        discard(ndx);
    }

    public void discard(int ndx) {
        if (ndx <= -1)
            return;

        if (ndx < finishedIndex.get()){
            finishedIndex.set(finishedIndex.get() - 1);
        }
        if (ndx < ongoingIndex.get()){
            ongoingIndex.set(ongoingIndex.get() - 1);
        }
        tasks.remove(ndx);
    }

    public RecyclerDataAccess<Task> getBacklogList(){
        return new RecyclerDataAccessImpl(0,ongoingIndex);
    }

    public RecyclerDataAccess<Task> getOngoingList(){
        return new RecyclerDataAccessImpl(ongoingIndex,finishedIndex);
    }

    public RecyclerDataAccess<Task> getFinishedList(){
        return new RecyclerDataAccessImpl(finishedIndex);
    }

    private class RecyclerDataAccessImpl implements RecyclerDataAccess<Task> {
        private int count;
        private int offset;
        public RecyclerDataAccessImpl(final ObservableInt ongoingIndex, final ObservableInt p1) {
            count = p1.get() - ongoingIndex.get();
            offset = ongoingIndex.get();
            ongoingIndex.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                @Override
                public void onPropertyChanged(Observable sender, int propertyId) {
                    count = p1.get() - ongoingIndex.get();
                    offset = ongoingIndex.get();
                }
            });
            p1.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                @Override
                public void onPropertyChanged(Observable sender, int propertyId) {
                    count = p1.get() - ongoingIndex.get();
                    offset = ongoingIndex.get();
                }
            });
        }

        public RecyclerDataAccessImpl(int ofs,final ObservableInt ongoingIndex) {
            count = ongoingIndex.get();
            this.offset = ofs;
            ongoingIndex.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                @Override
                public void onPropertyChanged(Observable sender, int propertyId) {
                    count = ongoingIndex.get();
                }
            });
        }

        public RecyclerDataAccessImpl(final ObservableInt ongoingIndex) {
            count = tasks.size() - ongoingIndex.get();
            offset = ongoingIndex.get();
            ongoingIndex.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
                @Override
                public void onPropertyChanged(Observable sender, int propertyId) {
                    count = tasks.size() - ongoingIndex.get();
                    offset = ongoingIndex.get();
                }
            });
        }

        @Override
        public int getCount() {
            return count;
        }

        @Override
        public Task getItem(int count) {
            return tasks.get(offset + count);
        }

        @Override
        public void remove(int position) {
            discard(offset + count);
        }
    }
}
