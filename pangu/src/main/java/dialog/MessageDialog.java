package dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import tao.pangu.R;

public class MessageDialog extends PanGuDialog {

    private TextView mTvSure;
    private TextView mTvCancel;
    private TextView mTvTitle;
    private TextView mTvMessage;
    private View textView10;

    public MessageDialog(@NonNull Context context) {
        super(context);
        initView();
    }

    public MessageDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        initView();
    }

    public MessageDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView();
    }

    private void initView() {
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_message, null);
        mTvTitle = dialogView.findViewById(R.id.tv_title);
        mTvSure = dialogView.findViewById(R.id.tv_sure);
        mTvCancel = dialogView.findViewById(R.id.tv_cancle);
        mTvMessage = dialogView.findViewById(R.id.tv_message);
        textView10 = dialogView.findViewById(R.id.textView10);
        setContentView(dialogView);
    }

    public TextView getTvSure() {
        return mTvSure;
    }

    public TextView getTvCancel() {
        return mTvCancel;
    }

    public TextView getTvTitle() {
        return mTvTitle;
    }

    public TextView getTvMessage() {
        return mTvMessage;
    }

    public View getTextView10() {
        return textView10;
    }
}
