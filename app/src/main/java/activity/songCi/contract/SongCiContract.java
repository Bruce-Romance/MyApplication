package activity.songCi.contract;

import Bean.SongCi;

public interface SongCiContract {

    interface Model {
        void requestSongCi(String appId);
    }

    interface View {
        void success(SongCi songCi);

        void fail(String errorMsg);
    }

    interface Presenter {
        void requestSongCi(String appId);

        void success(SongCi songCi);

        void fail(String errorMsg);
    }
}
