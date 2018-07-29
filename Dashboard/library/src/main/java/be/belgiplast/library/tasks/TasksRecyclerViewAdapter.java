package be.belgiplast.library.tasks;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import be.belgiplast.library.R;

class TasksRecyclerViewAdapter extends RecyclerView.Adapter<TasksRecyclerViewAdapter.ViewHolder> {

    private RecyclerDataAccess<Task> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    TasksRecyclerViewAdapter(Context context, RecyclerDataAccess data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the cell layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.menu_item, parent, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new ViewHolder(view);
    }

    // binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setTask(mData.getItem(position));
    }

    public void setData(RecyclerDataAccess<Task> mData) {
        this.mData = mData;
        super.notifyDataSetChanged();
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.getCount();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button myTextView;
        Task task;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = (Button) itemView.findViewById(R.id.button);
            myTextView.setOnClickListener(this);
        }

        public void setTask(Task task) {
            this.task = task;
            myTextView.setText(task.getName());
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null)
                mClickListener.onItemClick(view, mData.getItem(getAdapterPosition()));
        }
    }

    // convenience method for getting data at click position
    Task getItem(int id) {
        return mData.getItem(id);
    }

    // allows clicks events to be caught
    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, Task task);
    }
}
