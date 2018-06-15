package yomix.yt.com.myapplication;

import android.app.Activity;
import android.os.Bundle;


import intent.IntentUtils;

public class LaunchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        IntentUtils.skipIntent(LaunchActivity.this, MainActivity.class, true);
    }
}
