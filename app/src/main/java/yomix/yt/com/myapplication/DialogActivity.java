package yomix.yt.com.myapplication;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import tao.pangu.DialogUtils;
import tao.pangu.LoadDialog;
import tao.pangu.MessageDialog;
import tao.pangu.ToastUtils;
import tao.pangu.onEditDialogClick;
import tao.pangu.onMessageDialogClick;

public class DialogActivity extends AppCompatActivity {

    DialogUtils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        utils = new DialogUtils();
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
//        utils.loadDialog(DialogActivity.this, R.style.PanGuDialog).show();
    }
}
