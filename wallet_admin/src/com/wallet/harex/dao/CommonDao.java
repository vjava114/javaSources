package com.wallet.harex.dao;

import java.util.HashMap;

import com.wallet.common.util.MybatisCilent;
import com.wallet.harex.mapper.CommonMapper;


public class CommonDao extends MybatisCilent implements CommonMapper {
	public static final String PREFIX_PROMO_ID = "PR";					// 프로모션 코드 이니셜
	public static final String PREFIX_RULE_ID = "RL";						// 오퍼링 룰 ID 이니셜
	
	public String getPromoId() {
		
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		params.put("text", PREFIX_PROMO_ID);					
		params.put("returnValue", "");
		
		sqlMapper.selectOne("com.wallet.harex.mapper.CommonMapper.getPromoId", params);
		return  (String)params.get("returnValue");

	}
	
	public String getRuleId() {
		
		HashMap<String,Object> params = new HashMap<String,Object>();
		
		params.put("text", PREFIX_RULE_ID);					
		params.put("returnValue", "");
		
		sqlMapper.selectOne("com.wallet.harex.mapper.CommonMapper.getPromoId", params);
		return  (String)params.get("returnValue");

	}
	
}
