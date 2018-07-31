package dialog;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import tao.pangu.R;

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
            editDialog = new EditDialog(activity,R.style.Theme_AppCompat_Light_Dialog_Alert);
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
            messageDialog = new MessageDialog(activity, R.style.Theme_AppCompat_Light_Dialog_Alert);
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

    /**
     * 自定义数量的RadioButton选择框
     * @param activity
     * @param items
     * @param title
     * @param message
     * @param click
     */
    public static void chooseDialog(Activity activity, String[] items, String title, String message, final onChooseDialogClick click) {
        final ChooseDialog dialog = new ChooseDialog(activity,R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.getTvTitle().setText(title);
        dialog.getTvMessage().setText(message);
        for (int i = 0; i < items.length; i++) {
            RadioButton radioButton = new RadioButton(activity);
            radioButton.setText(items[i]);
            dialog.getRadioGroup().addView(radioButton, i);
        }
        final RadioGroup radioGroup = dialog.getRadioGroup();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = dialog.findViewById(radioGroup.getCheckedRadioButtonId());
                click.onClick(rb.getText().toString());
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
}
