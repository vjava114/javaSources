package com.wallet.membership.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.CouponAgeStatic;

public interface CouponAgeStaticMapper {
	/**
	 * @Method Name : selectCouponAgeStaticDayList
	 * @Description : 쿠폰별통계>연령/성별 다운로드(일별) 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<CouponAgeStatic>
	 * @author 김완섭
	 * @since 2012.09.25
	 */
	List<CouponAgeStatic> selectCouponAgeStaticDayList(HashMap<String ,Object> params);
	
	
	/**
	 * @Method Name : selectCouponAgeStaticMonthList
	 * @Description : 쿠폰별통계>연령/성별 다운로드(월별) 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<HashMap<String, Object>>
	 * @author 김완섭
	 * @since 2012.09.25
	 */
	List<HashMap<String, Object>> selectCouponAgeStaticMonthList(HashMap<String ,Object> params);
	
	
}
