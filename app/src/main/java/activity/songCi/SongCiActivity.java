package activity.songCi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import Bean.SongCi;
import activity.songCi.contract.SongCiContract;
import activity.songCi.presenter.SongCiPresenter;
import dialog.DialogUtils;
import dialog.LoadDialog;
import dialog.MessageDialog;
import dialog.onMessageDialogClick;
import yomix.yt.com.myapplication.R;

public class SongCiActivity extends AppCompatActivity implements SongCiContract.View {

    private TextView title, author, content;

    private EditText editText;

    private LoadDialog loadDialog;

    private SongCiContract.Presenter mPresenter = new SongCiPresenter(new SongCiContract.View() {
        @Override
        public void success(final SongCi songCi) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    title.setText(songCi.getType() + "\n\n" + songCi.getTitle());
                    author.setText(songCi.getAuthor());
                    String[] contents = songCi.getContent().split("。");
                    StringBuilder builder = new StringBuilder();
                    for (String content1 : contents) {
                        builder.append(content1).append("\n").append("\n");
                    }
                    content.setText(builder.toString());
//                    loadDialog.getLottieView().loop(false);
//                    loadDialog.dismiss();
                }
            });
        }

        @Override
        public void fail(final String errorMsg) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                    loadDialog.dismiss();
                    new DialogUtils().messageDialog(SongCiActivity.this, "查询失败", errorMsg, false, new onMessageDialogClick() {
                        @Override
                        public void confirm(MessageDialog dialog) {

                        }

                        @Override
                        public void cancel(MessageDialog dialog) {

                        }
                    });
                }
            });
        }
    });

    private DialogUtils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_ci);
        title = findViewById(R.id.tv_title);
        author = findViewById(R.id.tv_author);
        content = findViewById(R.id.tv_content);
        editText = findViewById(R.id.edit_content);
        utils = new DialogUtils();


        //查出最后一条数据
//        Long count = Dao.getInstance().getDaoSession().getSongCiDao().queryBuilder().count();
//        SongCi songCi = Dao.getInstance().getDaoSession().getSongCiDao().load(count - 1);
//        if (songCi != null) {
//            title.setText(songCi.getType() + "\n\n" + songCi.getTitle());
//            author.setText(songCi.getAuthor());
//            String[] contents = songCi.getContent().split("。");
//            StringBuilder builder = new StringBuilder();
//            for (String content1 : contents) {
//                builder.append(content1).append("\n").append("\n");
//            }
//            content.setText(builder.toString());
//        }


        //查询
        findViewById(R.id.btn_check).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mPresenter.requestSongCi(editText.getText().toString());
                    }
                }).start();

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (loadDialog != null) {
            loadDialog.dismiss();
        }
    }

    @Override
    public void success(final SongCi songCi) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
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
        });
    }

    @Override
    public void fail(final String errorMsg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loadDialog.dismiss();
                new DialogUtils().messageDialog(SongCiActivity.this, "查询失败", errorMsg, false, new onMessageDialogClick() {
                    @Override
                    public void confirm(MessageDialog dialog) {

                    }

                    @Override
                    public void cancel(MessageDialog dialog) {

                    }
                });
            }
        });
    }
}
