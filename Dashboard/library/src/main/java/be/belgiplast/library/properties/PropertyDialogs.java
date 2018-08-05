package be.belgiplast.library.properties;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import be.belgiplast.library.Action;
import be.belgiplast.library.R;
import be.belgiplast.library.ResourceAction;
//import be.belgiplast.library.dynamicMenu.MyRecyclerViewAdapter;

public final class PropertyDialogs {

    private PropertyDialogs() {
    }

    public static void showPropertyNameDialog(Activity context, final String[] names, final DialogInterface.OnClickListener listener) {
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(context);
        alertDialogBuilderUserInput.setTitle("Select property name");
        alertDialogBuilderUserInput.setItems(names, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        listener.onClick(dialogInterface, i);
                    }
                }
        );
        AlertDialog dlg = alertDialogBuilderUserInput.create();
        dlg.getWindow().setBackgroundDrawableResource(R.drawable.dialog_bg);
        dlg.show();
    }

    public static void showPropertyNameDialog(Activity context, final PropertyNameSource source) {
        Boolean result = false;

        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(context.getApplicationContext());
        View view = layoutInflaterAndroid.inflate(R.layout.property_name_layout, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(context);
        alertDialogBuilderUserInput.setView(view);


        /*
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.rv_props_names_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context);
        ((LinearLayoutManager)recyclerView.getLayoutManager()).setOrientation(LinearLayoutManager.VERTICAL);
        PropertyNameRecyclerViewAdapter adapter;
        if (source == null)
            adapter = new PropertyNameRecyclerViewAdapter(context, new EmptyPropertyNameSource());
        else adapter = new PropertyNameRecyclerViewAdapter(context, source);
        recyclerView.setAdapter(adapter);

        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        result = true;
                    }
                })
                .setNegativeButton("cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

        final AlertDialog alertDialog = alertDialogBuilderUserInput.create();
        alertDialog.show();
        */
    }
}
