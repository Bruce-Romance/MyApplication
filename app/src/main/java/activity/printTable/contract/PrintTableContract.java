package activity.printTable.contract;

import java.util.List;
import activity.printTable.PrintTableBean;

public interface PrintTableContract {


    interface Model {
        void initData();

        void split(List<PrintTableBean> list);
    }

    interface View {
        void complete(List<PrintTableBean> list);

        void print(String string);
    }

    interface Presenter {
        void initData();

        void complete(List<PrintTableBean> list);

        void split(List<PrintTableBean> list);

        void print(String string);
    }
}
