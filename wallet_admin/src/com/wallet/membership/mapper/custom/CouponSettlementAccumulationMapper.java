package com.wallet.membership.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.CouponSettlementAccumulation;

public interface CouponSettlementAccumulationMapper {
	
	/**
	 * @Method Name : select CouponSettlementAccumulationList
	 * @Description : ���� ���� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<HashMap<String, Object>>
	 * @author ��ϼ�
	 * @since 2012.09.28
	 */
	List<CouponSettlementAccumulation> selectCouponSettlementAccumulationList(HashMap<String ,Object> params);

	/**
	 * @Method Name : select CouponSettlementAccumulationListCnt
	 * @Description : ���� ���� �� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ��ϼ�
	 * @since 2012.09.28
	 */
	int selectCouponSettlementAccumulationListCnt(HashMap<String ,Object> params);



}
