package yomix.yt.com.myapplication;


import android.database.Cursor;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.lemap.app.BaseActivity;
import com.lemap.app.MessageDialog;
import com.lemap.data.Completed;
import com.lemap.data.ProgressMessage;
import com.lemap.data.ProgressMessageFunc;
import com.lemap.data.ScanType;
import com.lemap.scanner.Scannable;

import Bean.BaseBarCode;
import adapter.AutoAdapter;
import business.BarCodeBusiness;
import toast.ToastUtils;


public class IdCardActivity extends BaseActivity implements Scannable {

    private BarCodeBusiness business;

    private AutoAdapter adapter;

    private TextView info;

    @Override
    protected void onCreate() {
        setContentView(R.layout.activity_id_card);

        AutoCompleteTextView edit = findViewById(R.id.editText3);
        info = findViewById(R.id.textView4);
        business = new BarCodeBusiness();
        adapter = new AutoAdapter(IdCardActivity.this, business);
        edit.setAdapter(adapter);

        edit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StringBuilder sb = new StringBuilder("");
                Cursor c = (Cursor) adapter.getItem(position);
                String code = c.getString(0);
                BaseBarCode baseBarCode = business.queryOne(code);
                if (baseBarCode != null) {
                    sb.append("条码:").append(baseBarCode.Code).append("\n")
                            .append("货品名称:").append(baseBarCode.GoodsName).append("\n")
                            .append("颜色:").append(baseBarCode.ColorName).append("\n")
                            .append("尺码:").append(baseBarCode.SizeName).append("\n");
                    info.setText(sb);
                }
            }
        });
    }

    @Override
    protected void initComponent() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerScannable(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterScannable(this);
    }

    @Override
    public void onScan(String s, ScanType scanType) {

    }

    /**
     * 测试下载
     *
     * @param view
     */
    public void testDownload(View view) {
        this.startAsynMessage(new ProgressMessageFunc() {
            @Override
            public void doException(Exception e) {
                MessageDialog.show(IdCardActivity.this, "下载失败。" + e.getMessage());
            }

            @Override
            public Object run(ProgressMessage progressMessage) throws Exception {
                business.downLoadFile(progressMessage);
                return null;
            }
        }, new Completed() {
            @Override
            public void run(Object o) {
                ToastUtils.success("下载完成");
            }
        });
    }
}
