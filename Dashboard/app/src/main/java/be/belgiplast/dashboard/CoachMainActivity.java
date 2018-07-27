package be.belgiplast.dashboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import be.belgiplast.dashboard.coach.Task;
import be.belgiplast.dashboard.coach.TaskView;

public class CoachMainActivity extends AppCompatActivity {

    private ViewGroup relativeLayout;
    private TaskView backlog;
    private TaskView ongoing;
    private TaskView finished;
    private int mCurrentX, mCurrentY;

    PopupWindow[] popupWindows;
    View[] popupViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_main);

        popupWindows = new PopupWindow[3];
        popupViews = new View[3];

        relativeLayout = (ViewGroup) findViewById(R.id.coach_insert);
        backlog = (TaskView) findViewById(R.id.backlog);
        ongoing = (TaskView) findViewById(R.id.ongoing);
        finished = (TaskView) findViewById(R.id.finished);

        LayoutInflater layoutInflater1 = (LayoutInflater) backlog.getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        popupViews[0] = layoutInflater1.inflate(R.layout.task_popup, null);
        popupWindows[0] = new PopupWindow(popupViews[0], RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        popupWindows[0].setOutsideTouchable(true);

        LayoutInflater layoutInflater2 = (LayoutInflater) ongoing.getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        popupViews[1] = layoutInflater2.inflate(R.layout.task_popup_ongoing, null);
        popupWindows[1] = new PopupWindow(popupViews[1], RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        popupWindows[1].setOutsideTouchable(true);

        popupWindows[0].setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindows[0].dismiss();
                return true;
            }
        });
        popupWindows[1].setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindows[1].dismiss();
                return true;
            }
        });

        backlog.setTaskListener(new TaskView.OnTaskSelectionListener() {
            @Override
            public void taskSelected(Task task) {
                int[] loc = new int[2];
                backlog.getLocationOnScreen(loc);

                popupWindows[0].showAtLocation(relativeLayout, Gravity.NO_GRAVITY, loc[0], loc[1]);
            }
        });
        ongoing.setTaskListener(new TaskView.OnTaskSelectionListener() {
            @Override
            public void taskSelected(Task task) {
                int[] loc = new int[2];
                ongoing.getLocationOnScreen(loc);

                popupWindows[1].showAtLocation(relativeLayout, Gravity.NO_GRAVITY, loc[0], loc[1]);
            }
        });
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
