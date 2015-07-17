package com.wallet.membership.dao.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.membership.mapper.custom.CouponAgeStaticMapper;
import com.wallet.membership.model.custom.CouponAgeStatic;

public class CouponAgeStaticDao extends MybatisCilent implements CouponAgeStaticMapper{
private String preMapperName = "com.wallet.membership.mapper.custom.CouponAgeStaticMapper.";

	
	/**
	 * @Method Name : CouponAgeStaticDao
	 * @Description : ������
	 * @param : 
	 * @return : 
	 * @author ��ϼ�
	 * @since 2012.09.26
	 */
	public CouponAgeStaticDao() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @Method Name : selectCouponAgeStaticDayList
	 * @Description : �������>����/����(�Ϻ�) �ٿ�ε� ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<CouponAgeStatic>
	 * @author ��ϼ�
	 * @since 2012.09.26
	 */
	@SuppressWarnings("unchecked")
	public List<CouponAgeStatic> selectCouponAgeStaticDayList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectCouponAgeStaticDayList", params);
	}
	
	
	/**
	 * @Method Name : selectCouponAgeStaticMonthList
	 * @Description : �������>����/����(����) �ٿ�ε� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<HashMap<String, Object>>
	 * @author ���¸�
	 * @since 2012.09.26
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> selectCouponAgeStaticMonthList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectCouponAgeStaticMonthList", params);
	}
	
}
