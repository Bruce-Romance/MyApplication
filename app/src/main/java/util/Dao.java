package util;

import android.content.Context;
import org.greenrobot.greendao.database.Database;
import Bean.DaoMaster;
import Bean.DaoSession;

public class Dao {
    private DaoSession daoSession;
    private static Dao dao = new Dao();

    public static Dao getInstance() {
        return dao;
    }

    /**
     * 初始化GreenDao
     *
     * @param context
     */
    public void getContext(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "myApplication-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    /**
     * 对实体进行操作
     *
     * @return
     */
    public DaoSession getDaoSession() {
        return daoSession;
    }

}
