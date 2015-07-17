package com.wallet.membership.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.ComplexCoupon;

public interface ComplexCouponMapper {

	/**
	 * @Method Name : selectComplexCouponList
	 * @Description : ���� ���հ��� ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<ComplexCoupon>
	 * @author ���¸�
	 * @since 2012.09.21
	 */
	List<ComplexCoupon> selectComplexCouponList(HashMap<String ,Object> params);

	
	/**
	 * @Method Name : selectComplexCouponListCnt
	 * @Description : ���� ���հ������� ��� �� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.21
	 */
	Integer selectComplexCouponListCnt(HashMap<String ,Object> params);
	


	
	/**
	 * @Method Name : selectComplexCouponInfo
	 * @Description : ���� ���հ������� ��� �� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : ComplexCoupon
	 * @author ���¸�
	 * @since 2012.09.24
	 */
	ComplexCoupon selectComplexCouponInfo(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : insertComplexCoupon
	 * @Description : ���� ���հ������� ���
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.21
	 */
	Integer insertComplexCoupon(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : updateComplexCoupon
	 * @Description : ���� ���հ������� ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.21
	 */
	Integer updateComplexCoupon(HashMap<String ,Object> params);

}
