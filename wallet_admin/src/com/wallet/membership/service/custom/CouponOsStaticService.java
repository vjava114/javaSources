package com.wallet.membership.service.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.dao.custom.CouponOsStaticDao;
import com.wallet.membership.model.custom.CouponOsStatic;

public class CouponOsStaticService {
	private final CouponOsStaticDao sDao;

	/**
	 * @Method Name : CouponOsStaticService
	 * @Description : ������
	 * @param : 
	 * @return : 
	 * @author ��ϼ�
	 * @since 2012.09.24
	 */
	public CouponOsStaticService() {
		sDao = new CouponOsStaticDao();
	}
	

	/**
	 * @Method Name : selectCouponOsStaticDayList
	 * @Description : ���������>�ܸ���OS/��Ż纰(�Ϻ�) �ٿ�ε� ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<CouponOsStatic>
	 * @author ��ϼ�
	 * @since 2012.09.25
	 */
	public List<CouponOsStatic> selectCouponOsStaticDayList(HashMap<String ,Object> params) {
		List<CouponOsStatic> result = null;
		
		result = sDao.selectCouponOsStaticDayList(params);

		return result;
	}
	
	
	/**
	 * @Method Name : selectCouponOsStaticMonthList
	 * @Description : ���������>�ܸ���OS/��Ż纰(����) �ٿ�ε� ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author ��ϼ�
	 * @since 2012.09.25
	 */
	public List<HashMap<String, Object>> selectCouponOsStaticMonthList(HashMap<String ,Object> params) {
		List<HashMap<String, Object>> result = null;
		
		result = sDao.selectCouponOsStaticMonthList(params); 

		return result;
	}
	
	
}
