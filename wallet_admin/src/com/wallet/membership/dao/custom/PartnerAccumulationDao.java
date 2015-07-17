package com.wallet.membership.dao.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.membership.mapper.custom.CouponAccumulationMapper;
import com.wallet.membership.mapper.custom.MemberAccumulationMapper;
import com.wallet.membership.mapper.custom.PartnerAccumulationMapper;
import com.wallet.membership.model.custom.CouponAccumulation;
import com.wallet.membership.model.custom.CpnShareRatio;
import com.wallet.membership.model.custom.MemberAccumulation;
import com.wallet.membership.model.custom.PartnerAccumulation;

public class PartnerAccumulationDao extends MybatisCilent implements PartnerAccumulationMapper {

	private String preMapperName = "com.wallet.membership.mapper.custom.PartnerAccumulationMapper.";
	

	public void commit() {
		sqlMapper.commit();
	}

	public void rollback(){
		sqlMapper.rollback();
	}

	public PartnerAccumulationDao() {
		// TODO Auto-generated constructor stub
	}

	public List<PartnerAccumulation> selectPartnerAccumulationList(HashMap<String, Object> params) {
		// TODO Auto-generated method stub
		return sqlMapper.selectList(preMapperName + "selectPartnerAccumulationList", params);
	}

	public int selectPartnerAccumulationListCount(HashMap<String, Object> params) {
		// TODO Auto-generated method stub
		return (Integer) sqlMapper.selectOne("com.wallet.membership.mapper.custom.PartnerAccumulationMapper.selectPartnerAccumulationListCount", params);
	}
	

	
}
