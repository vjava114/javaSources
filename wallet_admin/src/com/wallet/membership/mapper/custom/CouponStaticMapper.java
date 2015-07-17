package com.wallet.membership.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.CouponStatic;

public interface CouponStaticMapper {
	/**
	 * @Method Name : selectCouponStaticDayList
	 * @Description : 쿠폰별통계>사용통계(일별) 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<CouponStatic>
	 * @author 김완섭
	 * @since 2012.09.26
	 */
	List<CouponStatic> selectCouponStaticDayList(HashMap<String ,Object> params);
	
	
	/**
	 * @Method Name : selectCouponStaticMonthList
	 * @Description : 쿠폰별통계>사용통계(월별) 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<CouponStatic>
	 * @author 김완섭
	 * @since 2012.09.26
	 */
	List<HashMap<String, Object>> selectCouponStaticMonthList(HashMap<String ,Object> params);
	
	
}
