package com.wallet.membership.dao.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.membership.mapper.custom.ComplexCouponMapper;
import com.wallet.membership.model.custom.ComplexCoupon;

public class ComplexCouponDao extends MybatisCilent implements ComplexCouponMapper {
	private String preMapperName = "com.wallet.membership.mapper.custom.ComplexCouponMapper.";

	/**
	 * @Method Name : commit
	 * @Description :
	 * @param : 
	 * @return : 
	 * @author ���¸�
	 * @since 2012.09.21
	 */
	public void commit() {
		sqlMapper.commit();
	}
	
	/**
	 * @Method Name : rollback
	 * @Description :
	 * @param : 
	 * @return : 
	 * @author ���¸�
	 * @since 2012.09.14
	 */
	public void rollback(){
		sqlMapper.rollback();
	}
	
	
	/**
	 * @Method Name : selectComplexCouponList
	 * @Description : ���� ���հ������� ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<ComplexCoupon>
	 * @author ���¸�
	 * @since 2012.09.21
	 */
	@SuppressWarnings("unchecked")
	public List<ComplexCoupon> selectComplexCouponList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectComplexCouponList", params);
	}
	
	
	/**
	 * @Method Name : selectComplexCouponListCnt
	 * @Description : ���� ���հ������� ��� �� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.21
	 */
	@SuppressWarnings("unchecked")
	public Integer selectComplexCouponListCnt(HashMap<String,Object> params){
		Integer cnt = 0;
		
		cnt = (Integer) sqlMapper.selectOne(preMapperName + "selectComplexCouponListCnt", params);
		return cnt;
	}
	
	/**
	 * @Method Name : selectComplexCouponInfo
	 * @Description : ���� ���հ������� ��� �� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : ComplexCoupon
	 * @author ���¸�
	 * @since 2012.09.21
	 */
	@SuppressWarnings("unchecked")
	public ComplexCoupon selectComplexCouponInfo(HashMap<String,Object> params){
		ComplexCoupon complexCoupon ;
		
		complexCoupon = (ComplexCoupon) sqlMapper.selectOne(preMapperName + "selectComplexCouponInfo", params);
		return complexCoupon;
	}
	
	/**
	 * @Method Name : selectAComplexCoupon
	 * @Description : ���� ���հ������� ��ȸ
	 * @return : List<ComplexCoupon>
	 * @author ���¸�
	 * @since 2012.09.21
	 */
	@SuppressWarnings("unchecked")
	public ComplexCoupon selectAComplexCoupon(HashMap<String,Object> params){
		return (ComplexCoupon) sqlMapper.selectOne(preMapperName + "selectComplexCouponInfo", params);
	}
	
	
	/**
	 * @Method Name : insertComplexCoupon
	 * @Description : ���� ���հ������� ���
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.21
	 */
	@SuppressWarnings("unchecked")
	public Integer insertComplexCoupon(HashMap<String,Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.insert(preMapperName + "insertComplexCoupon", params));
		return result;
	}
	
	
	/**
	 * @Method Name : updateComplexCoupon
	 * @Description : ���� ���հ������� ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.21
	 */
	@SuppressWarnings("unchecked")
	public Integer updateComplexCoupon(HashMap<String,Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.update(preMapperName + "updateComplexCoupon", params));
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public Integer updateCouponList(HashMap<String, Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.update(preMapperName + "updateCouponList", params));
		return result;
	}
	
	/**
	 * @Method Name : insertComplexStore
	 * @Description : �������Ѱ����� ����Ʈ ���
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.24
	 */
	@SuppressWarnings("unchecked")
	public Integer insertComplexStore(HashMap<String, Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.delete(preMapperName + "insertComplexStore", params));
		return result;
	}
	
	/**
	 * @Method Name : insertComplexBank
	 * @Description : �������� ������ ����Ʈ ���
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.24
	 */
	@SuppressWarnings("unchecked")
	public Integer insertComplexBank(HashMap<String, Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.delete(preMapperName + "insertComplexBank", params));
		return result;
	}
	
	/**
	 * @Method Name : insertComplexPayComp
	 * @Description : �������� ������ ����Ʈ ���
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.24
	 */
	@SuppressWarnings("unchecked")
	public Integer insertComplexPayComp(HashMap<String, Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.delete(preMapperName + "insertComplexPayComp", params));
		return result;
	}
	
	/**
	 * @Method Name : deleteComplexStore
	 * @Description : �������Ѱ����� ����Ʈ  ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.24
	 */
	@SuppressWarnings("unchecked")
	public Integer deleteComplexStore(HashMap<String, Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.delete(preMapperName + "deleteComplexStore", params));
		return result;
	}
	
	/**
	 * @Method Name : deleteComplexBank
	 * @Description : �������� ������ ����Ʈ ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.24
	 */
	@SuppressWarnings("unchecked")
	public Integer deleteComplexBank(HashMap<String, Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.delete(preMapperName + "deleteComplexBank", params));
		return result;
	}
	
	/**
	 * @Method Name : deleteComplexPayComp
	 * @Description : �������� ������ ����Ʈ ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.24
	 */
	@SuppressWarnings("unchecked")
	public Integer deleteComplexPayComp(HashMap<String, Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.delete(preMapperName + "deleteComplexPayComp", params));
		return result;
	}


	@SuppressWarnings("unchecked")
	public List<ComplexCoupon> selectComplexStore(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectComplexStore", params);
	}

	@SuppressWarnings("unchecked")
	public List<ComplexCoupon> selectComplexBank(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectComplexBank", params);
	}

	@SuppressWarnings("unchecked")
	public List<ComplexCoupon> selectComplexPayComp(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectComplexPayComp", params);
	}
	
}