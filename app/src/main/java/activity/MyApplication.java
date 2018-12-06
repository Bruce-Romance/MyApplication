package activity;

import android.app.Application;

import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.vondear.rxtools.RxTool;

import log.LogUtils;
import tao.pangu.PanGu;
import util.Dao;

/**
 * @author YT
 * @date 2018/2/9
 */

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        RxTool.init(this);
        PanGu.getInstance().init(this);
        LogUtils.level = 1;
        Dao.getInstance().getContext(this);
//        ViewTarget.setTagId(R.id.imageloader_uri);
        /**
         * 初始化common库
         * 参数1:上下文，不能为空
         * 参数2:设备类型，UMConfigure.DEVICE_TYPE_PHONE为手机、UMConfigure.DEVICE_TYPE_BOX为盒子，默认为手机
         * 参数3:Push推送业务的secret
         */
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "5bfcb716f1f5565a93000933");
        //打开友盟统计SDK调试模式
        UMConfigure.setLogEnabled(true);
        //设置场景类型
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
        //手动统计
        MobclickAgent.openActivityDurationTrack(false);
    }
}
