package com.wallet.membership.service.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.dao.custom.CouponOsStaticDao;
import com.wallet.membership.model.custom.CouponOsStatic;

public class CouponOsStaticService {
	private final CouponOsStaticDao sDao;

	/**
	 * @Method Name : CouponOsStaticService
	 * @Description : 생성자
	 * @param : 
	 * @return : 
	 * @author 김완섭
	 * @since 2012.09.24
	 */
	public CouponOsStaticService() {
		sDao = new CouponOsStaticDao();
	}
	

	/**
	 * @Method Name : selectCouponOsStaticDayList
	 * @Description : 쿠폰별통계>단말기OS/통신사별(일별) 다운로드 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<CouponOsStatic>
	 * @author 김완섭
	 * @since 2012.09.25
	 */
	public List<CouponOsStatic> selectCouponOsStaticDayList(HashMap<String ,Object> params) {
		List<CouponOsStatic> result = null;
		
		result = sDao.selectCouponOsStaticDayList(params);

		return result;
	}
	
	
	/**
	 * @Method Name : selectCouponOsStaticMonthList
	 * @Description : 쿠폰별통계>단말기OS/통신사별(월별) 다운로드 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author 김완섭
	 * @since 2012.09.25
	 */
	public List<HashMap<String, Object>> selectCouponOsStaticMonthList(HashMap<String ,Object> params) {
		List<HashMap<String, Object>> result = null;
		
		result = sDao.selectCouponOsStaticMonthList(params); 

		return result;
	}
	
	
}
