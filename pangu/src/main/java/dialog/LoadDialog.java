package dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.airbnb.lottie.LottieAnimationView;
import tao.pangu.R;

public class LoadDialog extends PanGuDialog {
    private LottieAnimationView lottieView;
    private TextView tvContent;

    public LoadDialog(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public LoadDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        initView(context);
    }

    public LoadDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView(context);
    }

    private void initView(Context context) {
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_load, null);
        lottieView = dialogView.findViewById(R.id.lottieView);
//        tvContent = dialogView.findViewById(R.id.tv_content);
        setContentView(dialogView);
    }

    public LottieAnimationView getLottieView() {
        return lottieView;
    }

    public TextView getTvContent() {
        return tvContent;
    }

//    public void setContent(String content) {
//        tvContent.setText(content);
//    }
}
