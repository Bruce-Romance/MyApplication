package activity.customView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import yomix.yt.com.myapplication.R;

public class CustomActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
    }
}
