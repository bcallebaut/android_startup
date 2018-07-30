package be.belgiplast.library.tasks;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import be.belgiplast.library.R;


/**
 * TODO: document your custom view class.
 */
public class TasksView extends RelativeLayout {
    private String tasksName; // TODO: use a default from R.string...
    private int mExampleColor = Color.RED; // TODO: use a default from R.color...
    private float mExampleDimension = 0; // TODO: use a default from R.dimen...
    private Drawable mExampleDrawable;

    private TextPaint mTextPaint;
    private float mTextWidth;
    private float mTextHeight;

    private RecyclerView recyclerView;
    private TasksRecyclerViewAdapter adapter;
    private OnTaskSelectionListener mListener;

    public TasksView(Context context) {
        this(context,null);
    }

    public TasksView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TasksView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
        setGravity(Gravity.TOP);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.sample_task_view, this, true);

        TextView text = (TextView)findViewById(R.id.taskstitle);
        text.setText(tasksName);

        try {
            recyclerView = (RecyclerView) findViewById(R.id.rv_tasks_view);
            //RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            //recyclerView.setLayoutParams(lp);
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(context);
            mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(mLayoutManager);
            ListRDA<Task> tasks = new ListRDA<Task>(new Task(){

                @Override
                public String getName() {
                    return "blah";
                }

                @Override
                public String getDescription() {
                    return "blah";
                }

                @Override
                public Date getStartDate() {
                    return new Date();
                }

                @Override
                public Date getFinishDate() {
                    return new Date();
                }

                @Override
                public TaskStates getState() {
                    return TaskStates.BACKLOG;
                }

                @Override
                public void promote() {

                }

                @Override
                public boolean canPromote() {
                    return false;
                }

                @Override
                public int getSize() {
                    return 0;
                }
            });
            adapter = new TasksRecyclerViewAdapter(context, tasks);
            recyclerView.setAdapter(adapter);
            this.setOnTouchListener(new OnTouchListener(){

                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (mListener != null)
                        mListener.notaskSelected();
                    return true;
                }
            });
            adapter.setItemClickListener(new TasksRecyclerViewAdapter.ItemClickListener() {
                @Override
                public void onItemClick(View view, Task task) {
                    if (mListener != null)
                        mListener.taskSelected(task);
                }
            });
        }catch (ClassCastException e){
            e.printStackTrace();
        }
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.TasksView, defStyle, 0);

        tasksName = a.getString(
                R.styleable.TasksView_tasksName);

        mExampleColor = a.getColor(
                R.styleable.TasksView_taskColor,
                mExampleColor);
        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
        // values that should fall on pixel boundaries.
        mExampleDimension = a.getDimension(
                R.styleable.TasksView_taskDimension,
                mExampleDimension);

        if (a.hasValue(R.styleable.TasksView_taskDrawable)) {
            mExampleDrawable = a.getDrawable(
                    R.styleable.TasksView_taskDrawable);
            mExampleDrawable.setCallback(this);
        }

        a.recycle();


    }

    private void invalidateTextPaintAndMeasurements() {
        mTextPaint.setTextSize(mExampleDimension);
        mTextPaint.setColor(mExampleColor);
        mTextWidth = mTextPaint.measureText(tasksName);

        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        mTextHeight = fontMetrics.bottom;
    }

    public OnTaskSelectionListener getTaskListener() {
        return mListener;
    }

    public void setTaskListener(OnTaskSelectionListener mListener) {
        this.mListener = mListener;
    }

    public void setData(RecyclerDataAccess<Task> mData) {
        adapter.setData(mData);
    }

    public interface OnTaskSelectionListener{
        void taskSelected(Task task);
        void notaskSelected();
    }
}
