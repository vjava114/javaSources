package com.wallet.membership.service.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.dao.custom.CouponAgeStaticDao;
import com.wallet.membership.model.custom.CouponAgeStatic;

public class CouponAgeStaticService {
	private final CouponAgeStaticDao sDao;

	/**
	 * @Method Name : CouponAgeStaticService
	 * @Description : ������
	 * @param : 
	 * @return : 
	 * @author ��ϼ�
	 * @since 2012.09.24
	 */
	public CouponAgeStaticService() {
		sDao = new CouponAgeStaticDao();
	}
	

	/**
	 * @Method Name : selectCouponAgeStaticDayList
	 * @Description : ���������>����/����(�Ϻ�) �ٿ�ε� ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<CouponAgeStatic>
	 * @author ��ϼ�
	 * @since 2012.09.26
	 */
	public List<CouponAgeStatic> selectCouponAgeStaticDayList(HashMap<String ,Object> params) {
		List<CouponAgeStatic> result = null;
		
		result = sDao.selectCouponAgeStaticDayList(params);

		return result;
	}
	
	
	
	/**
	 * @Method Name : selectCouponAgeStaticMonthList
	 * @Description : ���������>����/����(����) �ٿ�ε� ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<HashMap<String, Object>>
	 * @author ���¸�
	 * @since 2012.09.28
	 */
	public List<HashMap<String, Object>> selectCouponAgeStaticMonthList(HashMap<String ,Object> params) {
		List<HashMap<String, Object>> result = null;
		
		result = sDao.selectCouponAgeStaticMonthList(params); //-- �̺�Ʈ/�������� ����� ��ȸ��.

		return result;
	}
}
