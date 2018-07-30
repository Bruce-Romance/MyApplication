package activity.rfidPower.presenter;

import activity.rfidPower.contract.RxContract;
import activity.rfidPower.model.RxModel;

public class RxPresenter implements RxContract.Presenter {

    private RxContract.Model model;

    private RxContract.View mView;

    public RxPresenter(RxContract.View view) {
        this.mView = view;
        model = new RxModel(this);
    }

    /**
     * 初始化数据
     *
     * @param maxValue
     * @param minValue
     */
    @Override
    public void init(int maxValue, int minValue) {
        model.init(maxValue, minValue);
    }

    @Override
    public void change(String[] selection) {
        mView.change(selection);
    }
}
