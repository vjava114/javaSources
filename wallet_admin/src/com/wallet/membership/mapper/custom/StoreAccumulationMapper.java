package com.wallet.membership.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.CouponShare;
import com.wallet.membership.model.custom.MemberAccumulation;
import com.wallet.membership.model.custom.StoreAccumulation;

public interface StoreAccumulationMapper {

	/**
	 * @Method Name : selectCouponStaticDayList
	 * @Description : 정산 조회 가맹점별 정산 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<CouponStatic>
	 * @author 이정인
	 * @since 2012.09.26
	 */
	List<StoreAccumulation> selectStoreAccumulationList(HashMap<String ,Object> params);

	int selectStoreAccumulationListCount(HashMap<String ,Object> params);

}
