package activity;

import android.app.Application;
import android.content.Context;

import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.vondear.rxtools.RxTool;

import org.greenrobot.greendao.database.Database;

import bean.DaoMaster;
import bean.DaoSession;
import log.LogUtils;
import tao.pangu.PanGu;

/**
 * @author YT
 * @date 2018/2/9
 */

public class MyApplication extends Application {

    private static Context mContext;

    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        RxTool.init(this);
        PanGu.getInstance().init(this);
        LogUtils.level = 1;

        DaoMaster.DevOpenHelper helper = new  DaoMaster.DevOpenHelper(this, "database");
        Database db =  helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();

//        ViewTarget.setTagId(R.id.imageloader_uri);

        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "5bfcb716f1f5565a93000933");
        //打开友盟统计SDK调试模式
        UMConfigure.setLogEnabled(true);
        //设置场景类型
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
        //手动统计
        MobclickAgent.openActivityDurationTrack(false);
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }

    public static Context getApplication() {
        return mContext;
    }
}
