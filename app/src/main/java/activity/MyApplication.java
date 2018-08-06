package activity;

import android.app.Application;

import com.vondear.rxtools.RxTool;

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
//        LemapContext.getSingleDefault().AppName = this.getResources().getString(R.string.appName);
//        LemapContext.getSingleDefault().DBVersion = this.getResources().getInteger(R.integer.dbVersion);
//        LemapContext.getSingleDefault().CreateTableSql = this.getResources().getStringArray(R.array.createTableSql);
//        LemapContext.getSingleDefault().UpgradeTableSql = this.getResources().getStringArray(R.array.upgradeTableSql);
//        LemapContext.getSingleDefault().init(getApplicationContext(), this);
//        //初始化数据库
//        LemapContext.getSingleDefault().initDataBase();
//        SQLiteDatabase database = LemapContext.getSingleDefault().getWritableDatabase();
    }
}
