package activity.songCi;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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

        //查询
        findViewById(R.id.btn_check).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDialog = utils.loadDialog(SongCiActivity.this, R.style.PanGuDialog);
                //发送事件
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
        if (loadDialog != null) {
            loadDialog.dismiss();
        }
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
            loadDialog.getLottieView().loop(false);
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
