package act;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import intent.IntentUtils;
import log.LogUtils;
import toast.ToastUtils;

/**
 * Created by yangzw on 2018/10/11 下午1:38.
 */

public abstract class PanGuActivity extends AppCompatActivity {

    private String TAG;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        //添加到管理类
        ActManager.getInstance().addActivity(this);
        TAG = this.getLocalClassName();
    }

    /**
     * 错误吐司
     *
     * @param msg
     */
    private void showErrorToast(String msg) {
        ToastUtils.error(msg);
    }

    /**
     * 普通吐司
     *
     * @param msg
     */
    private void showNormalToast(String msg) {
        ToastUtils.normal(msg);
    }

    /**
     * 成功吐司
     *
     * @param msg
     */
    private void showSuccessToast(String msg) {
        ToastUtils.success(msg);
    }

    /**
     * 警告吐司
     *
     * @param msg
     */
    private void showWarningToast(String msg) {
        ToastUtils.warning(msg);
    }

    /**
     * 带参数跳转
     *
     * @param activity
     * @param cls
     * @param isFinishSelf
     * @param bundle
     * @param requestCode
     */
    private void skipWithData(Activity activity, Class<?> cls, boolean isFinishSelf, Bundle bundle, int requestCode) {
        IntentUtils.skipDataIntent(activity, cls, isFinishSelf, bundle, requestCode);
    }

    /**
     * 无参跳转
     *
     * @param activity
     * @param cls
     * @param isFinishSelf
     * @param requestCode
     */
    private void skipIntent(Activity activity, Class<?> cls, boolean isFinishSelf, int requestCode) {
        IntentUtils.skipIntent(activity, cls, isFinishSelf, requestCode);
    }

    /**
     * 日志
     * @param msg
     */
    private void logVerbose(String msg) {
        LogUtils.v(TAG, msg);
    }

    /**
     * 日志
     * @param msg
     */
    private void logDeBug(String msg) {
        LogUtils.d(TAG, msg);
    }

    /**
     * 日志
     * @param msg
     */
    private void logInfo(String msg) {
        LogUtils.i(TAG, msg);
    }

    /**
     * 日志
     * @param msg
     */
    private void logWarning(String msg) {
        LogUtils.w(TAG, msg);
    }

    /**
     * 日志
     * @param msg
     */
    private void logError(String msg) {
        LogUtils.e(TAG, msg);
    }
}
