package com.wallet.membership.service.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.CouponAccumulation;
import com.wallet.membership.model.custom.CpnShareRatio;
import com.wallet.membership.dao.custom.CouponAccumulationDao;

public class CouponAccumulationService {
	private final CouponAccumulationDao sDao;
	
	public CouponAccumulationService(){
		sDao = new CouponAccumulationDao();
	}

	/**
	 * @Method Name : commit
	 * @Description : 
	 * @param : 
	 * @return : void
	 * @author 김태리
	 * @since 2012.09.21
	 */
	public void commit(){
		sDao.commit();
	}
	
	/**
	 * @Method Name : rollback
	 * @Description :
	 * @param : 
	 * @return : 
	 * @author 김태리
	 * @since 2012.09.14
	 */
	public void rollback(){
		sDao.rollback();
	}
	
	/**
	 * @Method Name : selectCouponAccumulationList
	 * @Description : 쿠폰별 정산 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<CouponAccumulation>
	 * @author 김태리
	 * @since 2012.09.21
	 */
	public List<CouponAccumulation> couponAccumulationList(HashMap<String ,Object> params) {
		List<CouponAccumulation> result = null;
		
		result = sDao.selectCouponAccumulationList(params); //-- 멤버십 목록을 조회함.

		return result;
	}
	
	/**
	 * @Method Name : selectCouponAccumulationList
	 * @Description : 쿠폰별 정산 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<CouponAccumulation>
	 * @author 김태리
	 * @since 2012.09.21
	 */
	public int couponAccumulationListCnt(HashMap<String, Object> params) {
		return sDao.selectCouponAccumulationListCnt(params);
	}

	/**
	 * @Method Name : selectCouponAccumulationDetail
	 * @Description : 쿠폰별 정산 상세 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<ComplexCoupon>
	 * @author 김태리
	 * @since 2012.09.21
	 */
	public List<CpnShareRatio> selectCouponAccumulationDetail(HashMap<String, Object> params){
		List<CpnShareRatio> result = null;
		
		result = sDao.selectCouponAccumulationDetail(params);
		
		return result;
	}
}
