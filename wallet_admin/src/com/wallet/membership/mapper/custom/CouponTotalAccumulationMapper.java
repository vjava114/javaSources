package com.wallet.membership.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.CouponTotalAccumulation;

public interface CouponTotalAccumulationMapper {

	/**
	 * @Method Name : selectCouponTotalAccumulationList
	 * @Description : 통합통계 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<HashMap<String, Object>>
	 * @author 김태리
	 * @since 2012.09.28
	 */
	List<CouponTotalAccumulation> selectCouponTotalAccumulationList(HashMap<String ,Object> params);


}
