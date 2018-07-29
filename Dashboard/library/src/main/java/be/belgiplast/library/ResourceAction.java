package be.belgiplast.library;


import android.graphics.drawable.Drawable;
import android.view.View;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;

public class ResourceAction implements Action {
    private String text;
    private int resource;

    public ResourceAction(String text, int resource) {
        this.text = text;
        this.resource = resource;
    }

    public String getText(){
        return text;
    }
    public int getImageResource(){
        return resource;
    }
    public Drawable getImage(){
        return null;
    }
    public void onClick(View v){
        makeText(v.getContext().getApplicationContext(),"Action selected", LENGTH_LONG).show();
    }
}
