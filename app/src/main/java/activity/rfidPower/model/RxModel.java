package activity.rfidPower.model;

import activity.rfidPower.contract.RxContract;
import util.RfidConvert;

public class RxModel implements RxContract.Model {

    private RxContract.Presenter mPresenter;

    public RxModel(RxContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void init(int maxValue, int minValue) {
        String[] strings = RfidConvert.RfidConvertToPer(maxValue, minValue);
        mPresenter.change(strings);
    }
}
