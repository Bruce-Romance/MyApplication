/**
 * 版权所有 (C) Lemap 2012
 * @author 廖安良
 * @version 创建时间：2013-1-16 上午11:27:57
 */
package bean;

import com.lemap.core.AppParamContext;
import com.lemap.core.LemapContext;
import com.lemap.dataaccess.Column;
import com.lemap.synchrodata.bean.SyncableBean;

public abstract class BaseBusinessBean extends SyncableBean {

	private static final long serialVersionUID = 1L;

	/**
	 * 是否删除(0否;1是)
	 */
	@Column(name = "IsDeleted")
	public boolean isDeleted;

	/**
	 * 创建者ID
	 */
	@Column(name = "Creater")
	public String creater;

	/**
	 * 编辑者ID
	 */
	@Column(name = "Editor")
	public String editor;


	@Override
	public void onInserting() {
		this.isDeleted = false;
		this.update();
	}
	@Override
	public void onUpdating() {
		this.update();
	}

	@Override
	public void onReplacing() {
		this.update();
	}

	@Override
	protected void update() {
		super.update();
		this.creater = LemapContext.getSingleDefault().getAppParamContext().getValue(AppParamContext.UserCode).toString();
		this.editor = LemapContext.getSingleDefault().getAppParamContext().getValue(AppParamContext.UserCode).toString();
	}

}
