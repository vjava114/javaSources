package com.wallet.membership.service.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.dao.custom.ComplexCouponDao;
import com.wallet.membership.model.custom.ComplexCoupon;

public class ComplexCouponService {
	private final ComplexCouponDao sDao;

	/**
	 * @Method Name : ComplexCouponService
	 * @Description : ������
	 * @param : 
	 * @return : 
	 * @author ���¸�
	 * @since 2012.09.21
	 */
	public ComplexCouponService() {
		sDao = new ComplexCouponDao();
	}
	
	/**
	 * @Method Name : commit
	 * @Description : 
	 * @param : 
	 * @return : void
	 * @author ���¸�
	 * @since 2012.09.21
	 */
	public void commit(){
		sDao.commit();
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
		sDao.rollback();
	}
	
	/**
	 * @Method Name : selectComplexCouponList
	 * @Description : �����(ī������) ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<ComplexCoupon>
	 * @author ���¸�
	 * @since 2012.09.21
	 */
	public List<ComplexCoupon> selectComplexCouponList(HashMap<String ,Object> params) {
		List<ComplexCoupon> result = null;
		
		result = sDao.selectComplexCouponList(params); //-- ����� ����� ��ȸ��.

		return result;
	}
	
	/**
	 * @Method Name : selectComplexCouponListCnt
	 * @Description : �����(ī������) ��� �� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ���¸�
	 * @since 2012.09.21
	 */
	public int selectComplexCouponListCnt(HashMap<String, Object> params) {
		int result = 0;
		
		result = Integer.parseInt(sDao.selectComplexCouponListCnt(params).toString()); //-- ����� ����� ��ȸ��.

		return result;
	}

	
	/**
	 * @Method Name : selectAComplexCoupon
	 * @Description : �����(ī������) ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<ComplexCoupon>
	 * @author ���¸�
	 * @since 2012.09.21
	 */
	@SuppressWarnings("unchecked")
	public ComplexCoupon selectAComplexCoupon(HashMap<String,Object> params){
		ComplexCoupon aComplexCoupon = sDao.selectAComplexCoupon(params);
		
		return aComplexCoupon;
	}
	
	/**
	 * @Method Name : insertComplexCoupon
	 * @Description : �����(ī������) ���
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ���¸�
	 * @since 2012.09.21
	 */
	public int insertComplexCoupon(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.insertComplexCoupon(params);
		
		return result;
	}
	
	/**
	 * @Method Name : updateComplexCoupon
	 * @Description : �����(ī������) ����
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ���¸�
	 * @since 2012.09.21
	 */
	public int updateComplexCoupon(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.updateComplexCoupon(params);
		
		return result;
	}
	

	
	public int updateCouponList(HashMap<String, Object> params){
		int result = 0;
		result = sDao.updateCouponList(params);
		return result;
	}
	
	/**
	 * @Method Name : insertComplexStore
	 * @Description : �������Ѱ����� ����Ʈ ���
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ���¸�
	 * @since 2012.09.24
	 */
	public int insertComplexStore(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.insertComplexStore(params);
		
		return result;
	}
	
	/**
	 * @Method Name : insertComplexBank
	 * @Description : �������� ������ ����Ʈ ���
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ���¸�
	 * @since 2012.09.24
	 */
	public int insertComplexBank(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.insertComplexBank(params);
		
		return result;
	}
	
	/**
	 * @Method Name : insertComplexPayComp
	 * @Description : �������� ������ ����Ʈ ���
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ���¸�
	 * @since 2012.09.24
	 */
	public int insertComplexPayComp(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.insertComplexPayComp(params);
		
		return result;
	}
	
	/**
	 * @Method Name : deleteComplexStore
	 * @Description : �������Ѱ����� ����Ʈ ����
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ���¸�
	 * @since 2012.09.24
	 */
	public int deleteComplexStore(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.deleteComplexStore(params);
		
		return result;
	}
	
	/**
	 * @Method Name : deleteComplexBank
	 * @Description : �������� ������ ����Ʈ  ����
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ���¸�
	 * @since 2012.09.24
	 */
	public int deleteComplexBank(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.deleteComplexBank(params);
		
		return result;
	}
	
	/**
	 * @Method Name : deleteComplexPayComp
	 * @Description : �������� ������ ����Ʈ ����
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ���¸�
	 * @since 2012.09.24
	 */
	public int deleteComplexPayComp(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.deleteComplexPayComp(params);
		
		return result;
	}
	
	public List<ComplexCoupon> selectComplexStore(HashMap<String, Object> params){
		List<ComplexCoupon> result = null;
		
		result = sDao.selectComplexStore(params);

		return result;
		
	}
	
	public List<ComplexCoupon> selectComplexBank(HashMap<String, Object> params){
		List<ComplexCoupon> result = null;
		
		result = sDao.selectComplexBank(params);

		return result;
		
	}
	
	public List<ComplexCoupon> selectComplexPayComp(HashMap<String, Object> params){
		List<ComplexCoupon> result = null;
		
		result = sDao.selectComplexPayComp(params); 

		return result;
		
	}
}
