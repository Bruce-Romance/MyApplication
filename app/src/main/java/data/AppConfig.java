/**
 * 版权所有 (C) Lemap 2012
 *
 * @author 廖安良
 * @version 创建时间：2013-1-16 上午9:50:15
 */
package data;

import android.content.SharedPreferences.Editor;
import com.lemap.cloudPerform.CAO;
import com.lemap.core.LemapContext;
import java.net.MalformedURLException;
import java.net.URL;


public abstract class AppConfig {

    private static final String BPIString = "MPos.Business.dll::MPos.Business.%s.%s()";

    /**
     * 获取云端IP和端口
     */
    public static String getCloudIPAndPort() {

        try {
            URL rul = new URL(LemapContext.getSingleDefault().getSharedPreferences().getString(CAO.STR_CAOITEMKEY, CAO.STR_URLDEFUALT));
            return rul.getHost() + ":" + rul.getPort();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return CAO.STR_URLDEFUALT.substring("http://".length());
        }
    }

    /**
     * 获取BPIString
     *
     */
    public static String getBPIString(String clsName, String actionName) {
        return String.format(AppConfig.BPIString, clsName, actionName);
    }

}
