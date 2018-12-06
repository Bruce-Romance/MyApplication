package activity.customView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.umeng.analytics.MobclickAgent;

import yomix.yt.com.myapplication.R;

public class CustomActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("CustomActivity");
        MobclickAgent.onResume(this);
    }
    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("CustomActivity");
        MobclickAgent.onPause(this);
    }

}
