package yomix.yt.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import Bean.IdCard;
import dialog.DialogUtils;
import dialog.LoadDialog;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class IdCardActivity extends AppCompatActivity {

    LoadDialog loadDialog;

    public interface requestInfo {
        void response(IdCard idCard);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_card);
        final TextView person = findViewById(R.id.tv_person_info);
        final DialogUtils utils = new DialogUtils();
        findViewById(R.id.btn_query).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDialog = utils.loadDialog(IdCardActivity.this, R.style.PanGuDialog);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        requestId(new requestInfo() {
                            @Override
                            public void response(final IdCard idCard) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        person.setText(idCard.getArea() + "\n" + idCard.getSex() + "\n" + idCard.getBirthday());
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

    private void requestId(requestInfo info) {
        OkHttpClient client = new OkHttpClient();
        String url = "https://www.easy-mock.com/mock/5af534998efab658654cbccf/example/http:/idcard/index";
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            JSONObject object = new JSONObject(response.body().string());
            int result = object.getInt("resultcode");
            if (result == 200) {
                JSONObject jb = object.getJSONObject("result");
                IdCard idCard = new IdCard();
                idCard.setArea(jb.getString("area"));
                idCard.setSex(jb.getString("sex"));
                idCard.setBirthday(jb.getString("birthday"));
                info.response(idCard);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
