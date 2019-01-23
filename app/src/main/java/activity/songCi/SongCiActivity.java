package activity.songCi;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import activity.MyApplication;
import eventbus.Event;
import eventbus.EventBusCode;
import bean.SongCi;
import activity.BaseActivity;
import activity.songCi.contract.SongCiContract;
import activity.songCi.presenter.SongCiPresenter;
import dialog.DialogUtils;
import dialog.LoadDialog;
import dialog.MessageDialog;
import dialog.onMessageDialogClick;
import yomix.yt.com.myapplication.R;

public class SongCiActivity extends BaseActivity implements SongCiContract.View {

    private TextView title, author, content;

    private EditText editText;
    /*自定义耗时Dialog*/
    private LoadDialog loadDialog;
    /*P层*/
    private SongCiContract.Presenter mPresenter;

    private DialogUtils utils;

    List<SongCi> list = new ArrayList<>();


    /**
     * 是否需要订阅Event事件
     *
     * @return
     */
    @Override
    protected boolean isSubscribe() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_ci);
        title = findViewById(R.id.tv_title);
        author = findViewById(R.id.tv_author);
        content = findViewById(R.id.tv_content);
        editText = findViewById(R.id.edit_content);

        utils = new DialogUtils();

        mPresenter = new SongCiPresenter(this);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 50000; i++) {
//                    SongCi s = new SongCi();
//                    s.setAuthor("李白" + i);
//                    s.setContent("日照香炉生紫烟，遥看瀑布挂前川.飞流直下三千尺,疑是银河落九天.");
//                    s.setTitle("望庐山瀑布");
//                    s.setType("这是李白五十岁左右隐居庐山时写的一首风景诗。这首诗形象地描绘了庐山瀑布雄奇壮丽的景色，反映了诗人对祖国大好河山的无限热爱。");
//                    MyApplication.getDaoSession().insert(s);
//                    Log.d("插入数据", "第" + i + "个");
//                }
//            }
//        }).start();


        //查询
        findViewById(R.id.btn_check).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDialog = utils.loadDialog(SongCiActivity.this, R.style.PanGuDialog);
//                //发送事件
                EventBus.getDefault().post(new Event<>(EventBusCode.SongCiStart, editText.getText().toString()));
            }
        });
    }

    @Override
    protected void rfidStatus(int value) {

    }

    /**
     * 订阅消息
     * 根据对象区分事件
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void query(Event event) {
        if (event.getCode() == EventBusCode.SongCiStart)
            mPresenter.requestSongCi((String) event.getData());
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("SongCiActivity");
        MobclickAgent.onPause(this);
        if (loadDialog != null) {
            loadDialog.dismiss();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("SongCiActivity");
        MobclickAgent.onResume(this);
    }


    /**
     * View层发送事件,切换到主线程处理
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeSuccess(Event event) {
        if (event.getCode() == EventBusCode.SongCiSuccess) {
            SongCi songCi = (SongCi) event.getData();
            title.setText(songCi.getType() + "\n\n" + songCi.getTitle());
            author.setText(songCi.getAuthor());
            String[] contents = songCi.getContent().split("。");
            StringBuilder builder = new StringBuilder();
            for (String content1 : contents) {
                builder.append(content1).append("\n").append("\n");
            }
            content.setText(builder.toString());
//            loadDialog.getLottieView().loop(false);
            loadDialog.dismiss();
        }
    }

    /**
     * View层回调
     *
     * @param songCi
     */
    @Override
    public void success(final SongCi songCi) {
//        EventBus.getDefault().post(songCi);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeFail(Event event) {
        if (event.getCode() == EventBusCode.SongCiFail) {
            loadDialog.dismiss();
            new DialogUtils().messageDialog(SongCiActivity.this, "查询失败", (String) event.getData(), false, new onMessageDialogClick() {
                @Override
                public void confirm(MessageDialog dialog) {

                }

                @Override
                public void cancel(MessageDialog dialog) {

                }
            });
        }
    }

    @Override
    public void fail(final String errorMsg) {
//        EventBus.getDefault().post(new ErrorMessage(errorMsg));
    }
}
