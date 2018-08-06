package dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import tao.pangu.R;

public class EditDialog extends PanGuDialog {

    private TextView mTvSure;
    private TextView mTvCancel;
    private EditText editText;
    private TextView mTvTitle;

    public EditDialog(@NonNull Context context) {
        super(context);
        initView();
    }

    public EditDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        initView();
    }

    public EditDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView();
    }

    private void initView() {
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_edittext_sure_false, null);
        mTvTitle =  dialogView.findViewById(R.id.tv_title);
        mTvSure =  dialogView.findViewById(R.id.tv_sure);
        mTvCancel =  dialogView.findViewById(R.id.tv_cancle);
        editText =  dialogView.findViewById(R.id.editText);
        setContentView(dialogView);
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        mTvTitle.setText(title);
    }

    public TextView getTvSure() {
        return mTvSure;
    }

    public TextView getTvCancel() {
        return mTvCancel;
    }

    public EditText getEditText() {
        return editText;
    }
}
