package yomix.yt.com.myapplication;

import android.app.Application;

import com.vondear.rxtools.RxTool;

import org.greenrobot.greendao.database.Database;

import Bean.DaoMaster;
import log.LogUtils;
import tao.pangu.PanGu;
import util.Dao;

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
        Dao.getInstance().getContext(this);
//        ViewTarget.setTagId(R.id.imageloader_uri);
    }
}
