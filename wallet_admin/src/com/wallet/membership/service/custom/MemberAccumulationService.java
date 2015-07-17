package com.wallet.membership.service.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.Log;
import com.wallet.membership.model.custom.CouponAccumulation;
import com.wallet.membership.model.custom.CouponShare;
import com.wallet.membership.model.custom.CpnShareRatio;
import com.wallet.membership.model.custom.MemberAccumulation;
import com.wallet.membership.dao.custom.CouponAccumulationDao;
import com.wallet.membership.dao.custom.MemberAccumulationDao;

public class MemberAccumulationService {
	private final MemberAccumulationDao sDao;
	
	public MemberAccumulationService(){
		sDao = new MemberAccumulationDao();
	}

	public void commit(){
		sDao.commit();
	}
	
	public void rollback(){
		sDao.rollback();
	}
	
	/**
	 * @Method Name : selectMemberAccumulationList
	 * @Description : 멤버십별 정산 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<CouponAccumulation>
	 * @author 이정인
	 * @since 2012.09.21
	 */
	

	public List<MemberAccumulation> selectMemberAccumulationList(HashMap<String ,Object> params) {
		List<MemberAccumulation> result = null;
		
		result = sDao.selectMemberAccumulationList(params); //-- 멤버십 목록을 조회함.

		return result;
	}
	
	public int getMemberAccumulationListCountByExample(HashMap<String ,Object> params) {
		return sDao.selectMemberAccumulationListCount(params);
	}


}
