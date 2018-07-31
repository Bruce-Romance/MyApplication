package activity.reverse.presenter;

import activity.reverse.contract.ReverseContract;
import activity.reverse.model.ReverseModel;

public class ReversePresenter implements ReverseContract.Presenter {

    private ReverseContract.View mView;
    private ReverseContract.Model model;

    public ReversePresenter(ReverseContract.View view) {
        this.mView = view;
        model = new ReverseModel(this);
    }

    @Override
    public void method1(String str) {
        model.method1(str);
    }

    @Override
    public void method2(String str) {
        model.method2(str);
    }

    @Override
    public void method3(String str) {
        model.method3(str);
    }

    @Override
    public void result1(String str) {
        mView.method1(str);
    }

    @Override
    public void result2(String str) {
        mView.method2(str);
    }

    @Override
    public void result3(String str) {
        mView.method3(str);
    }


}
