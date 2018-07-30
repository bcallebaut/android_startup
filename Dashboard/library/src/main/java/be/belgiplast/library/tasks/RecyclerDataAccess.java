package be.belgiplast.library.tasks;

public interface RecyclerDataAccess<T> {
    int getCount();
    T getItem(int count);
    void remove(int position);
}
