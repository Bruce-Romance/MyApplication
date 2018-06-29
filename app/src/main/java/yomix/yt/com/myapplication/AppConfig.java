/**
 * 版权所有 (C) Lemap 2012
 *
 * @author 廖安良
 * @version 创建时间：2013-1-16 上午9:50:15
 */
package yomix.yt.com.myapplication;

import android.annotation.SuppressLint;
import android.content.SharedPreferences.Editor;

import com.lemap.authorize.AuthorizeType;
import com.lemap.authorize.AuthorizeUtils;
import com.lemap.cloudPerform.CAO;
import com.lemap.core.AppParamContext;
import com.lemap.core.LemapContext;
import com.lemap.core.utils.EnumConvert;
import com.lemap.core.utils.StringValidator;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 应用配置项
 *
 * @author 廖安良
 * @version 创建时间：2013-1-16 上午9:50:15
 */
@SuppressLint("SdCardPath")
public abstract class AppConfig {
    private static String mAccessToken;

    @SuppressWarnings("unused")
    private static String mClientCode;
    private static String mCurrentVersion;

    private static final String BPIString = "MPos.Business.dll::MPos.Business.%s.%s()";
    private static final String mLemapServiceUrl = "http://122.13.0.67:8898";
    public static final String mAuthorizeBPIString = "MPos.Business.dll::MPos.Business.%s.%s()";
    public static final String mAuthorizeServiceUrl = "authorize.kuaidingtong.com:8088";
    private static String mUpgradeServiceUrl;//= "upgrade.kuaidingtong.com:8089";

    /**
     * 获得授权服务URL
     *
     * @return
     */
    public static String getLemapServiceUrl() {
        return mLemapServiceUrl;
    }

    // 零售默认订单ID
    public static String getDefaultOrderId = "0000";

    /**
     * 临时
     *
     * @return
     */
    public static String getLastVersion() {
        return LemapContext.getSingleDefault().getSharedPreferences().getString("LastVersion", "");
    }

    /**
     * 临时
     *
     * @param url
     */
    public static void setLastVersion(String value) {
        Editor editor = LemapContext.getSingleDefault().getSharedPreferencesEditor();
        editor.putString("LastVersion", value);
        editor.commit();
    }

    /**
     * 获取版本号
     *
     * @return
     */
    public static String getCurrentVersion() {
        if (mCurrentVersion == null) {
            mCurrentVersion = LemapContext.getSingleDefault().getSharedPreferences().getString("CurrentVersion", "");
        }
        return mCurrentVersion;
    }

    /**
     * 设置版本号
     *
     * @param url
     */
    public static void setCurrentVersion(String version) {
        mCurrentVersion = version;
        Editor editor = LemapContext.getSingleDefault().getSharedPreferencesEditor();
        editor.putString("CurrentVersion", mCurrentVersion);
        editor.commit();
    }


    /**
     * 获取BPIString
     *
     * @param clsName    业务类名
     * @param actionName 方法名
     * @return BPIString
     * @author 廖安良
     * @version 创建时间：2013-1-22 下午2:54:08
     */
    public static String getAuthorizeBPIString(String clsName, String actionName) {
        return String.format(AppConfig.mAuthorizeBPIString, clsName, actionName);
    }

    /**
     * 获取升级地址
     *
     * @return
     */
    public static String getUpgradeServiceUrl() {
        if (mUpgradeServiceUrl == null) {
            mUpgradeServiceUrl = LemapContext.getSingleDefault().getSharedPreferences().getString("UpgradeServiceUrl", "");
        }
        return mUpgradeServiceUrl;
    }

    /**
     * 设置升级地址
     *
     * @param url
     */
    public static void setUpgradeServiceUrl(String url) {
        mUpgradeServiceUrl = url;
        Editor editor = LemapContext.getSingleDefault().getSharedPreferencesEditor();
        editor.putString("UpgradeServiceUrl", mUpgradeServiceUrl);
        editor.commit();
    }




    /**
     * 获取访问云端Url
     *
     * @return
     * @author 廖安良
     * @version 创建时间：2013-1-23 下午4:16:22
     */
    public static String getCloudUrl(String ipAndPort) {
        return "http://" + ipAndPort + "/Lemap/CloudService";
    }

    /**
     * 获取云端IP和端口
     *
     * @return
     * @author 廖安良
     * @version 创建时间：2013-1-23 下午4:16:22
     */
    public static String getCloudIPAndPort() {

        try {
            URL rul = new URL(LemapContext.getSingleDefault()
                    .getSharedPreferences()
                    .getString(CAO.STR_CAOITEMKEY, CAO.STR_URLDEFUALT));
            return rul.getHost() + ":" + rul.getPort();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return CAO.STR_URLDEFUALT.substring("http://".length());
        }
    }

    /**
     * 设置默认云端IP和端口
     *
     * @param cloudIPAndPort 云端IP和端口
     * @author 廖安良
     * @version 创建时间：2013-1-23 下午4:16:42
     */
    public static void setDefaultCloudIPAndPort(String cloudIPAndPort) {
        if (!LemapContext.getSingleDefault().getSharedPreferences()
                .contains(CAO.STR_CAOITEMKEY)) {
            AppConfig.setCloudIPAndPort(cloudIPAndPort);
        }
    }

    /**
     * 设置云端IP和端口
     *
     * @param cloudIPAndPort 云端IP和端口
     * @author 廖安良
     * @version 创建时间：2013-1-23 下午4:16:42
     */
    public static void setCloudIPAndPort(String cloudIPAndPort) {
        Editor editor = LemapContext.getSingleDefault().getSharedPreferencesEditor();
        editor.putString(CAO.STR_CAOITEMKEY, AppConfig.getCloudUrl(cloudIPAndPort));
        editor.commit();
    }

    /**
     * 获取BPIString
     *
     * @param clsName    业务类名
     * @param actionName 方法名
     * @return BPIString
     * @author 廖安良
     * @version 创建时间：2013-1-22 下午2:54:08
     */
    public static String getBPIString(String clsName, String actionName) {
        return String.format(AppConfig.BPIString, clsName, actionName);
    }

}
