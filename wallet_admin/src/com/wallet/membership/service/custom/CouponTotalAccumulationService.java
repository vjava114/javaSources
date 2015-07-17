package com.wallet.membership.service.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.CouponTotalAccumulation;
import com.wallet.membership.dao.custom.CouponTotalAccumulationDao;

public class CouponTotalAccumulationService {
	private final CouponTotalAccumulationDao sDao;
	
	/**
	 * @Method Name : CouponTotalAccumulationService
	 * @Description : 생성자
	 * @param : 
	 * @return : 
	 * @author 김태리
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
	 * @author 김태리
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
	 * @author 김태리
	 * @since 2012.09.28
	 */
	
	public void rollback() {
		sDao.rollback();
	}
	
	/**
	 * @Method Name : selectSettlementList
	 * @Description : 결제사 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<HashMap<String, Object>>
	 * @author 김태리
	 * @since 2012.09.28
	 */
	public List<CouponTotalAccumulation> selectCouponTotalAccumulationList(HashMap<String ,Object> params) {
		List<CouponTotalAccumulation> result = null;
		
		result = sDao.selectCouponTotalAccumulationList(params); //-- 이벤트/공지사항 목록을 조회함.

		return result;
	}
}
