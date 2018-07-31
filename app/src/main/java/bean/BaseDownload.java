package bean;

import com.lemap.dataaccess.Column;

public class BaseDownload extends BaseBusinessBean {

	private static final long serialVersionUID = 1L;

	@Column(name = "RemoteFileUrl")
	public String remoteFileUrl;

	@Column(name = "LocalFilePath")
	public String localFilePath;

	@Column(name = "LocalFileName")
	public String localFileName;

    @Column(name = "BarCodeFileQty")
    public int barCodeFileQty;

}
