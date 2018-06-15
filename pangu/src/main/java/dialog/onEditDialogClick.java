package dialog;

import android.app.Dialog;
import android.widget.EditText;

public interface onEditDialogClick {
    void confirm(EditText editText);

    void cancel(Dialog dialog);
}
