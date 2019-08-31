package activity.songCi;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.umeng.analytics.MobclickAgent;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import act.PanGuActivity;
import eventbus.Event;
import eventbus.EventBusCode;
import bean.SongCi;
import activity.songCi.contract.SongCiContract;
import activity.songCi.presenter.SongCiPresenter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import task.MessageInfo;
import task.MyAsyncTask;
import toast.ToastUtils;
import yomix.yt.com.myapplication.R;

public class SongCiActivity extends PanGuActivity implements SongCiContract.View {

    private TextView title, author, content;

    private EditText editText;
    /*P层*/
    private SongCiContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_ci);
        title = findViewById(R.id.tv_title);
        author = findViewById(R.id.tv_author);
        content = findViewById(R.id.tv_content);
        editText = findViewById(R.id.edit_content);

        mPresenter = new SongCiPresenter(this);

        //查询
        findViewById(R.id.btn_check).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAsyncTask(SongCiActivity.this, new MyAsyncTask() {
                    @Override
                    public Object onRunning(MessageInfo info) throws Exception {
                        //子线程执行具体业务
                        return request(info, editText.getText().toString());
                    }

                    @Override
                    public void onFail(Exception e) {
                        //报错处理
                        ToastUtils.error(e.getMessage());
                    }

                    @Override
                    public void onComplete(Object o) {
                        //成功处理
                        ToastUtils.success("成功");
                    }
                });
            }
        });
    }

    private final String URL = "http://api.jisuapi.com/songci/search?appkey=1df53fee682ab4c8&keyword=";

    private List<SongCi> request(MessageInfo info, String appId) throws Exception {
        info.setMsg("正在查询：" + appId);
        List<SongCi> list = new ArrayList<>();
        try {

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(URL + appId)
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
                    list.add(songCi);
                }
            } else {
                throw new Exception(object.getString("msg"));
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        } catch (JSONException e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
        return list;
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

    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("SongCiActivity");
        MobclickAgent.onResume(this);
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


    @Override
    public void fail(final String errorMsg) {
//        EventBus.getDefault().post(new ErrorMessage(errorMsg));
    }
}
