package com.wallet.membership.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.ComplexCoupon;

public interface ComplexCouponMapper {

	/**
	 * @Method Name : selectComplexCouponList
	 * @Description : 쿠폰 복합결제 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<ComplexCoupon>
	 * @author 김태리
	 * @since 2012.09.21
	 */
	List<ComplexCoupon> selectComplexCouponList(HashMap<String ,Object> params);

	
	/**
	 * @Method Name : selectComplexCouponListCnt
	 * @Description : 쿠폰 복합결제정보 목록 수 조회
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2012.09.21
	 */
	Integer selectComplexCouponListCnt(HashMap<String ,Object> params);
	


	
	/**
	 * @Method Name : selectComplexCouponInfo
	 * @Description : 쿠폰 복합결제정보 목록 수 조회
	 * @param : HashMap<String ,Object>
	 * @return : ComplexCoupon
	 * @author 김태리
	 * @since 2012.09.24
	 */
	ComplexCoupon selectComplexCouponInfo(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : insertComplexCoupon
	 * @Description : 쿠폰 복합결제정보 등록
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2012.09.21
	 */
	Integer insertComplexCoupon(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : updateComplexCoupon
	 * @Description : 쿠폰 복합결제정보 수정
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2012.09.21
	 */
	Integer updateComplexCoupon(HashMap<String ,Object> params);

}
