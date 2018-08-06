package toast;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import tao.pangu.PanGu;
import tao.pangu.R;


/**
 * @author YT
 * @date 2018/2/5
 */

public class ToastUtils {

    private static Toast currentToast;

    private static final String TOAST_TYPEFACE = "sans-serif-condensed";

    private static final int DEFAULT_TEXT_COLOR = Color.parseColor("#FFFFFF");

    private static final int ERROR_COLOR = Color.parseColor("#FD4C5B");

    private static final int SUCCESS_COLOR = Color.parseColor("#388E3C");

    private static final int WARNING_COLOR = Color.parseColor("#FFA900");

    private static final int NORMAL_COLOR = Color.parseColor("#FF808080");

    /**
     * 弹出错误吐司
     *
     * @param message
     */
    public static void error(String message) {
        error(PanGu.getInstance().getApplication(), message).show();
    }

    private static Toast error(Context context, String message) {
        return custom(context, message, getDrawable(context, R.drawable.pangu_ic_clear), DEFAULT_TEXT_COLOR, ERROR_COLOR, true);
    }

    /**
     * 弹出成功吐司
     *
     * @param message
     */
    public static void success(String message) {
        success(PanGu.getInstance().getApplication(), message).show();
    }

    private static Toast success(Context context, String message) {
        return custom(context, message, getDrawable(context, R.drawable.pangu_ic_check), DEFAULT_TEXT_COLOR, SUCCESS_COLOR, true);
    }

    /**
     * 弹出警告吐司
     *
     * @param message
     */
    public static void warning(String message) {
        warning(PanGu.getInstance().getApplication(), message).show();
    }

    private static Toast warning(Context context, String message) {
        return custom(context, message, getDrawable(context, R.drawable.pangu_ic_error), DEFAULT_TEXT_COLOR, WARNING_COLOR, true);
    }

    /**
     * 弹出普通吐司(无Icon)
     *
     * @param message
     */
    public static void normal(String message) {
        normal(PanGu.getInstance().getApplication(), message).show();
    }

    private static Toast normal(Context context, String message) {
        return custom(context, message, null, DEFAULT_TEXT_COLOR, NORMAL_COLOR, false);
    }

    private static Toast custom(Context context, String message, Drawable icon, int textColor, int tintColor, boolean withIcon) {
        if (currentToast == null) {
            currentToast = new Toast(context);
        }
        final View toastLayout = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.pangu_toast_layout, null);
        final ImageView toastIcon = (ImageView) toastLayout.findViewById(R.id.toast_icon);
        final TextView toastTextView = (TextView) toastLayout.findViewById(R.id.toast_text);

        Drawable drawableFrame = tint9PatchDrawableFrame(context, tintColor);

        setBackground(toastLayout, drawableFrame);

        if (withIcon) {
            if (icon == null) {
                throw new IllegalArgumentException("Avoid passing 'icon' as null if 'withIcon' is set to true");
            }
            setBackground(toastIcon, icon);
        } else {
            toastIcon.setVisibility(View.GONE);
        }

        toastTextView.setTextColor(textColor);
        toastTextView.setText(message);
        toastTextView.setTypeface(Typeface.create(TOAST_TYPEFACE, Typeface.NORMAL));

        currentToast.setView(toastLayout);
        currentToast.setDuration(Toast.LENGTH_SHORT);
        return currentToast;
    }

    private static void setBackground(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    private static Drawable tint9PatchDrawableFrame(Context context, int tintColor) {
        final NinePatchDrawable toastDrawable = (NinePatchDrawable) getDrawable(context, R.drawable.pangu_toast_frame);
        toastDrawable.setColorFilter(new PorterDuffColorFilter(tintColor, PorterDuff.Mode.SRC_IN));
        return toastDrawable;
    }

    private static Drawable getDrawable(Context context, int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return context.getDrawable(id);
        } else {
            return context.getResources().getDrawable(id);
        }
    }

}
