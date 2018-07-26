package be.belgiplast.dashboard.dynamicMenu;

import android.content.Context;
import android.graphics.drawable.DrawableWrapper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import be.belgiplast.dashboard.Action;
import be.belgiplast.dashboard.R;

class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private Action[] mData = new Action[0];
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    MyRecyclerViewAdapter(Context context, Action[] data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the cell layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.menu_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.myTextView.setText(mData[position].getText());
        if (mData[position].getImage() == null) {
            holder.myTextView.setBackground(holder.myTextView.getContext().getResources().getDrawable(mData[position].getImageResource(), holder.myTextView.getContext().getTheme()));
        }else
            holder.myTextView.setBackground(mData[position].getImage());
    }

    public void setData(Action[] mData) {
        this.mData = mData;
        super.notifyDataSetChanged();
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.length;
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = (Button) itemView.findViewById(R.id.button);
            myTextView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //if (mClickListener != null) mClickListener.onItemClick(view, mData[getAdapterPosition()]);
            mData[getAdapterPosition()].onClick(view);
        }
    }

    // convenience method for getting data at click position
    Action getItem(int id) {
        return mData[id];
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, Action action);
    }
}
