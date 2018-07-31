package be.belgiplast.dashboard;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import be.belgiplast.library.Action;
import be.belgiplast.library.ResourceAction;
import be.belgiplast.library.tasks.Task;
import be.belgiplast.dashboard.tasks.EditTaskActivity;
import be.belgiplast.library.tasks.TasksView;

public class CoachMainActivity extends FragmentActivity {
    DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;
    ViewPager mViewPager;

    private Action newAction;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_demo);

        // ViewPager and its adapters use support library
        // fragments, so use getSupportFragmentManager.
        mDemoCollectionPagerAdapter =
                new DemoCollectionPagerAdapter(
                        getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mDemoCollectionPagerAdapter);

        newAction = new ResourceAction("new Task", -1) {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CoachMainActivity.this, NewTaskActivity.class);
                CoachMainActivity.this.startActivity(intent);
            }
        };

        mDemoCollectionPagerAdapter.setNewAction(newAction);


        /*
        setContentView(R.layout.activity_coach_main);

        popupWindows = new PopupWindow[3];
        popupViews = new View[3];

        relativeLayout = (ViewGroup) findViewById(R.id.coach_insert);
        backlog = (TasksView) findViewById(R.id.backlog);
        ongoing = (TasksView) findViewById(R.id.ongoing);
        finished = (TasksView) findViewById(R.id.finished);

        LayoutInflater layoutInflater1 = (LayoutInflater) backlog.getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        popupViews[0] = layoutInflater1.inflate(R.layout.task_popup, null);
        popupWindows[0] = new PopupWindow(popupViews[0], RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        popupWindows[0].setOutsideTouchable(true);

        LayoutInflater layoutInflater2 = (LayoutInflater) ongoing.getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        popupViews[1] = layoutInflater2.inflate(R.layout.task_popup_ongoing, null);
        popupWindows[1] = new PopupWindow(popupViews[1], RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        popupWindows[1].setOutsideTouchable(true);



        popupWindows[1].setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindows[1].dismiss();
                return true;
            }
        });

        backlog.setTaskListener(new TasksView.OnTaskSelectionListener() {
            @Override
            public void taskSelected(Task task) {
                int[] loc = new int[2];
                backlog.getLocationOnScreen(loc);

                popupWindows[0].showAtLocation(relativeLayout, Gravity.NO_GRAVITY, loc[0], loc[1]);
            }
        });
        ongoing.setTaskListener(new TasksView.OnTaskSelectionListener() {
            @Override
            public void taskSelected(Task task) {
                int[] loc = new int[2];
                ongoing.getLocationOnScreen(loc);

                popupWindows[1].showAtLocation(relativeLayout, Gravity.NO_GRAVITY, loc[0], loc[1]);
            }
        });
        Button btnEdit = (Button)popupViews[0].findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindows[0].dismiss();
                Intent intent = new Intent(CoachMainActivity.this, EditTaskActivity.class);
                intent.putExtra("taskId","blah"); //TODO :replace by actual task name
                CoachMainActivity.this.startActivity(intent);
            }
        });
        Button btnPromote= (Button)popupViews[0].findViewById(R.id.btnPromote);
        btnPromote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindows[0].dismiss();
                Intent intent = new Intent(CoachMainActivity.this, CollectionDemoActivity.class);
                //intent.putExtra("taskId","blah"); //TODO :replace by actual task name
                CoachMainActivity.this.startActivity(intent);
            }
        });
        */
    }


/*

PopupWindow popupWindow;
    View popupView;
    int mCurrentX,mCurrentY;
    RelativeLayout relativeLayout;
    //mData[getAdapterPosition()].onClick(view);
    LayoutInflater layoutInflater = (LayoutInflater)view.getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
    popupView = layoutInflater.inflate(R.layout.task_popup,null);

    popupWindow = new PopupWindow(popupView, RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
    Button btnPromote = (Button)popupView.findViewById(R.id.btnPromote);
            btnPromote.setEnabled(task.canPromote());
    Button btnDiscard = (Button)popupView.findViewById(R.id.btnDiscard);
    Button btnEdit = (Button)popupView.findViewById(R.id.btnEdit);

            btnPromote.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            task.promote();
        }
    });

            btnDiscard.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mData.remove(mData.indexOf(task));
        }
    });

            btnEdit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(mInflater.getContext(), EditTaskActivity.class);
            intent.putExtra("taskId",task.getName());
            mInflater.getContext().startActivity(intent);
        }
    });


    mCurrentX = 20;
    mCurrentY = 50;

            popupWindow.showAtLocation(relativeLayout, Gravity.NO_GRAVITY, mCurrentX, mCurrentY);



            popupView.setOnTouchListener(new View.OnTouchListener() {
        private float mDx;
        private float mDy;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int action = event.getAction();
            if (action == MotionEvent.ACTION_DOWN) {
                mDx = mCurrentX - event.getRawX();
                mDy = mCurrentY - event.getRawY();
            } else
            if (action == MotionEvent.ACTION_MOVE) {
                mCurrentX = (int) (event.getRawX() + mDx);
                mCurrentY = (int) (event.getRawY() + mDy);
                popupWindow.update(mCurrentX, mCurrentY, -1, -1);
            }
            return true;
        }
    });
}
*/
}
