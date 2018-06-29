package util;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.telephony.SmsManager;
import android.util.Base64;
import android.widget.EditText;

import com.lemap.core.utils.DeviceInfo;
import com.lemap.core.utils.DeviceInfo.SimState;
import com.lemap.core.utils.StringExtensions;
import com.lemap.core.utils.StringValidator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClsUtils {


    /**
     * 设置只读属性
     *
     * @param view
     */
    public static void setEditTextReadOnly(EditText view) {
        if (view instanceof EditText) {
            view.setCursorVisible(false);      //设置输入框中的光标不可见
            view.setFocusable(false);           //无焦点
            view.setFocusableInTouchMode(false);     //触摸时也得不到焦点
        }
    }

    /**
     * 判断是否RFID
     *
     * @param barCode
     * @return
     */
    static public boolean getIsRfidCode(String barCode) {
        return barCode.length() > 20;
    }

    /**
     * 根据长度，截取字符
     *
     * @param str       字符
     * @param appendStr 追加字符
     * @return
     */
    static public String getTopicLeft(String str, int subLength, String appendStr) {
        int num = 0;// 已经截取字符的长度
        int length = 0;// 每个字符的长度
        StringBuffer sb = new StringBuffer();
        char ch[] = str.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            length = StringExtensions.getBytesLength(String.valueOf(ch[i]));
            num += length;
            if (num > subLength) {
                break;
            }
            sb.append(ch[i]);
        }
        return sb.toString();
    }

    static public String fillString(String sText, int totalLength) {
        String tempStr = "";
        for (int i = 0; i < totalLength; i++) {
            tempStr += sText;
        }
        return tempStr;
    }

    static public String padBytesCenter(String sText, int totalLength, String paddingString) {
        int textLength = StringExtensions.getBytesLength(sText);

        if (textLength > totalLength) {
            sText = getTopicLeft(sText, totalLength, "");
        } else if (textLength < totalLength) {
            int padLength = totalLength - textLength;
            int padLeft = padLength / 2;
            int padRight = padLength - padLeft;
            for (int i = 0; i < padLeft; i++) {
                sText = paddingString + sText;
            }
            for (int i = 0; i < padRight; i++) {
                sText += paddingString;
            }
        }
        return sText;
    }

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

    /**
     * 获取百分比
     *
     * @param totalSize
     * @param availableSize
     * @return
     */
    static public String getPercent(float totalSize, float availableSize) {
        if (totalSize == 0 || availableSize == 0) return "0%";
        int percent = (int) (availableSize / totalSize * 100);
        return String.valueOf(percent) + "%";
    }

    /**
     * 判断是否日期格式
     *
     * @param sDate
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    static public Boolean isValidDate(String sDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            format.parse(sDate);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @SuppressLint("SimpleDateFormat")
    static public Date getDateFormat(String sDate) {
        Date dt = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dt = dateFormat.parse(sDate);
        } catch (ParseException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return dt;
    }

    @SuppressLint("SimpleDateFormat")
    static public Date getDateFormatSp(String date) {
        //20130101
        Date dt = new Date();
        if (!date.contains("-") || !date.contains("/")) {
            date = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dt = dateFormat.parse(date);
        } catch (ParseException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return dt;
    }

    /**
     * 字符转日期，带格式
     *
     * @param sDate
     * @param sFormat
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    static public Date getDateFormat(String sDate, String sFormat) {
        Date dt = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(sFormat);
        try {
            dt = dateFormat.parse(sDate);
        } catch (ParseException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        return dt;
    }

    /**
     * 日期转字符，带格式
     *
     * @param sDate
     * @param sFormat
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    static public String getDateFormat(Date sDate, String sFormat) {
        String date = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat(sFormat);
        try {
            date = dateFormat.format(sDate);
        } catch (Exception ex) {
            date = "";
        }
        return date;
    }

    @SuppressLint("SimpleDateFormat")
    static public String getDateFormat(Date sDate) {
        String date = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = dateFormat.format(sDate);
        } catch (Exception ex) {
            date = "";
        }
        return date;
    }

    @SuppressLint("SimpleDateFormat")
    static public String getDateStarFormat(Date sDate) {
        String date = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        try {
            date = dateFormat.format(sDate);
        } catch (Exception ex) {
            date = "";
        }
        return date;
    }

    @SuppressLint("SimpleDateFormat")
    static public String getDateEndFormat(Date sDate) {
        String date = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        try {
            date = dateFormat.format(sDate);
        } catch (Exception ex) {
            date = "";
        }
        return date;
    }





    /*
     * 日期加上指定的天数
     */
    static public Date addDate(Date date, long day) {
        long time = date.getTime();
        day = day * 24 * 60 * 60 * 1000;
        time += day;
        return new Date(time);
    }

    /**
     * 时间间隔
     *
     * @param date1
     * @param date2
     * @param dateType（天，时，分，秒）
     * @return
     */
    static public int dateDiff(Date date1, Date date2, String dateType) {
        long itime = 0;
        long t1 = date1.getTime();
        long t2 = date2.getTime();
        long t3 = t2 - t1;
        if (dateType.equals("天")) {
            itime = t3 / 24 / 1000 / 60 / 60;
        } else if (dateType.equals("时")) {
            itime = t3 / 1000 / 60 / 60;
        } else if (dateType.equals("分")) {
            itime = t3 / 1000 / 60;
        } else {
            itime = t3 / 1000;
        }
        return (int) itime;
    }


    static public double getDouble(double d1, double d2) {
        return Math.round(d1 * 100 / d2) / 100.0;
    }

    static public double getDouble(Object obj) {
        if (obj == null || obj.toString().length() <= 0) return 0.0;
        return Double.parseDouble(obj.toString());
    }

    /**
     * 取得指定精度的double
     *
     * @param value
     * @param scale
     * @param roundingMode,HALF_UP（四舍五入）,CEILING（进位），DOWN（舍去）....
     * @return
     */
    static public double getDoubleFormat(double value, int scale, RoundingMode roundingMode) {
        BigDecimal bd = new BigDecimal(String.valueOf(value));
        bd = bd.setScale(scale, roundingMode);
        double d = bd.doubleValue();
        bd = null;
        return d;
    }


    /**
     * 返回4位小数的折扣
     *
     * @param value
     * @return
     */
    static public double getDiscountFormat(double value) {
        double dValue = getDoubleFormat(value, 4, RoundingMode.HALF_UP);
        return dValue;
    }

    /**
     * 发送短信
     *
     * @param phoneNum
     * @param message
     */
    static public Boolean sendSms(String phoneNum, String message) {
        //拆分短信，防止短信大于70个字符
        if (DeviceInfo.getSimState() != SimState.Ready) {
            return false;
        }
        try {
            SmsManager smsManager = SmsManager.getDefault();
            ArrayList<String> texts = smsManager.divideMessage(message);
            for (String text : texts) {
                smsManager.sendTextMessage(phoneNum, null, text, null, null);
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }


    /**
     * 替换特殊符号为字符串
     *
     * @param str
     * @return
     */
    static public String replaceSpCharVal(String str) {
        String result = str;
        try {
            if (str.toLowerCase().startsWith("s")) {  //s10 代表空格10个
                int count = 0;

                count = Integer.parseInt(str.substring(1));
                result = "";
                for (int i = 0; i < count; i++) {
                    result += " ";
                }
            } else if (str.toLowerCase().startsWith("t")) {//t10代表制表符10个
                int count = 0;
                count = Integer.parseInt(str.substring(1));
                result = "";
                for (int i = 0; i < count; i++) {
                    result += "\t";
                }
            } else if (str.toLowerCase().startsWith("d")) {//时间
                String type = str.substring(1);
                if (type.equals("1")) {
                    result = getDateFormat(new Date(), "yyyy-MM-dd");
                } else if (type.equals("2")) {
                    result = getDateFormat(new Date(), "yyyy-MM-dd HH:mm:ss");
                }
            }
        } catch (Exception e) {

        }
        return result;
    }

    public static Bitmap resizeImage(Bitmap bitmap, int screenWidth) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int newWidth = screenWidth;
        int newHeight = screenWidth * height / width; // 根据屏幕的宽度，计算按比较缩放后的高度
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        Matrix matrix = new Matrix();

        matrix.postScale(scaleWidth, scaleHeight);

        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);

        return resizedBitmap;
    }

    /**
     * 深拷贝
     *
     * @param src
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    static public <T> List<T> deepCopy(List<T> src) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);

        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        @SuppressWarnings("unchecked")
        List<T> dest = (List<T>) in.readObject();
        return dest;
    }

    /**
     * 深拷贝
     *
     * @param src
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    static public <T> T deepCopy(T src) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);

        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        @SuppressWarnings("unchecked")
        T dest = (T) in.readObject();
        return dest;
    }

    /**
     * 图片缩略图
     *
     * @return
     */
    public static Bitmap compressThumbnailImage(Bitmap image, String imageSavePath) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(CompressFormat.JPEG, 100, baos);
        // if (baos.toByteArray().length / 1024 > 100) {
        // baos.reset();// 重置baos即清空baos
        // image.compress(Bitmap.CompressFormat.JPEG, 50, baos);//
        // 这里压缩50%，把压缩后的数据存放到baos中
        // }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        float hh = 100f;// 这里设置高度为800f
        float ww = 80f;// 这里设置宽度为480f
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;// 设置缩放比例
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(imageSavePath);
            bitmap.compress(CompressFormat.JPEG, 90, out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static Bitmap getimage(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);//此时返回bm为空

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 1280f;//这里设置高度为800f
        float ww = 720f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return compressImage(bitmap);//压缩好比例大小后再进行质量压缩
    }

    public static Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 2048) {    //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            options -= 10;//每次都减少10
            baos.reset();//重置baos即清空baos
            image.compress(CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    /**
     * 获得图片字符
     *
     * @param filePath
     * @return
     */
    public static String getImagesString(String filePath) {
        Bitmap bm = null;
        File f = new File(filePath);
        if (f.exists()) {
            bm = BitmapFactory.decodeFile(f.getAbsolutePath());

            if (bm != null) {
                byte[] bytes = compressBitmapToByte(bm);

                String imageString = Base64.encodeToString(bytes, Base64.DEFAULT);
                return imageString;
            }
        }

        return "";
    }

    /**
     * 压缩图片
     *
     * @param src
     * @return
     */
    public static byte[] compressBitmapToByte(Bitmap src) {
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        src.compress(CompressFormat.JPEG, 100, bStream);

        ByteArrayInputStream isBm = new ByteArrayInputStream(
                bStream.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;
        newOpts.inJustDecodeBounds = false;
        int w = src.getWidth();
        int h = src.getHeight();
        float hh = 800f;
        float ww = 480f;
        int be = 1;
        if (w > h && w > ww) {
            be = (int) (w / ww);
        } else if (w < h && h > hh) {
            be = (int) (w / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;
        src = BitmapFactory.decodeStream(isBm, null, newOpts);
        try {
            isBm.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        src.compress(CompressFormat.JPEG, 100, bStream);
        src.recycle();

        byte[] bytes = bStream.toByteArray();
        try {
            bStream.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return bytes;
    }

}
