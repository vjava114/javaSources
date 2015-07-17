package com.wallet.membership.service.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.dao.custom.CouponStaticDao;
import com.wallet.membership.model.custom.CouponStatic;

public class CouponStaticService {
	private final CouponStaticDao sDao;

	/**
	 * @Method Name : CouponStaticService
	 * @Description : 생성자
	 * @param : 
	 * @return : 
	 * @author 김완섭
	 * @since 2012.09.24
	 */
	public CouponStaticService() {
		sDao = new CouponStaticDao();
	}
	

	/**
	 * @Method Name : selectCouponStaticDayList
	 * @Description : 쿠폰별통계>사용통계(일별) 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<CouponStatic>
	 * @author 김완섭
	 * @since 2012.09.26
	 */
	public List<CouponStatic> selectCouponStaticDayList(HashMap<String ,Object> params) {
		List<CouponStatic> result = null;
		
		result = sDao.selectCouponStaticDayList(params);

		return result;
	}
	
	
	/**
	 * @Method Name : selectCouponStaticMonthList
	 * @Description :쿠폰별통계>사용통계(월별) 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author 김완섭
	 * @since 2012.09.25
	 */
	public  List<HashMap<String, Object>> selectCouponStaticMonthList(HashMap<String ,Object> params) {
		 List<HashMap<String, Object>> result = null;
		
		result = sDao.selectCouponStaticMonthList(params); 

		return result;
	}
	
	
}
