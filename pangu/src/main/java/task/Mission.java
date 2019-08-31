package task;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 *
 * Created by yangzw on 2019/2/11 上午11:38.
 */

public abstract class Mission {

    protected abstract Object run(MessageInfo messageInfo) throws Exception;

    protected abstract void exception(Exception e);

    protected abstract void complete(Object o);

    private static final int EXCEPTION = 2;
    private static final int COMPLETE = 3;
    static final int MESSAGE = 4;

    public void start(Activity activity) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        final AlertDialog dialog = builder.create();

        final Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case EXCEPTION:
                        Exception e = (Exception) msg.obj;
                        exception(e);
                        break;
                    case COMPLETE:
                        Object o = msg.obj;
                        complete(o);
                        break;
                    case MESSAGE:
                        String message = (String) msg.obj;
                        dialog.setMessage(message);
                        break;
                }
            }
        };

        final MessageInfo info = new MessageInfo(handler);
        dialog.setMessage("正在执行...");
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Object o = Mission.this.run(info);
                    handler.sendMessage(handler.obtainMessage(COMPLETE, o));
                } catch (Exception e) {
                    handler.sendMessage(handler.obtainMessage(EXCEPTION, e));
                } finally {
                    dialog.dismiss();
                }
            }
        };

        new Thread(runnable).start();
    }
}
