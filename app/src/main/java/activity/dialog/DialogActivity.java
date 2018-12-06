package activity.dialog;

import android.app.Dialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.umeng.analytics.MobclickAgent;

import activity.dialog.contract.DialogContract;
import activity.dialog.presenter.DialogPresenter;
import dialog.DialogUtils;
import dialog.LoadDialog;
import dialog.MessageDialog;
import toast.ToastUtils;
import dialog.onEditDialogClick;
import dialog.onMessageDialogClick;
import yomix.yt.com.myapplication.R;

public class DialogActivity extends AppCompatActivity implements DialogContract.View {

    private DialogContract.Presenter presenter;

    DialogUtils utils;

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("DialogActivity");
        MobclickAgent.onResume(this);
    }
    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("DialogActivity");
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        utils = new DialogUtils();

        presenter = new DialogPresenter(this);
    }


    public void EditDialog(View view) {
        utils.editDialog(DialogActivity.this, "编辑", new onEditDialogClick() {
            @Override
            public void confirm(EditText editText) {
                ToastUtils.success("输入了" + editText.getText().toString());
            }

            @Override
            public void cancel(Dialog dialog) {
                ToastUtils.success("取消");
            }
        });
    }

    public void MessageDialog(View view) {
        utils.messageDialog(DialogActivity.this, "提示", getResources().getString(R.string.content), true, new onMessageDialogClick() {
            @Override
            public void confirm(MessageDialog dialog) {

            }

            @Override
            public void cancel(MessageDialog dialog) {

            }
        });
    }

    public void LoadDialog(View view) {
        final LoadDialog dialog = utils.loadDialog(DialogActivity.this, R.style.PanGuDialog);
        dialog.show();
        Handler handler = new Handler(getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 4000);
    }
}
