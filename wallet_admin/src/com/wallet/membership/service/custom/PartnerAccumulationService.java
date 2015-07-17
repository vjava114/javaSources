package com.wallet.membership.service.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.Log;
import com.wallet.membership.model.custom.CouponAccumulation;
import com.wallet.membership.model.custom.CouponShare;
import com.wallet.membership.model.custom.CpnShareRatio;
import com.wallet.membership.model.custom.MemberAccumulation;
import com.wallet.membership.model.custom.PartnerAccumulation;
import com.wallet.membership.dao.custom.CouponAccumulationDao;
import com.wallet.membership.dao.custom.MemberAccumulationDao;
import com.wallet.membership.dao.custom.PartnerAccumulationDao;

public class PartnerAccumulationService {
	private final PartnerAccumulationDao sDao;
	
	public PartnerAccumulationService(){
		sDao = new PartnerAccumulationDao();
	}

	public void commit(){
		sDao.commit();
	}
	
	public void rollback(){
		sDao.rollback();
	}
	
	/**
	 * @Method Name : selectMemberAccumulationList
	 * @Description : ����ʺ� ���� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<CouponAccumulation>
	 * @author ������
	 * @since 2012.09.21
	 */
	

	public List<PartnerAccumulation> selectPartnerAccumulationList(HashMap<String ,Object> params) {
		List<PartnerAccumulation> result = null;
		
		result = sDao.selectPartnerAccumulationList(params); //-- ����� ����� ��ȸ��.

		return result;
	}
	
	public int getPartnerAccumulationListCountByExample(HashMap<String ,Object> params) {
		return sDao.selectPartnerAccumulationListCount(params);
	}


}
