package yomix.yt.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import toast.ToastUtils;


/**
 * UI适配计算
 */
public class UiCalcActivity extends AppCompatActivity {

    private DecimalFormat decimal = new DecimalFormat("0.0");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_calc);

        final TextView result = findViewById(R.id.tv_result);
        final EditText width = findViewById(R.id.edt_width);
        final EditText height = findViewById(R.id.edt_height);
        final EditText size = findViewById(R.id.edt_screenSize);
        final EditText dpi = findViewById(R.id.edt_dpi);

        findViewById(R.id.btn_calc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //计算
                if (TextUtils.isEmpty(width.getText()) || TextUtils.isEmpty(height.getText()) || TextUtils.isEmpty(size.getText())) {
                    ToastUtils.error("宽度/高度,设备尺寸不允许为空!");
                    return;
                }
                if (TextUtils.isEmpty(dpi.getText())) {
                    //只计算DPI
                    dpi.setText(calcDpi(width.getText().toString(), height.getText().toString(), size.getText().toString()));
                    ToastUtils.success("再次点击可直接计算DP");
                } else {
                    //计算DPi
                    double DPI = Double.parseDouble(calcDpi(width.getText().toString(), height.getText().toString(), size.getText().toString()));
                    //宽度DP
                    double widthDp = calcWidthDp(width.getText().toString(), DPI);
                    //高度DP
                    double heightDp = calcHeightDp(height.getText().toString(), DPI);
                    String str = "宽度DP:" + decimal.format(widthDp) + "\n高度DP:" + decimal.format(heightDp);
                    result.setText(str);
                }
            }
        });
    }

    private String calcDpi(String width, String height, String size) {
        int Width = Integer.parseInt(width);
        int Height = Integer.parseInt(height);
        double ScreenSize = Double.parseDouble(size);
        int x = Width * Width + Height * Height;
        double C = Math.sqrt(x);
        double Dpi = C / ScreenSize;
        return decimal.format(Dpi);
    }

    private double calcWidthDp(String width, double dpi) {
        int Width = Integer.parseInt(width);
        return Width / (dpi / 160);
    }

    private double calcHeightDp(String height, double dpi) {
        int Height = Integer.parseInt(height);
        return Height / (dpi / 160);
    }
}
