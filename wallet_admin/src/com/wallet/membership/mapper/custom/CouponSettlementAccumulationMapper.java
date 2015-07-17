package com.wallet.membership.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.CouponSettlementAccumulation;

public interface CouponSettlementAccumulationMapper {
	
	/**
	 * @Method Name : select CouponSettlementAccumulationList
	 * @Description : °áÁ¦ Á¤»ê Á¶È¸
	 * @param : HashMap<String ,Object>
	 * @return : List<HashMap<String, Object>>
	 * @author ±è¿Ï¼·
	 * @since 2012.09.28
	 */
	List<CouponSettlementAccumulation> selectCouponSettlementAccumulationList(HashMap<String ,Object> params);

	/**
	 * @Method Name : select CouponSettlementAccumulationListCnt
	 * @Description : °áÁ¦ Á¤»ê ¼ö Á¶È¸
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ±è¿Ï¼·
	 * @since 2012.09.28
	 */
	int selectCouponSettlementAccumulationListCnt(HashMap<String ,Object> params);



}
