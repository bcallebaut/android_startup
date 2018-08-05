package be.belgiplast.dashboard;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import be.belgiplast.library.properties.PropertyDialogs;
import be.belgiplast.library.tasks.AbstractTask;
import be.belgiplast.library.tasks.Task;

public class EditTaskView extends RelativeLayout {

    private AbstractTask task;

    public EditTaskView(Context context) {
        this(context,null);
    }

    public EditTaskView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EditTaskView(final Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
//        init(attrs, defStyle);
        setGravity(Gravity.TOP);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.activity_edit_task, this, true);

        EditText nameEdit = (EditText)v.findViewById(R.id.nameEdit);
        nameEdit.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (task != null)
                    task.setName(editable.toString());
            }
        });

        RecyclerView rv = (RecyclerView)v.findViewById(R.id.rv_tasks_props_view);
        final String[] names = new String[]{"name", "date"};
        nameEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                PropertyDialogs.showPropertyNameDialog((Activity) context, names, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context,"Test successful " + names[i],Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    public AbstractTask getTask() {
        return task;
    }

    public void setTask(AbstractTask task) {
        this.task = task;
    }
}
