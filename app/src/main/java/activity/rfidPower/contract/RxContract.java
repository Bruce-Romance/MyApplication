package activity.rfidPower.contract;

public interface RxContract {

    interface Model {
        void init(int maxValue, int minValue);
    }

    interface View {
        void change(String[] selection);
    }

    interface Presenter {
       void init(int maxValue, int minValue);
       void change(String[] selection);
    }
}
