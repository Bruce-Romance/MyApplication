package act;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity管理类
 * 在封装Activity的OnCreate Add当前Activity
 * 或者在需要管理的Activity的OnCreate中Add
 *
 * Created by yangzw on 2018/8/24 下午2:41.
 */

public class ActManager {

    public static ActManager mActManager;
    /**
     * 存放Activity的map
     */
    private List<Activity> mActivities = new ArrayList<Activity>();

    //将构造方法私有化，所以不能通构造方法来初始化ActivityManager
    private ActManager() {

    }

    //单例ActivityManager
    public static ActManager getInstance() {
        if (mActManager == null) {
            mActManager = new ActManager();
        }
        return mActManager;
    }

    //添加activity
    public void addActivity(Activity activity) {
        if (!mActivities.contains(activity)) {
            mActivities.add(activity);
        }
    }

    //关闭指定的Activity
    public void removeActivity(Activity activity) {
        if (activity != null) {
            if (mActivities.contains(activity)) {
                mActivities.remove(activity);
            }
            activity.finish();
            activity = null;
        }
    }

    //将activity全部关闭掉
    public void clearAll() {
        for (Activity activity : mActivities) {
            activity.finish();
        }
    }

    //将activity全部关闭掉,LoginActivity
    public void clearAllExceptLogin() {

        for (Activity activity : mActivities) {
            Log.e("activityManager", activity.getClass().getSimpleName());

            if (activity.getClass().getSimpleName().equals("LoginActivity")) {

                continue;
            }
            activity.finish();
        }
    }
}
