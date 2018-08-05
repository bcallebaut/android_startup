package be.belgiplast.notes;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import be.belgiplast.library.ResourceAction;

public class NotesAction extends ResourceAction{
    public NotesAction(String text, int resource) {
        super("Notes", resource);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(),NotesActivity.class);
        ((Activity)v.getContext()).startActivity(intent);
    }
}
