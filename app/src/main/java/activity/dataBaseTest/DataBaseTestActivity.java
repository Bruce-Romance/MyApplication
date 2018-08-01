package activity.dataBaseTest;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import activity.BaseActivity;
import activity.dataBaseTest.presenter.DataBasePresenter;
import activity.dataBaseTest.contract.DataBaseContract;
import adapter.AutoAdapter;
import business.BarCodeBusiness;
import dialog.DialogUtils;
import dialog.LoadDialog;
import eventbus.Event;
import eventbus.EventBusCode;
import toast.ToastUtils;
import yomix.yt.com.myapplication.R;


public class DataBaseTestActivity extends BaseActivity implements DataBaseContract.View {

    private DataBaseContract.Presenter presenter;

    private AutoAdapter adapter;

    private TextView info;

    private LoadDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_card);

        presenter = new DataBasePresenter(this);

        AutoCompleteTextView edit = findViewById(R.id.editText3);
        info = findViewById(R.id.textView4);
        adapter = new AutoAdapter(DataBaseTestActivity.this, new BarCodeBusiness());
        edit.setAdapter(adapter);

        edit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.ItemClick(position, adapter);
            }
        });
    }

    /**
     * 开始下载
     *
     * @param view
     */
    public void testDownload(View view) {
        DialogUtils utils = new DialogUtils();
        dialog = utils.loadDialog(this, R.style.PanGuDialog);
        dialog.show();
        EventBus.getDefault().post(new Event<>(EventBusCode.DataBaseStartDown));
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void startDownLoad(Event event) {
        if (event.getCode() == EventBusCode.DataBaseStartDown) {
            presenter.downLoad();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void downSuccess(Event event) {
        if (event.getCode() == EventBusCode.DataBaseDownSuccess) {
            dialog.dismiss();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void downFail(Event event) {
        if (event.getCode() == EventBusCode.DataBaseDownFail) {
            ToastUtils.error((String) event.getData());
            dialog.dismiss();
        }
    }

    @Override
    protected boolean isSubscribe() {
        return true;
    }

    @Override
    protected void rfidStatus(int value) {

    }

    @Override
    public void setItem(String str, int code) {
        switch (code) {
            case 1:
                info.setText(str);
                break;
            case 2:
                ToastUtils.error("条码不存在");
                break;
        }

    }
}
