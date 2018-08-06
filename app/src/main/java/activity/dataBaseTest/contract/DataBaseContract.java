package activity.dataBaseTest.contract;


import adapter.AutoAdapter;

public interface DataBaseContract {

    interface Model {
        void downLoad();
        void ItemClick(int position, AutoAdapter adapter);
    }

    interface View {
        void setItem(String info,int code);
    }

    interface Presenter {
        void downLoad();

        void requestFail(String msg);

        void requestSuccess();

        void ItemClick(int position, AutoAdapter adapter);

        void setItem(String info,int code);


    }
}
