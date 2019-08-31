package activity.customView;

import android.os.Bundle;
import android.view.View;

import com.umeng.analytics.MobclickAgent;

import act.PanGuActivity;
import customView.CustomToggleButton;
import task.MessageInfo;
import task.MyAsyncTask;
import toast.ToastUtils;
import yomix.yt.com.myapplication.R;

public class CustomActivity extends PanGuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        final CustomToggleButton button = findViewById(R.id.custom_button);
        button.setIndeterminateProgressMode(true);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (button.getProgress() == 0) {
                    button.setProgress(50);
                } else if (button.getProgress() == -1) {
                    button.setProgress(0);
                } else {
                    button.setProgress(-1);
                }
            }
        });
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
