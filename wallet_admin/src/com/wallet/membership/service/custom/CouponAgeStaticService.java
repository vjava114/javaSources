package com.wallet.membership.service.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.dao.custom.CouponAgeStaticDao;
import com.wallet.membership.model.custom.CouponAgeStatic;

public class CouponAgeStaticService {
	private final CouponAgeStaticDao sDao;

	/**
	 * @Method Name : CouponAgeStaticService
	 * @Description : 생성자
	 * @param : 
	 * @return : 
	 * @author 김완섭
	 * @since 2012.09.24
	 */
	public CouponAgeStaticService() {
		sDao = new CouponAgeStaticDao();
	}
	

	/**
	 * @Method Name : selectCouponAgeStaticDayList
	 * @Description : 쿠폰별통계>연령/성별(일별) 다운로드 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<CouponAgeStatic>
	 * @author 김완섭
	 * @since 2012.09.26
	 */
	public List<CouponAgeStatic> selectCouponAgeStaticDayList(HashMap<String ,Object> params) {
		List<CouponAgeStatic> result = null;
		
		result = sDao.selectCouponAgeStaticDayList(params);

		return result;
	}
	
	
	
	/**
	 * @Method Name : selectCouponAgeStaticMonthList
	 * @Description : 쿠폰별통계>연령/성별(월별) 다운로드 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<HashMap<String, Object>>
	 * @author 김태리
	 * @since 2012.09.28
	 */
	public List<HashMap<String, Object>> selectCouponAgeStaticMonthList(HashMap<String ,Object> params) {
		List<HashMap<String, Object>> result = null;
		
		result = sDao.selectCouponAgeStaticMonthList(params); //-- 이벤트/공지사항 목록을 조회함.

		return result;
	}
}
