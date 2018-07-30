package be.belgiplast.library.tasks;

import java.util.ArrayList;
import java.util.List;

public class ListRDA<T> implements RecyclerDataAccess<T> {
    private List<T> backend;

    public ListRDA(List<T> backend) {
        this.backend = backend;
    }

    public ListRDA(T ...backend) {
        this.backend = new ArrayList<>();
        for (T t : backend)
            this.backend.add(t);
    }

    @Override
    public int getCount() {
        return backend.size();
    }

    @Override
    public T getItem(int count) {
        return backend.get(count);
    }

    @Override
    public void remove(int position) {
        backend.remove(position);
    }
}
