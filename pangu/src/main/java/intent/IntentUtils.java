package intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * @author YT
 * @date 2018/2/5
 */

public class IntentUtils {

    private static Intent intent = new Intent();

    /**
     * 不带参数跳转
     *
     * @param activity     上下文
     * @param cls          跳转到哪里
     * @param isFinishSelf 是否结束当前Activity
     * @param requestCode  请求Code
     */
    public static void skipIntent(Activity activity, Class<?> cls, boolean isFinishSelf, int requestCode) {
        intent.setClass(activity, cls);
        activity.startActivityForResult(intent, requestCode);
        if (isFinishSelf) {
            activity.finish();
        }
    }

    /**
     * 带参数跳转
     *
     * @param activity     上下文
     * @param cls          跳转到哪里
     * @param isFinishSelf 是否结束当前Activity
     * @param bundle       传递对象
     * @param requestCode  请求Code
     */
    public static void skipDataIntent(Activity activity, Class<?> cls, boolean isFinishSelf, Bundle bundle, int requestCode) {
        intent.setClass(activity, cls);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent, requestCode);
        if (isFinishSelf) {
            activity.finish();
        }
    }
}
