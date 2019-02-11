package activity.customView;

import android.os.Bundle;

import com.umeng.analytics.MobclickAgent;

import act.PanGuActivity;
import task.MessageInfo;
import task.MyAsyncTask;
import toast.ToastUtils;
import yomix.yt.com.myapplication.R;

public class CustomActivity extends PanGuActivity {

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
