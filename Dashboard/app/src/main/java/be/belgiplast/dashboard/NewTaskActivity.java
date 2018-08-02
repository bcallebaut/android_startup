package be.belgiplast.dashboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import be.belgiplast.library.tasks.AbstractTask;

public class NewTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        final AbstractTask task = new AbstractTask();
        ((EditTaskView)findViewById(R.id.task_editor)).setTask(task);

        Button create = (Button)findViewById(R.id.button5);
        create.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                ((DashboardApplication)getApplicationContext()).getCoach().addTask(task);
            }
        });
    }
}
