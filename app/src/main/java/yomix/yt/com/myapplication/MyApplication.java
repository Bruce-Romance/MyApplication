package yomix.yt.com.myapplication;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.lemap.app.LemapApplication;
import com.lemap.app.MessageDialog;
import com.lemap.core.LemapContext;
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

public class MyApplication extends LemapApplication {


    @Override
    public void onCreate() {
        super.onCreate();
        RxTool.init(this);
        PanGu.getInstance().init(this);
        LogUtils.level = 1;
//        Dao.getInstance().getContext(this);
//        ViewTarget.setTagId(R.id.imageloader_uri);
        LemapContext.getSingleDefault().AppName = this.getResources().getString(R.string.appName);
        LemapContext.getSingleDefault().DBVersion = this.getResources().getInteger(R.integer.dbVersion);
        LemapContext.getSingleDefault().CreateTableSql = this.getResources().getStringArray(R.array.createTableSql);
        LemapContext.getSingleDefault().UpgradeTableSql = this.getResources().getStringArray(R.array.upgradeTableSql);
        LemapContext.getSingleDefault().init(getApplicationContext(), this);
        //初始化数据库
        LemapContext.getSingleDefault().initDataBase();
        SQLiteDatabase database = LemapContext.getSingleDefault().getWritableDatabase();
    }
}
