package activity.dialog.model;

import activity.dialog.contract.DialogContract;

public class DialogModel implements DialogContract.Model {
    private DialogContract.Presenter presenter;

    public DialogModel(DialogContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
