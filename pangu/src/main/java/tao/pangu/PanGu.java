package tao.pangu;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * @author YT
 * @date 2018/2/9
 */

public class PanGu {


    private Application mApplication;

    private static PanGu panGu = new PanGu();

    private PanGu() {

    }

    public static PanGu getInstance() {
        return panGu;
    }

    public void init(Application application) {
        mApplication = application;
    }

    public Application getApplication() {
        return mApplication;
    }
}
