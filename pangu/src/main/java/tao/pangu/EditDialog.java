package tao.pangu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EditDialog extends PanGuDialog {

    private TextView mTvSure;
    private TextView mTvCancel;
    private EditText editText;
    private TextView mTvTitle;

    EditDialog(@NonNull Context context) {
        super(context);
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
