package com.wallet.membership.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.CouponShare;
import com.wallet.membership.model.custom.MemberAccumulation;
import com.wallet.membership.model.custom.PartnerAccumulation;

public interface PartnerAccumulationMapper {

	/**
	 * @Method Name : selectCouponStaticDayList
	 * @Description : ���� ��ȸ ����ʺ� ���� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<CouponStatic>
	 * @author ������
	 * @since 2012.09.26
	 */
	List<PartnerAccumulation> selectPartnerAccumulationList(HashMap<String ,Object> params);

	int selectPartnerAccumulationListCount(HashMap<String ,Object> params);

}
