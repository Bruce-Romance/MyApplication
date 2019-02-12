package task;

import android.os.Handler;
import android.os.Message;

/**
 *
 * Created by yangzw on 2019/2/11 下午4:42.
 */

public class MessageInfo {

    private String msg;

    private int progress;

    private Handler mHandler;

    public MessageInfo(Handler handler) {
        mHandler = handler;
    }

    public void setMsg(String msg) {
        Message message = mHandler.obtainMessage(Mission.MESSAGE, msg);
        mHandler.sendMessage(message);
    }

    public String getMsg() {
        return msg;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
