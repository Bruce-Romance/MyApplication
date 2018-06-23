package Thread;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadUtils {
    /**
     * 全局线程池
     */
    private static ExecutorService executorService;

    /**
     * 全局异步执行
     */
    private static Handler sHandler;

    /**
     * @return
     */
    public static ExecutorService getExecutorService() {
        synchronized (ThreadUtils.class) {
            if (executorService == null) {
                executorService = Executors.newCachedThreadPool();
            }
            return executorService;
        }
    }

    /**
     * @return
     */
    public static Handler getMainHandler() {
        synchronized (ThreadUtils.class) {
            if (sHandler == null) {
                sHandler = new InternalHandler();
            }
            return sHandler;
        }
    }


    private static class InternalHandler extends Handler {
        public InternalHandler() {
            super(Looper.getMainLooper());
        }

        @Override
        public void handleMessage(Message msg) {

        }
    }
}
