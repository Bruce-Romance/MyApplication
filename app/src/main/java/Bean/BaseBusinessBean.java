/**
 * 版权所有 (C) Lemap 2012
 * @author 廖安良
 * @version 创建时间：2013-1-16 上午11:27:57
 */
package Bean;

import com.lemap.core.AppParamContext;
import com.lemap.core.LemapContext;
import com.lemap.dataaccess.Column;
import com.lemap.synchrodata.bean.SyncableBean;

/**
 * 业务Bean基类（包含公共字段：企业编号，是否删除，创建者，编辑者，编辑时间）
 * 
 * @author 廖安良
 * @version 创建时间：2013-1-16 上午11:27:57
 */
public abstract class BaseBusinessBean extends SyncableBean {

	/**
	 * @author 廖安良
	 * @version 创建时间：2013-1-19 下午12:35:27
	 */
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

	/*
	 * @see com.lemap.bean.BaseBean#onInserting()
	 * 
	 * @author 廖安良
	 * 
	 * @version 创建时间：2013-1-20 下午2:14:01
	 */
	@Override
	public void onInserting() {
		this.isDeleted = false;
		this.update();
	}

	/*
	 * @see com.lemap.bean.BaseBean#onUpdating()
	 * 
	 * @author 廖安良
	 * 
	 * @version 创建时间：2013-1-20 下午2:14:28
	 */
	@Override
	public void onUpdating() {
		this.update();
	}

	/*
	 * @see com.lemap.bean.BaseBean#onReplacing()
	 * 
	 * @author 廖安良
	 * 
	 * @version 创建时间：2013-1-20 下午2:15:43
	 */
	@Override
	public void onReplacing() {
		this.update();
	}

	/*
	 * 处理公共字段数据
	 * 
	 * @author 廖安良
	 * 
	 * @version 创建时间：2013-1-20 下午1:45:11
	 */
	@Override
	protected void update() {
		super.update();
		this.creater = LemapContext.getSingleDefault().getAppParamContext().getValue(AppParamContext.UserCode).toString();
		this.editor = LemapContext.getSingleDefault().getAppParamContext().getValue(AppParamContext.UserCode).toString();
	}

	/**
	 * 获取企业及删掉标志条件
	 * 
	 * @return
	 * 
	 * @author 廖安良
	 * @version 创建时间：2013-1-31 上午10:16:32
	 */
	public static String getWhereIsDeleted() {
		return String.format(" IsDeleted=0 ");
	}
	
	/**
	 * 获取企业及删掉标志条件
	 * 
	 * @return
	 * 
	 * @author 廖安良
	 * @version 创建时间：2013-1-31 上午10:16:32
	 */
	public static String getWhereEnterpriseCodeAndIsDeleted() {
		String enterpriseCode = LemapContext.getSingleDefault()
				.getAppParamContext().getValue(AppParamContext.EnterpriseCode)
				.toString();
		
		return String.format(" EnterpriseCode='%s' And IsDeleted=0 ",
				enterpriseCode);
	}

	/**
	 * 获取企业条件
	 * 
	 * @return
	 * 
	 * @author 廖安良
	 * @version 创建时间：2013-1-31 上午10:16:32
	 */
	public static String getWhereEnterpriseCode() {
		String enterpriseCode = LemapContext.getSingleDefault()
				.getAppParamContext().getValue(AppParamContext.EnterpriseCode)
				.toString();
		return String.format(" EnterpriseCode='%s'", enterpriseCode);
	}
}
