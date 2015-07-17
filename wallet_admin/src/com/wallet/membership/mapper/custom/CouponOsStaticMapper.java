package com.wallet.membership.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.CouponOsStatic;

public interface CouponOsStaticMapper {
	/**
	 * @Method Name : selectCouponOsStaticDayList
	 * @Description : 쿠폰별통계>단말기OS/통신사별다운로드(일별) 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<CouponOsStatic>
	 * @author 김완섭
	 * @since 2012.09.25
	 */
	List<CouponOsStatic> selectCouponOsStaticDayList(HashMap<String ,Object> params);
	
	
	/**
	 * @Method Name : selectCouponOsStaticMonthList
	 * @Description : 쿠폰별통계>단말기OS/통신사별다운로드(월별) 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<CouponOsStatic>
	 * @author 김완섭
	 * @since 2012.09.25
	 */
	List<HashMap<String, Object>> selectCouponOsStaticMonthList(HashMap<String ,Object> params);
	
	
}
