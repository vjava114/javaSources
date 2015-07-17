package com.wallet.membership.dao.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.membership.mapper.custom.CouponOsStaticMapper;
import com.wallet.membership.model.custom.CouponOsStatic;

public class CouponOsStaticDao extends MybatisCilent implements CouponOsStaticMapper{
private String preMapperName = "com.wallet.membership.mapper.custom.CouponOsStaticMapper.";

	
	/**
	 * @Method Name : CouponOsStaticDao
	 * @Description : ������
	 * @param : 
	 * @return : 
	 * @author ��ϼ�
	 * @since 2012.09.25
	 */
	public CouponOsStaticDao() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @Method Name : selectCouponOsStaticDayList
	 * @Description : �������>�ܸ���OS/��Ż纰(�Ϻ�) �ٿ�ε� ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<CouponOsStatic>
	 * @author ��ϼ�
	 * @since 2012.09.25
	 */
	@SuppressWarnings("unchecked")
	public List<CouponOsStatic> selectCouponOsStaticDayList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectCouponOsStaticDayList", params);
	}
	
	
	/**
	 * @Method Name : selectCouponOsStaticMonthList
	 * @Description : �������>�ܸ���OS/��Ż纰(����) �ٿ�ε� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<MCouponOsStatic>
	 * @author ��ϼ�
	 * @since 2012.09.25
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> selectCouponOsStaticMonthList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectCouponOsStaticMonthList", params);
	}
	
	
}
