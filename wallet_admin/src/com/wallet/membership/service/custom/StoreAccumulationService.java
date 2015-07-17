package com.wallet.membership.service.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.Log;
import com.wallet.membership.model.custom.CouponAccumulation;
import com.wallet.membership.model.custom.CouponShare;
import com.wallet.membership.model.custom.CpnShareRatio;
import com.wallet.membership.model.custom.MemberAccumulation;
import com.wallet.membership.model.custom.StoreAccumulation;
import com.wallet.membership.dao.custom.CouponAccumulationDao;
import com.wallet.membership.dao.custom.MemberAccumulationDao;
import com.wallet.membership.dao.custom.StoreAccumulationDao;

public class StoreAccumulationService {
	private final StoreAccumulationDao sDao;
	
	public StoreAccumulationService(){
		sDao = new StoreAccumulationDao();
	}

	public void commit(){
		sDao.commit();
	}
	
	public void rollback(){
		sDao.rollback();
	}
	
	/**
	 * @Method Name : selecStoreAccumulationList
	 * @Description : 멤버십별 정산 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<CouponAccumulation>
	 * @author 이정인
	 * @since 2012.09.21
	 */
	

	public List<StoreAccumulation> selectStoreAccumulationList(HashMap<String ,Object> params) {
		List<StoreAccumulation> result = null;
		
		result = sDao.selectStoreAccumulationList(params); //-- 멤버십 목록을 조회함.

		return result;
	}
	
	public int getStoreAccumulationListCountByExample(HashMap<String ,Object> params) {
		return sDao.selectStoreAccumulationListCount(params);
	}


}
