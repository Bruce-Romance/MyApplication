package yomix.yt.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import Bean.SongCi;
import dialog.DialogUtils;
import dialog.LoadDialog;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SongCiActivity extends AppCompatActivity {

    private TextView title, author, content;

    private EditText editText;

    private final String URL = "http://api.jisuapi.com/songci/search?appkey=1df53fee682ab4c8&keyword=";

    private LoadDialog loadDialog;

    private interface songCiCallBack {
        void callBack(SongCi songCi);
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
                                        title.setText(songCi.getTitle() + "\n" + songCi.getType());
                                        author.setText(songCi.getAuthor());
                                        content.setText(songCi.getContent());
                                        loadDialog.getLottieView().loop(false);
                                        loadDialog.dismiss();
                                    }
                                });
                            }
                        });
                    }
                }).start();
            }
        });
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
                    callBack.callBack(songCi);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
