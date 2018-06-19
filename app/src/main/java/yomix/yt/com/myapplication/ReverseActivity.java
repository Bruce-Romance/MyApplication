package yomix.yt.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ReverseActivity extends AppCompatActivity {
    TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reverse);
        final EditText editText2 = findViewById(R.id.editText2);
        tv_result = findViewById(R.id.tv_result);
        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //方式一
                method1(editText2.getText().toString());
            }
        });
        findViewById(R.id.button6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //方式二
                method2(editText2.getText().toString());
            }
        });
        findViewById(R.id.button7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //方式三
                method3(editText2.getText().toString());
            }
        });

    }

    private void method3(String s) {
        if (!TextUtils.isEmpty(s)) {
            StringBuilder stringBuilder = new StringBuilder(s);
            if (stringBuilder.reverse().toString().equals(s)) {
                tv_result.setText("是回文");
            } else {
                tv_result.setText("不是回文");
            }
        } else {
            tv_result.setText("请输入内容");
        }
    }

    /**
     * @param s
     */
    private void method2(String s) {
        char[] c = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = c.length - 1; i >= 0; i--) {
            sb.append(c[i]);
        }
        tv_result.setText(sb.toString());
    }

    /**
     * 使用自带API,Reverse
     *
     * @param s
     */
    private void method1(String s) {
        StringBuilder stringBuffer = new StringBuilder(s);
        tv_result.setText(stringBuffer.reverse());
    }
}
