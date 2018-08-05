package be.belgiplast.library.properties;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import be.belgiplast.library.Action;
import be.belgiplast.library.R;

final class PropertyNameRecyclerViewAdapter extends RecyclerView.Adapter<PropertyNameRecyclerViewAdapter.ViewHolder> {

    private PropertyNameSource mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    PropertyNameRecyclerViewAdapter(Context context, PropertyNameSource source) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = source;
    }

    // inflates the cell layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.object_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.myTextView.setText(mData.getName(position));
    }

    public void setData(PropertyNameSource source) {
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

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = (Button) itemView.findViewById(R.id.property_item);
            myTextView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, mData.getName(getAdapterPosition()));
        }
    }

    // convenience method for getting data at click position
    public String getItem(int id) {
        return mData.getName(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, String name);
    }
}
