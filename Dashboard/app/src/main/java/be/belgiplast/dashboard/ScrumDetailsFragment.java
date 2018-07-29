package be.belgiplast.dashboard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import be.belgiplast.library.Action;
import be.belgiplast.library.tasks.Task;
import be.belgiplast.library.tasks.TaskList;
import be.belgiplast.library.tasks.TasksView;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class ScrumDetailsFragment extends ScrumFragment<TaskList> {
    public static final String ARG_OBJECT = "object";

    private ViewGroup relativeLayout;
    private TasksView backlog;
    private TasksView ongoing;
    private TasksView finished;
    private int mCurrentX, mCurrentY;

    private Action promoteAction;
    private Action discardAction;
    private Action editAction;


    PopupWindow[] popupWindows;
    View[] popupViews;

    public ScrumDetailsFragment() {
        super("Details");
    }

    public Action getPromoteAction() {
        return promoteAction;
    }

    public void setPromoteAction(Action promoteAction) {
        this.promoteAction = promoteAction;
    }

    public Action getDiscardAction() {
        return discardAction;
    }

    public void setDiscardAction(Action discardAction) {
        this.discardAction = discardAction;
    }

    public Action getEditAction() {
        return editAction;
    }

    public void setEditAction(Action editAction) {
        this.editAction = editAction;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.task_fragment_scrumboard, container, false);
        Bundle args = getArguments();

        popupWindows = new PopupWindow[3];
        popupViews = new View[3];

        relativeLayout = (ViewGroup) rootView.findViewById(R.id.coach_insert);
        backlog = (TasksView) rootView.findViewById(R.id.backlog);
        ongoing = (TasksView) rootView.findViewById(R.id.ongoing);
        finished = (TasksView) rootView.findViewById(R.id.finished);

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
                editAction.onClick(view);
            }
        });
        Button btnPromote= (Button)popupViews[0].findViewById(R.id.btnPromote);
        btnPromote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindows[0].dismiss();
                promoteAction.onClick(view);
            }
        });
        Button btnDiscard= (Button)popupViews[1].findViewById(R.id.btnDiscard);
        btnDiscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindows[1].dismiss();
                discardAction.onClick(view);
            }
        });

        return rootView;
    }

}
