package log;

import android.util.Log;

/**
 * Created by Administrator on 2018/3/17.
 */

public class LogUtils {

    private static final int VERBOSE = 1;

    private static final int DEBUG = 2;

    private static final int INFO = 3;

    private static final int WARNING = 4;

    private static final int ERROR = 5;

    /**
     * 屏蔽所有Log打印
     * 适合线上版本
     */
    private static final int NOTHING = 6;
    /**
     * 默认level,打印所有日志
     * 适合开发阶段
     */
    public static int level = VERBOSE;


    public static void v(String tag, String msg) {
        if (level <= VERBOSE) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (level <= DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (level <= INFO) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (level <= WARNING) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (level <= ERROR) {
            Log.e(tag, msg);
        }
    }

}
