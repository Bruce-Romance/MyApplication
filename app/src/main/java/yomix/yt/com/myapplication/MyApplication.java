package yomix.yt.com.myapplication;

import android.app.Application;
import com.vondear.rxtools.RxTool;
import tao.pangu.LogUtils;
import tao.pangu.PanGu;

/**
 * @author YT
 * @date 2018/2/9
 */

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        RxTool.init(this);
        PanGu.getInstance().init(this);
        LogUtils.level = 1;
//        ViewTarget.setTagId(R.id.imageloader_uri);
    }
}
