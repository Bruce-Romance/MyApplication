package activity.dataBaseTest.model;


import android.os.Handler;

import com.lemap.core.LemapContext;
import com.lemap.core.utils.JsonConvert;
import com.lemap.core.utils.StringFormat;
import com.lemap.data.ProgressMessage;
import com.lemap.synchrodata.business.BaseListSyncManagement;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import activity.dataBaseTest.contract.DataBaseContract;
import bean.BaseBarCode;
import bean.BaseDownload;
import data.AppConfig;
import util.ClsUtils;
import util.FileHelper;


public class DataBaseModel extends BaseListSyncManagement<BaseBarCode> implements DataBaseContract.Model {
    private DataBaseContract.Presenter presenter;

    public DataBaseModel(DataBaseContract.Presenter presenter) {
        super(BaseBarCode.class);
        this.presenter = presenter;
    }

    @Override
    public void downLoad() {
//        msg.setMessage("正在下载数据");

        this.getMission().BPIString = AppConfig.getBPIString("ProductManagement", "GetTEST_BARCODE");
        BaseDownload mDownload = null;
        LemapContext.getSingleDefault().getSharedPreferencesEditor().putString("LemapEnterUrl", "http://10.0.1.95:8080/Lemap/CloudService").commit();
        if (this.process()) {
            mDownload = this.getMission().getDataPacketFirst(BaseDownload.class, "@MasterPack", true);
            if (mDownload == null) return;
        } else {
            presenter.requestFail("请求失败");
        }
        String localFilePath = LemapContext.getSingleDefault().getAppDirPath() + "/" + mDownload.localFilePath + "/";
        String localFileName = localFilePath + mDownload.localFileName;
        File folder = new File(localFilePath);
        File file = new File(localFileName);

        if (!folder.exists()) {
            folder.mkdirs();
        }
        if (file.exists()) {
            file.delete();
        }

        boolean isSucess = false;
        OutputStream output = null;
        InputStream input = null;
        try {
            URL url = new URL("http://" + AppConfig.getCloudIPAndPort() + "/Lemap/MPos/" + mDownload.remoteFileUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            input = conn.getInputStream();
            file.createNewFile();
            output = new FileOutputStream(file);
            byte[] buffer = new byte[4 * 1024];
            int len = -1;
            while ((len = input.read(buffer)) != -1) {
                output.write(buffer, 0, len);
            }
            output.flush();
            output.close();
            input.close();
            isSucess = true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        if (!isSucess) return;
//        msg.setMessage("资料下载完成,解压中...");
        try {
            FileHelper.upZipFile(localFileName, localFilePath);
            if (mDownload.barCodeFileQty > 0) {
                for (int i = 1; i <= mDownload.barCodeFileQty; i++) {
                    String jsonString = FileHelper.ReadText(localFilePath + "BARCODE" + String.valueOf(i) + ".json", "");
                    JSONArray list = new JSONArray(jsonString);
                    //实体类转换
                    List<BaseBarCode> models = JsonConvert.jsonArrayToEntitys(BaseBarCode.class, list);
                    if (models.size() > 0) {
//                        msg.setMessage("正在更新数据");
                        this.beginTransaction();
                        String sql = "REPLACE INTO BaseBarCode(BarCode,GoodsName,ColorCode,ColorName,SizeCode,SizeName,Category,SmallCategory,Long,Brand,StandardPrice,UpdateTimestamp,CreateTime,EditTime)";
                        for (BaseBarCode model : models) {
                            String getEditTimeStriing = ClsUtils.getStringBySqlFilter(model.getEditTimeString(), "2000-01-01");
                            String getCreateTimeString = StringFormat.formatDate(model.CreateTime, "yyyy-MM-dd HH:mm:ss");
                            String sql1 = sql + " VALUES('" + ClsUtils.getStringBySqlFilter(model.Code)
                                    + "','" + ClsUtils.getStringBySqlFilter(model.GoodsName)
                                    + "','" + ClsUtils.getStringBySqlFilter(model.ColorCode)
                                    + "','" + ClsUtils.getStringBySqlFilter(model.ColorName)
                                    + "','" + ClsUtils.getStringBySqlFilter(model.SizeCode)
                                    + "','" + ClsUtils.getStringBySqlFilter(model.SizeName)
                                    + "','" + ClsUtils.getStringBySqlFilter(model.Category)
                                    + "','" + ClsUtils.getStringBySqlFilter(model.SmallCategory)
                                    + "','" + ClsUtils.getStringBySqlFilter(model.Long)
                                    + "','" + ClsUtils.getStringBySqlFilter(model.Brand)
                                    + "','" + ClsUtils.getStringBySqlFilter(String.valueOf(model.StandardPrice))
                                    + "','" + ClsUtils.getStringBySqlFilter(String.valueOf(model.UpdateTimestamp))
                                    + "','" + ClsUtils.getStringBySqlFilter(getCreateTimeString)
                                    + "','" + ClsUtils.getStringBySqlFilter(getEditTimeStriing) + "')";
                            this.execSQL(sql1);
                        }
                        this.commitTransaction();
                        //请求成功
                        presenter.requestSuccess();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
