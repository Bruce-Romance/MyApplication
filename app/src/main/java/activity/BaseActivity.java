package activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import java.util.Timer;
import java.util.TimerTask;

public abstract class BaseActivity extends AppCompatActivity {

    private Handler mAutoRefreshHandler = null;

    public static final String INTENT_EMSH_BROADCAST = "android.intent.extra.EMSH_STATUS";

    public static final String INTENT_EMSH_REQUEST = "android.intent.extra.EMSH_REQUEST";

    public static final String CMD_REFRESH_EMSH_STATUS = "emsh.REFRESH_BATTERY_STATUS";

    private EmshStatusBroadcastReceiver mEmshStatusReceiver = null;

    private int SessionStatus, HardwareStatus;

    public class EmshStatusBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (INTENT_EMSH_BROADCAST.equalsIgnoreCase(intent.getAction())) {
                // 和MCU通讯的session状态值
                SessionStatus = intent.getIntExtra("SessionStatus", 0);
                // 把枪硬件连接电平信号状态(0/1)
                HardwareStatus = intent.getIntExtra("HardwareStatus", -1);

                Message msgRefresh = mAutoRefreshHandler.obtainMessage(2);
                msgRefresh.sendToTarget();
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAutoRefreshHandler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        //刷新状态(发送状态广播)
                        request_RefreshEmshStatus();
                        break;
                    case 2:
                        getStatus();
                        break;
                }
                super.handleMessage(msg);
            }

            ////刷新状态(发送状态广播)
            private void request_RefreshEmshStatus() {
                sendRequest_RefreshEmshStatus();
            }
        };

        Timer mTimer = new Timer();
        TimerTask mTimerTask = new TimerTask() {
            @Override
            public void run() {
                Message msg = mAutoRefreshHandler.obtainMessage(1);
                msg.sendToTarget();
            }
        };
        // 定时发送Intent请求后台服务广播当前EMSH状态
        mTimer.schedule(mTimerTask, 100, 1000);

        //动态注册
        mEmshStatusReceiver = new EmshStatusBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter(INTENT_EMSH_BROADCAST);
        registerReceiver(mEmshStatusReceiver, intentFilter);
    }

    /**
     * 获取RFID连接状态
     */
    private void getStatus() {
        rfidStatus(HardwareStatus);
    }

    /**
     * 发送状态广播
     */
    private void sendRequest_RefreshEmshStatus() {
        Intent intent = new Intent(INTENT_EMSH_REQUEST);
        intent.putExtra("CMD", CMD_REFRESH_EMSH_STATUS);
        sendBroadcast(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mEmshStatusReceiver);
    }

    protected abstract void rfidStatus(int value);
}
