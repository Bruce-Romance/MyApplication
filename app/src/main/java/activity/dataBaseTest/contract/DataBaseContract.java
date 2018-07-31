package activity.dataBaseTest.contract;


public interface DataBaseContract {

    interface Model {
        void downLoad();
    }

    interface View {

    }

    interface Presenter {
        void downLoad();

        void requestFail(String msg);

        void requestSuccess();
    }
}
