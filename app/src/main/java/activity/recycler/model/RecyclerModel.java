package activity.recycler.model;

import java.util.ArrayList;
import java.util.List;

import bean.MainBean;
import activity.recycler.contract.RecyclerContract;
import activity.recycler.presenter.RecyclerPresenter;

public class RecyclerModel implements RecyclerContract.Model {

    private RecyclerContract.Presenter presenter;

    public RecyclerModel(RecyclerPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void initData() {
        List<MainBean> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            MainBean bean = new MainBean();
            bean.setTitle("标题" + i);
            bean.setMessage("内容" + i);
            list.add(bean);
        }
        presenter.complete(list);
    }
}
