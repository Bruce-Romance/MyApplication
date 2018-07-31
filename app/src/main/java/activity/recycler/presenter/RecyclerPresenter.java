package activity.recycler.presenter;

import java.util.List;

import bean.MainBean;
import activity.recycler.contract.RecyclerContract;
import activity.recycler.model.RecyclerModel;

public class RecyclerPresenter implements RecyclerContract.Presenter {

    private RecyclerContract.View view;
    private RecyclerContract.Model model;

    public RecyclerPresenter(RecyclerContract.View view) {
        this.view = view;
        model = new RecyclerModel(this);
    }

    @Override
    public void initData() {
        model.initData();
    }

    @Override
    public void complete(List<MainBean> list) {
        view.complete(list);
    }
}
