package com.wallet.membership.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.CouponShare;
import com.wallet.membership.model.custom.MemberAccumulation;

public interface MemberAccumulationMapper {

	/**
	 * @Method Name : selectCouponStaticDayList
	 * @Description : 정산 조회 멤버십별 정산 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<CouponStatic>
	 * @author 이정인
	 * @since 2012.09.26
	 */
	List<MemberAccumulation> selectMemberAccumulationList(HashMap<String ,Object> params);

	int selectMemberAccumulationListCount(HashMap<String ,Object> params);

}
