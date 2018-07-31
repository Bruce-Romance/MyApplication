package activity.dialog.presenter;

import activity.dialog.contract.DialogContract;
import activity.dialog.model.DialogModel;

public class DialogPresenter implements DialogContract.Presenter {

    private DialogContract.View view;

    private DialogContract.Model model;

    public DialogPresenter(DialogContract.View view) {
        this.view = view;
        model = new DialogModel(this);
    }
}
