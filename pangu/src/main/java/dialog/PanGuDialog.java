package dialog;


import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager.LayoutParams;

class PanGuDialog extends Dialog {

    PanGuDialog(@NonNull Context context) {
        super(context);
        init(context);
    }

    PanGuDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    PanGuDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init(context);
    }

    private void init(Context context) {
        Window window = this.getWindow();
        LayoutParams mLayoutParams = window.getAttributes();
        window.setAttributes(mLayoutParams);
        if (mLayoutParams != null) {
            mLayoutParams.height = android.view.ViewGroup.LayoutParams.MATCH_PARENT;
            mLayoutParams.width = android.view.ViewGroup.LayoutParams.MATCH_PARENT;
            mLayoutParams.gravity = Gravity.CENTER;
        }
    }
}
