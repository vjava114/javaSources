package com.wallet.membership.dao.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.membership.mapper.custom.CouponAccumulationMapper;
import com.wallet.membership.mapper.custom.MemberAccumulationMapper;
import com.wallet.membership.model.custom.CouponAccumulation;
import com.wallet.membership.model.custom.CpnShareRatio;
import com.wallet.membership.model.custom.MemberAccumulation;

public class MemberAccumulationDao extends MybatisCilent implements MemberAccumulationMapper {

	private String preMapperName = "com.wallet.membership.mapper.custom.MemberAccumulationMapper.";
	

	public void commit() {
		sqlMapper.commit();
	}

	public void rollback(){
		sqlMapper.rollback();
	}

	public MemberAccumulationDao() {
		// TODO Auto-generated constructor stub
	}

	public List<MemberAccumulation> selectMemberAccumulationList(HashMap<String, Object> params) {
		// TODO Auto-generated method stub
		return sqlMapper.selectList(preMapperName + "selectMemberAccumulationList", params);
	}

	public int selectMemberAccumulationListCount(HashMap<String, Object> params) {
		// TODO Auto-generated method stub
		return (Integer) sqlMapper.selectOne("com.wallet.membership.mapper.custom.MemberAccumulationMapper.selectMemberAccumulationListCount", params);
	}
	

	
}
