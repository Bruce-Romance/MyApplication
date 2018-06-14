package tao.pangu;

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
     * 普通跳转
     *
     * @param activity
     * @param cls
     */
    public static void skipIntent(Activity activity, Class<?> cls, boolean isFinishSelf) {
        intent.setClass(activity, cls);
        activity.startActivity(intent);
        if (isFinishSelf) {
            activity.finish();
        }
    }

    /**
     * 普通跳转(ForResult)
     *
     * @param activity
     * @param cls
     * @param isFinishSelf
     * @param RequestCode
     */
    public static void skipIntentForResult(Activity activity, Class<?> cls, boolean isFinishSelf, int RequestCode) {
        intent.setClass(activity, cls);
        activity.startActivityForResult(intent, RequestCode);
        if (isFinishSelf) {
            activity.finish();
        }
    }

    /**
     * 带参数跳转
     *
     * @param activity
     * @param cls
     * @param bundle
     */
    public static void skipDataIntent(Activity activity, Class<?> cls, boolean isFinishSelf, Bundle bundle) {
        intent.setClass(activity, cls);
        intent.putExtras(bundle);
        activity.startActivity(intent);
        if (isFinishSelf) {
            activity.finish();
        }
    }

    /**
     * 带参数跳转(ForResult)
     *
     * @param activity
     * @param cls
     * @param isFinishSelf
     * @param bundle
     * @param RequestCode
     */
    public static void skipDataIntentForResult(Activity activity, Class<?> cls, boolean isFinishSelf, Bundle bundle, int RequestCode) {
        intent.setClass(activity, cls);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent, RequestCode);
        if (isFinishSelf) {
            activity.finish();
        }
    }
}
