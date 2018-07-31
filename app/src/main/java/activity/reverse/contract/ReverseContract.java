package activity.reverse.contract;

public interface ReverseContract {

    interface Model {
        void method1(String str);
        void method2(String str);
        void method3(String str);
    }

    interface View {
        void method1(String str);
        void method2(String str);
        void method3(String str);
    }

    interface Presenter {
        void method1(String str);
        void method2(String str);
        void method3(String str);
        void result1(String str);
        void result2(String str);
        void result3(String str);
    }
}
