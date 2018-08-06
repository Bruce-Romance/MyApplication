package util;


import com.lemap.core.utils.StringValidator;

public class ClsUtils {

    /**
     * 过滤插入sql的字符
     *
     * @param obj
     * @return
     */
    static public String getStringBySqlFilter(String obj) {
        return getStringBySqlFilter(obj, "");
    }

    /**
     * 过滤插入sql的字符
     *
     * @param obj
     * @param def
     * @return
     */
    static public String getStringBySqlFilter(String obj, String def) {
        if (StringValidator.isNullOrEmpty(obj)) return def;
        return obj.replace("'", "''").replace("\r\n", "").replace("\r", "").replace("\n", "");
    }

}
