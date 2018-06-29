package Bean;

import com.lemap.dataaccess.Column;
import com.lemap.dataaccess.MKey;
import com.lemap.synchrodata.bean.SyncableBean;
import java.util.Date;

public class BaseBarCode extends SyncableBean {

    @Column(name = "BarCode")
    public String Code;

    @Column(name = "GoodsName")
    public String GoodsName;

    @Column(name = "ColorCode")
    public String ColorCode;

    @Column(name = "ColorName")
    public String ColorName;

    @Column(name = "SizeCode")
    public String SizeCode;

    @Column(name = "SizeName")
    public String SizeName;

    @Column(name = "Category")
    public String Category;

    @Column(name = "Brand")
    public String Brand;

    @Column(name = "Long")
    public String Long;

    @Column(name = "SmallCategory")
    public String SmallCategory;

    @Column(name = "StandardPrice")
    public double StandardPrice;


    @Column(name = "UpdateTimestamp")
    public Long UpdateTimestamp;


    @Column(name = "CreateTime")
    public Date CreateTime;


    @Column(name = "EditTime")
    public Date EditTime;
}
