package com.wallet.membership.dao.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.membership.mapper.custom.CouponStaticMapper;
import com.wallet.membership.model.custom.CouponStatic;

public class CouponStaticDao extends MybatisCilent implements CouponStaticMapper{
private String preMapperName = "com.wallet.membership.mapper.custom.CouponStaticMapper.";

	
	/**
	 * @Method Name : CouponStaticDao
	 * @Description : ������
	 * @param : 
	 * @return : 
	 * @author ��ϼ�
	 * @since 2012.09.26
	 */
	public CouponStaticDao() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @Method Name : selectCouponStaticDayList
	 * @Description : �������>������(�Ϻ�) ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<CouponStatic>
	 * @author ��ϼ�
	 * @since 2012.09.26
	 */
	@SuppressWarnings("unchecked")
	public List<CouponStatic> selectCouponStaticDayList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectCouponStaticDayList", params);
	}
	
	
	/**
	 * @Method Name : selectCouponStaticMonthList
	 * @Description : �������>������(����) ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<HashMap<String, Object>>
	 * @author ��ϼ�
	 * @since 2012.09.26
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> selectCouponStaticMonthList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectCouponStaticMonthList", params);
	}
	
	
}
