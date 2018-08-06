package dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import tao.pangu.R;

public class ChooseDialog extends PanGuDialog {

    private TextView mTvTitle;

    private TextView mTvMessage;

    private RadioGroup mRadioGroup;

    public ChooseDialog(@NonNull Context context) {
        super(context);
        initView();
    }

    public ChooseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        initView();
    }

    public ChooseDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView();
    }

    public TextView getTvTitle() {
        return mTvTitle;
    }

    public TextView getTvMessage() {
        return mTvMessage;
    }

    public RadioGroup getRadioGroup() {
        return mRadioGroup;
    }

    private void initView() {
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_choose, null);
        mTvTitle = dialogView.findViewById(R.id.tv_title);
        mTvMessage = dialogView.findViewById(R.id.tv_message);
        mRadioGroup = dialogView.findViewById(R.id.radio_group);
        setContentView(dialogView);
    }
}
