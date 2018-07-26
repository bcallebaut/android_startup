package be.belgiplast.dashboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import be.belgiplast.dashboard.dynamicMenu.DynamicMenu;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        RelativeLayout tv = (RelativeLayout) findViewById(R.id.insert);
        DynamicMenu mnu = new DynamicMenu(this);
        mnu.setData(new Action[]{new ResourceAction("Coach",R.drawable.coach){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CoachMainActivity.class);
                MainActivity.this.startActivity(intent);
            }
        },new ResourceAction("B",R.drawable.advisor)});
        tv.addView(mnu);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    //public native String stringFromJNI();
}
