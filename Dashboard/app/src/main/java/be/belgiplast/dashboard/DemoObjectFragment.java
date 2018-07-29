package be.belgiplast.dashboard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DemoObjectFragment extends NamedFragment {
    public static final String ARG_OBJECT = "object";

    public DemoObjectFragment() {
        super("Demo");
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.fragment_collection_object, container, false);
        Bundle args = getArguments();
        TextView tv = ((TextView) rootView.findViewById(R.id.demotext_1));
        String arg = "demo";
        try{
            arg = Integer.toString(args.getInt(ARG_OBJECT));
        }catch (Exception e){
            e.printStackTrace();
        }
        tv.setText(
                arg);
        return rootView;
    }

}
