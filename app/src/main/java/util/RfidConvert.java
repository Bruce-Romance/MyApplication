package util;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

/**
 * RFID转换帮助类
 */
public class RfidConvert {

    /**
     * 将RFID功率转换成对应百分比数组
     *
     * @param maxValue 最大功率
     * @param minValue 最小功率
     * @return
     */
    public static String[] RfidConvertToPer(int maxValue, int minValue) {
        //以100为有效功率分割出有多少个选项
        int count = ((maxValue - minValue) / 100) + 1;
        //初始化RFID选项数组
        String[] selection = new String[count];
        //一个选项等于多少百分比
        float value = (float) 100 / count;
        //格式化数值,如果大于100个选项.为了防止出现相同的两个选项值.多取一个位数
        DecimalFormat df;
        if (count > 100) {
            df = new DecimalFormat("0.0");
        } else {
            df = new DecimalFormat("0");
        }
        //循环赋值,首尾需特殊处理
        for (int i = 0; i < count; i++) {
            if (i == 0) {
                //赋值第一个
                selection[0] = "0%";
            } else if (i == count - 1) {
                //赋值最后一个
                selection[i] = "100%";
            } else {
                //区间赋值
                selection[i] = df.format(value * i) + "%";
            }
        }

        return selection;
    }

    /**
     * 将RFID功率以100分割成int数组
     *
     * @param maxValue 最大功率
     * @param minValue 最小功率
     * @return
     */
    public static int[] PerConvertToRfid(int maxValue, int minValue) {
        //以100为有效功率分割出有多少个选项
        int count = (maxValue - minValue) / 100 + 1;
        //初始化功率数组
        int[] values = new int[count];
        //循环赋值,首尾需特殊处理
        for (int i = 0; i < count; i++) {
            if (i == 0) {
                values[0] = minValue;
            } else if (i == count - 1) {
                values[i] = maxValue;
            } else {
                values[i] = minValue + 100 * i;
            }
        }
        return values;
    }

    /**
     * 获取选择的RFID功率值(int)
     *
     * @param position
     * @return
     */
    public static int getChooseRfidPower(int position) {
        //最小值500
        int chooseValue = 500;
        if (position == 0) {
            return chooseValue;
        } else {
            chooseValue = chooseValue + (position * 100);
            return chooseValue;
        }
    }
}
