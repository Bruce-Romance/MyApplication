package activity.recycler.contract;

import java.util.List;

import bean.MainBean;

public interface RecyclerContract {

    interface Model {
        void initData();
    }

    interface View {
        void complete(List<MainBean> list);
    }

    interface Presenter {
        void initData();
        void complete(List<MainBean> list);
    }
}
