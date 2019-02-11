package task;

/**
 * Created by yangzw on 2019/2/11 上午11:14.
 */

public interface MyAsyncTask {
    Object onRunning(MessageInfo info) throws Exception;

    void onFail(Exception e);

    void onComplete(Object o);
}
