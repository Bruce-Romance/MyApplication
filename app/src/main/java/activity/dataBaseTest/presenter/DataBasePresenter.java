package activity.dataBaseTest.presenter;

import android.app.Activity;

import org.greenrobot.eventbus.EventBus;

import activity.dataBaseTest.contract.DataBaseContract;
import activity.dataBaseTest.model.DataBaseModel;
import eventbus.Event;
import eventbus.EventBusCode;

public class DataBasePresenter implements DataBaseContract.Presenter {
    private DataBaseContract.View view;

    private DataBaseContract.Model model;

    public DataBasePresenter(DataBaseContract.View view) {
        this.view = view;
        model = new DataBaseModel(this);
    }

    @Override
    public void downLoad() {
        model.downLoad();
    }

    @Override
    public void requestFail(String msg) {
        EventBus.getDefault().post(new Event<>(EventBusCode.DataBaseDownFail, msg));
    }

    @Override
    public void requestSuccess() {
        EventBus.getDefault().post(new Event<>(EventBusCode.DataBaseDownSuccess));
    }
}
