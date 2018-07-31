package be.belgiplast.dashboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import be.belgiplast.library.tasks.AbstractTask;

public class NewTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        final AbstractTask task = new AbstractTask();
        ((DashboardApplication)getApplicationContext()).getCoach().addTask(task);


    }
}
