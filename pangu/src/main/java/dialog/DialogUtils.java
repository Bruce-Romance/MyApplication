package dialog;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;

/**
 * @author YT
 * @date 2018/2/5
 */

public class DialogUtils {
    private EditDialog editDialog;
    private LoadDialog loadDialog;
    private MessageDialog messageDialog;

    /**
     * 自定义EditTextDialog
     *
     * @param activity
     * @param title
     * @param click
     */
    public void editDialog(Activity activity, String title, final onEditDialogClick click) {
        if (editDialog == null) {
            editDialog = new EditDialog(activity);
        }
        editDialog.setTitle(title);
        final EditText editText = editDialog.getEditText();
        editDialog.getTvSure().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.confirm(editText);
                editDialog.dismiss();
            }
        });
        editDialog.getTvCancel().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.cancel(editDialog);
                editDialog.dismiss();
            }
        });
        editDialog.show();
    }

    /**
     * 自定义耗时操作Dialog
     *
     * @param activity
     * @param themeResId StyleId
     * @return
     */
    public LoadDialog loadDialog(Activity activity, int themeResId) {
        if (loadDialog == null) {
            loadDialog = new LoadDialog(activity, themeResId);
        }
//        loadDialog.setContent(content);
        loadDialog.getLottieView().playAnimation();
        loadDialog.getLottieView().loop(true);
        loadDialog.setCanceledOnTouchOutside(false);
        loadDialog.setCancelable(false);
        loadDialog.show();
        return loadDialog;
    }

    /**
     * 自定义消息对话框
     *
     * @param activity
     * @param title
     * @param message
     * @param hasCancel
     * @param click
     */
    public void messageDialog(Activity activity, String title, String message, boolean hasCancel, final onMessageDialogClick click) {
        if (messageDialog == null) {
            messageDialog = new MessageDialog(activity);
        }
        messageDialog.getTvTitle().setText(title);
        messageDialog.getTvMessage().setText(message);
        if (!hasCancel) {
            messageDialog.getTextView10().setVisibility(View.GONE);
            messageDialog.getTvCancel().setVisibility(View.GONE);
        }
        messageDialog.getTvSure().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.confirm(messageDialog);
                messageDialog.dismiss();
            }
        });
        messageDialog.getTvCancel().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.cancel(messageDialog);
                messageDialog.dismiss();
            }
        });
        messageDialog.show();
    }
}
