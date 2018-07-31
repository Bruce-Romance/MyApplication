package activity.dataBaseTest.presenter;

import activity.dataBaseTest.contract.DataBaseContract;
import activity.dataBaseTest.model.DataBaseModel;

public class DataBasePresenter implements DataBaseContract.Presenter {
    private DataBaseContract.View view;

    private DataBaseContract.Model model;

    public DataBasePresenter(DataBaseContract.View view) {
        this.view = view;
        model = new DataBaseModel(this);
    }
}
