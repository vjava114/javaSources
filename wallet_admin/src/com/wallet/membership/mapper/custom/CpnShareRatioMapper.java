package com.wallet.membership.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.CpnShareRatio;

public interface CpnShareRatioMapper {

	/**
	 * @Method Name : selectCouponAccumulationList
	 * @Description : 쿠폰별 정산 상세 조회(분담율)
	 * @param : HashMap<String ,Object>
	 * @return : List<CpnShareRatio>
	 * @author 김태리
	 * @since 2012.09.28
	 */
	List<CpnShareRatio> selectCouponAccumulationList(HashMap<String ,Object> params);

}
