package yomix.yt.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;

import Bean.SongCi;
import Bean.SongCiDao;
import dialog.DialogUtils;
import dialog.LoadDialog;
import dialog.MessageDialog;
import dialog.onMessageDialogClick;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import util.Dao;

public class SongCiActivity extends AppCompatActivity {

    private TextView title, author, content;

    private EditText editText;

    private final String URL = "http://api.jisuapi.com/songci/search?appkey=1df53fee682ab4c8&keyword=";

    private LoadDialog loadDialog;

    private interface songCiCallBack {
        void callBack(SongCi songCi);

        void fail(String msg);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_ci);
        title = findViewById(R.id.tv_title);
        author = findViewById(R.id.tv_author);
        content = findViewById(R.id.tv_content);
        editText = findViewById(R.id.edit_content);
        final DialogUtils utils = new DialogUtils();

        //查出最后一条数据
        Long count = Dao.getInstance().getDaoSession().getSongCiDao().queryBuilder().count();
        SongCi songCi = Dao.getInstance().getDaoSession().getSongCiDao().load(count - 1);
        title.setText(songCi.getType() + "\n\n" + songCi.getTitle());
        author.setText(songCi.getAuthor());
        String[] contents = songCi.getContent().split("。");
        StringBuilder builder = new StringBuilder();
        for (String content1 : contents) {
            builder.append(content1).append("\n").append("\n");
        }
        content.setText(builder.toString());

        //查询
        findViewById(R.id.btn_check).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDialog = utils.loadDialog(SongCiActivity.this, R.style.PanGuDialog);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        requestSongCi(new songCiCallBack() {
                            @Override
                            public void callBack(final SongCi songCi) {
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
                            public void fail(final String msg) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        loadDialog.dismiss();
                                        new DialogUtils().messageDialog(SongCiActivity.this, "查询失败", msg, false, new onMessageDialogClick() {
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

    private void requestSongCi(songCiCallBack callBack) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(URL + editText.getText().toString())
                    .build();
            Response response = client.newCall(request).execute();
            JSONObject object = new JSONObject(response.body().string());
            if (object.getInt("status") == 0) {

                JSONObject object1 = object.getJSONObject("result");
                JSONArray jsonArray = object1.getJSONArray("list");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    SongCi songCi = new SongCi();
                    songCi.setTitle(jsonObject.getString("title"));
                    songCi.setType(jsonObject.getString("type"));
                    songCi.setContent(jsonObject.getString("content"));
                    songCi.setAuthor(jsonObject.getString("author"));
                    SongCi songCi1 = Dao.getInstance().getDaoSession().getSongCiDao().queryBuilder().where(SongCiDao.Properties.Title.eq(songCi.getTitle())).unique();
                    if (songCi1 == null) {
                        Dao.getInstance().getDaoSession().insert(songCi);
                    }
                    callBack.callBack(songCi);
                }
            } else {
                callBack.fail(object.getString("msg"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (final JSONException e) {
            e.printStackTrace();
        }
    }
}
