package com.wallet.membership.service.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.dao.custom.CouponSettlementAccumulationDao;
import com.wallet.membership.model.custom.CouponSettlementAccumulation;

public class CouponSettlementAccumulationService {
	private final CouponSettlementAccumulationDao sDao;
	/**
	 * @Method Name : CouponSettlementAccumulationService
	 * @Description : ������
	 * @param : 
	 * @return : 
	 * @author ��ϼ�
	 * @since 2012.09.28
	 */
	
	public CouponSettlementAccumulationService() {
		sDao = new CouponSettlementAccumulationDao();
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
	 * @Method Name : selectCouponSettlementAccumulationList
	 * @Description : ������ ���� ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<HashMap<String, Object>>
	 * @author ��ϼ�
	 * @since 2012.09.28
	 */
	public List<CouponSettlementAccumulation> selectCouponSettlementAccumulationList(HashMap<String ,Object> params) {
		List<CouponSettlementAccumulation> result = null;
		
		result = sDao.selectCouponSettlementAccumulationList(params); 

		return result;
	}
	
	/**
	 * @Method Name : selectCouponSettlementAccumulationListCnt
	 * @Description : ������ ���� ��� �� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<HashMap<String, Object>>
	 * @author ��ϼ�
	 * @since 2012.09.28
	 */
	public int selectCouponSettlementAccumulationListCnt(HashMap<String, Object> params) {
		return sDao.selectCouponSettlementAccumulationListCnt(params);
	}
}