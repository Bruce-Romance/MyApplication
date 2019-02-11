package task;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * Created by yangzw on 2019/2/11 上午11:38.
 */

public abstract class Mission {

    protected abstract Object run(MessageInfo messageInfo) throws Exception;

    protected abstract void exception(Exception e);

    protected abstract void complete(Object o);

    @SuppressLint("HandlerLeak")
    public void start() {

        final Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 2://Exception
                        Exception e = (Exception) msg.obj;
                        exception(e);
                        break;
                    case 3://Complete
                        Object o = msg.obj;
                        complete(o);
                        break;
                }
            }
        };
        final MessageInfo info = new MessageInfo();
        info.setMsg("正在执行...");

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Object o = Mission.this.run(info);
                    handler.sendMessage(handler.obtainMessage(3, o));
                } catch (Exception e) {
                    handler.sendMessage(handler.obtainMessage(2, e));
                }
            }
        };

        new Thread(runnable).start();


    }
}
