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
	 * @Description : 생성자
	 * @param : 
	 * @return : 
	 * @author 김완섭
	 * @since 2012.09.25
	 */
	public CouponOsStaticDao() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @Method Name : selectCouponOsStaticDayList
	 * @Description : 쿠폰통계>단말기OS/통신사별(일별) 다운로드 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<CouponOsStatic>
	 * @author 김완섭
	 * @since 2012.09.25
	 */
	@SuppressWarnings("unchecked")
	public List<CouponOsStatic> selectCouponOsStaticDayList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectCouponOsStaticDayList", params);
	}
	
	
	/**
	 * @Method Name : selectCouponOsStaticMonthList
	 * @Description : 쿠폰통계>단말기OS/통신사별(월별) 다운로드 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<MCouponOsStatic>
	 * @author 김완섭
	 * @since 2012.09.25
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> selectCouponOsStaticMonthList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectCouponOsStaticMonthList", params);
	}
	
	
}
