package be.belgiplast.library;

import android.graphics.drawable.Drawable;
import android.view.View;

public interface Action {
    String getText();
    int getImageResource();
    Drawable getImage();
    void onClick(View v);
}
