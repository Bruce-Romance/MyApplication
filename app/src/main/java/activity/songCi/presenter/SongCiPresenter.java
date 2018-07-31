package activity.songCi.presenter;

import org.greenrobot.eventbus.EventBus;

import eventbus.Event;
import eventbus.EventBusCode;
import bean.SongCi;
import activity.songCi.contract.SongCiContract;
import activity.songCi.model.SongCiModel;

public class SongCiPresenter implements SongCiContract.Presenter {

    private SongCiContract.Model model;

    private SongCiContract.View view;

    public SongCiPresenter(SongCiContract.View view) {
        this.view = view;
        model = new SongCiModel(this);
    }

    /**
     * 开始请求
     */
    @Override
    public void requestSongCi(String appId) {
        model.requestSongCi(appId);
    }

    /**
     * 请求成功
     *
     * @param songCi
     */
    @Override
    public void success(SongCi songCi) {
        //使用EventBus可以减少一次View层回调.否则又需要在View层多写一个线程转换方法
        EventBus.getDefault().post(new Event<>(EventBusCode.SongCiSuccess, songCi));
//        view.success(songCi);
    }

    /**
     * 请求失败
     *
     * @param errorMsg
     */
    @Override
    public void fail(String errorMsg) {
        EventBus.getDefault().post(new Event<>(EventBusCode.SongCiFail, errorMsg));
//        view.fail(errorMsg);
    }

}
