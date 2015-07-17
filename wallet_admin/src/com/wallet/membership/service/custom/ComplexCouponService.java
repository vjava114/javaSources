package com.wallet.membership.service.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.dao.custom.ComplexCouponDao;
import com.wallet.membership.model.custom.ComplexCoupon;

public class ComplexCouponService {
	private final ComplexCouponDao sDao;

	/**
	 * @Method Name : ComplexCouponService
	 * @Description : 생성자
	 * @param : 
	 * @return : 
	 * @author 김태리
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
	 * @author 김태리
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
	 * @author 김태리
	 * @since 2012.09.14
	 */
	public void rollback(){
		sDao.rollback();
	}
	
	/**
	 * @Method Name : selectComplexCouponList
	 * @Description : 멤버십(카드정보) 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<ComplexCoupon>
	 * @author 김태리
	 * @since 2012.09.21
	 */
	public List<ComplexCoupon> selectComplexCouponList(HashMap<String ,Object> params) {
		List<ComplexCoupon> result = null;
		
		result = sDao.selectComplexCouponList(params); //-- 멤버십 목록을 조회함.

		return result;
	}
	
	/**
	 * @Method Name : selectComplexCouponListCnt
	 * @Description : 멤버십(카드정보) 목록 수 조회
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2012.09.21
	 */
	public int selectComplexCouponListCnt(HashMap<String, Object> params) {
		int result = 0;
		
		result = Integer.parseInt(sDao.selectComplexCouponListCnt(params).toString()); //-- 멤버십 목록을 조회함.

		return result;
	}

	
	/**
	 * @Method Name : selectAComplexCoupon
	 * @Description : 멤버십(카드정보) 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<ComplexCoupon>
	 * @author 김태리
	 * @since 2012.09.21
	 */
	@SuppressWarnings("unchecked")
	public ComplexCoupon selectAComplexCoupon(HashMap<String,Object> params){
		ComplexCoupon aComplexCoupon = sDao.selectAComplexCoupon(params);
		
		return aComplexCoupon;
	}
	
	/**
	 * @Method Name : insertComplexCoupon
	 * @Description : 멤버십(카드정보) 등록
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2012.09.21
	 */
	public int insertComplexCoupon(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.insertComplexCoupon(params);
		
		return result;
	}
	
	/**
	 * @Method Name : updateComplexCoupon
	 * @Description : 멤버십(카드정보) 수정
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
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
	 * @Description : 쿠폰제한가맹점 리스트 등록
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2012.09.24
	 */
	public int insertComplexStore(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.insertComplexStore(params);
		
		return result;
	}
	
	/**
	 * @Method Name : insertComplexBank
	 * @Description : 쿠폰제한 금융사 리스트 등록
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2012.09.24
	 */
	public int insertComplexBank(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.insertComplexBank(params);
		
		return result;
	}
	
	/**
	 * @Method Name : insertComplexPayComp
	 * @Description : 쿠폰제한 결제사 리스트 등록
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2012.09.24
	 */
	public int insertComplexPayComp(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.insertComplexPayComp(params);
		
		return result;
	}
	
	/**
	 * @Method Name : deleteComplexStore
	 * @Description : 쿠폰제한가맹점 리스트 삭제
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2012.09.24
	 */
	public int deleteComplexStore(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.deleteComplexStore(params);
		
		return result;
	}
	
	/**
	 * @Method Name : deleteComplexBank
	 * @Description : 쿠폰제한 금융사 리스트  삭제
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2012.09.24
	 */
	public int deleteComplexBank(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.deleteComplexBank(params);
		
		return result;
	}
	
	/**
	 * @Method Name : deleteComplexPayComp
	 * @Description : 쿠폰제한 결제사 리스트 삭제
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
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
