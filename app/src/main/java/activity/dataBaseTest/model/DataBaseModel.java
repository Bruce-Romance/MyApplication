package activity.dataBaseTest.model;

import activity.dataBaseTest.contract.DataBaseContract;

public class DataBaseModel implements DataBaseContract.Model {
    private DataBaseContract.Presenter presenter;

    public DataBaseModel(DataBaseContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
