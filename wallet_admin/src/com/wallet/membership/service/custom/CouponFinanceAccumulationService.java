package com.wallet.membership.service.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.dao.custom.CouponFinanceAccumulationDao;
import com.wallet.membership.model.custom.CouponFinanceAccumulation;

public class CouponFinanceAccumulationService {
	private final CouponFinanceAccumulationDao sDao;
	/**
	 * @Method Name : CouponFinanceAccumulationService
	 * @Description : ������
	 * @param : 
	 * @return : 
	 * @author ��ϼ�
	 * @since 2012.09.28
	 */
	
	public CouponFinanceAccumulationService() {
		sDao = new CouponFinanceAccumulationDao();
	}
	
	/**
	 * @Method Name : commit
	 * @Description : 
	 * @param : 
	 * @return : void
	 * @author ��ϼ�
	 * @since 2012.09.28
	 */
	
	public void commit() {
		sDao.commit();
	}
	
	/**
	 * @Method Name : rollback
	 * @Description : 
	 * @param : 
	 * @return : void
	 * @author ��ϼ�
	 * @since 2012.09.28
	 */
	
	public void rollback() {
		sDao.rollback();
	}
	
	/**
	 * @Method Name : selectCouponFinanceAccumulationList
	 * @Description : ������ ���� ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<HashMap<String, Object>>
	 * @author ��ϼ�
	 * @since 2012.09.28
	 */
	public List<CouponFinanceAccumulation> selectCouponFinanceAccumulationList(HashMap<String ,Object> params) {
		List<CouponFinanceAccumulation> result = null;
		
		result = sDao.selectCouponFinanceAccumulationList(params); 

		return result;
	}
	/**
	 * @Method Name : selectCouponFinanceAccumulationListCnt
	 * @Description : ������ ���� �� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<HashMap<String, Object>>
	 * @author ��ϼ�
	 * @since 2012.09.28
	 */
	public int selectCouponFinanceAccumulationListCnt(HashMap<String, Object> params) {
		return sDao.selectCouponFinanceAccumulationListCnt(params);
	}
	
}