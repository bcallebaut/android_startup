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

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.task_fragment_edit_task, container, false);
        Bundle args = getArguments();

        return rootView;
    }

}
