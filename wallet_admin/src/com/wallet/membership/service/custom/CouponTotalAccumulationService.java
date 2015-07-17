package com.wallet.membership.service.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.CouponTotalAccumulation;
import com.wallet.membership.dao.custom.CouponTotalAccumulationDao;

public class CouponTotalAccumulationService {
	private final CouponTotalAccumulationDao sDao;
	
	/**
	 * @Method Name : CouponTotalAccumulationService
	 * @Description : ������
	 * @param : 
	 * @return : 
	 * @author ���¸�
	 * @since 2012.09.28
	 */
	
	public CouponTotalAccumulationService() {
		sDao = new CouponTotalAccumulationDao();
	}
	
	/**
	 * @Method Name : commit
	 * @Description : 
	 * @param : 
	 * @return : void
	 * @author ���¸�
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
	 * @author ���¸�
	 * @since 2012.09.28
	 */
	
	public void rollback() {
		sDao.rollback();
	}
	
	/**
	 * @Method Name : selectSettlementList
	 * @Description : ������ ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<HashMap<String, Object>>
	 * @author ���¸�
	 * @since 2012.09.28
	 */
	public List<CouponTotalAccumulation> selectCouponTotalAccumulationList(HashMap<String ,Object> params) {
		List<CouponTotalAccumulation> result = null;
		
		result = sDao.selectCouponTotalAccumulationList(params); //-- �̺�Ʈ/�������� ����� ��ȸ��.

		return result;
	}
}
