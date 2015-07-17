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
	 * @Description : 생성자
	 * @param : 
	 * @return : 
	 * @author 김완섭
	 * @since 2012.09.26
	 */
	public CouponAgeStaticDao() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @Method Name : selectCouponAgeStaticDayList
	 * @Description : 쿠폰통계>연령/성별(일별) 다운로드 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<CouponAgeStatic>
	 * @author 김완섭
	 * @since 2012.09.26
	 */
	@SuppressWarnings("unchecked")
	public List<CouponAgeStatic> selectCouponAgeStaticDayList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectCouponAgeStaticDayList", params);
	}
	
	
	/**
	 * @Method Name : selectCouponAgeStaticMonthList
	 * @Description : 쿠폰통계>연령/성별(월별) 다운로드 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<HashMap<String, Object>>
	 * @author 김태리
	 * @since 2012.09.26
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> selectCouponAgeStaticMonthList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectCouponAgeStaticMonthList", params);
	}
	
}
