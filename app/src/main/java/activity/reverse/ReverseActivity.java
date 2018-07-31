package activity.reverse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import activity.reverse.contract.ReverseContract;
import activity.reverse.presenter.ReversePresenter;
import yomix.yt.com.myapplication.R;

public class ReverseActivity extends AppCompatActivity implements ReverseContract.View {

    private TextView tv_result;

    private ReverseContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reverse);
        final EditText editText2 = findViewById(R.id.editText2);
        tv_result = findViewById(R.id.tv_result);

        presenter = new ReversePresenter(this);

        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //方式一
                presenter.method3(editText2.getText().toString());
            }
        });
        findViewById(R.id.button6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //方式二
                presenter.method2(editText2.getText().toString());
            }
        });
        findViewById(R.id.button7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //方式三
                presenter.method1(editText2.getText().toString());
            }
        });

    }

    @Override
    public void method1(String str) {
        tv_result.setText(str);
    }

    @Override
    public void method2(String str) {
        tv_result.setText(str);
    }

    @Override
    public void method3(String str) {
        tv_result.setText(str);
    }
}
