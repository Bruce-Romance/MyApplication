package activity.printTable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import activity.printTable.contract.PrintTableContract;
import activity.printTable.presenter.PrintTablePresenter;
import yomix.yt.com.myapplication.R;

/**
 * @author YT
 */
public class PrintTableActivity extends AppCompatActivity implements PrintTableContract.View {

    private PrintTableContract.Presenter presenter;

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_table);
        tv = findViewById(R.id.tv);
        presenter = new PrintTablePresenter(this);
        presenter.initData();
    }


    @Override
    public void complete(List<PrintTableBean> list) {
        presenter.split(list);
    }

    @Override
    public void print(String string) {
        tv.setText(string);
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("PrintTableActivity");
        MobclickAgent.onResume(this);
    }
    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("PrintTableActivity");
        MobclickAgent.onPause(this);
    }
}
