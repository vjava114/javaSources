package com.wallet.membership.service.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.dao.custom.SettlementDao;
import com.wallet.membership.model.custom.Settlement;

public class SettlementService {
	private final SettlementDao sDao;

	/**
	 * @Method Name : SettlementService
	 * @Description : 생성자
	 * @param : 
	 * @return : 
	 * @author 김태리
	 * @since 2012.09.05
	 */
	public SettlementService() {
		sDao = new SettlementDao();
	}
	
	/**
	 * @Method Name : commit
	 * @Description : 
	 * @param : 
	 * @return : void
	 * @author 김태리
	 * @since 2012.09.05
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
	 * @Method Name : selectSettlementList
	 * @Description : 결제사 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<Settlement>
	 * @author 김태리
	 * @since 2012.09.05
	 */
	public List<Settlement> selectSettlementList(HashMap<String ,Object> params) {
		List<Settlement> result = null;
		
		result = sDao.selectSettlementList(params); //-- 이벤트/공지사항 목록을 조회함.

		return result;
	}
	
	/**
	 * @Method Name : selectSettlementListCnt
	 * @Description : 결제사 목록 수 조회
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2012.09.05
	 */
	public int selectSettlementListCnt(HashMap<String, Object> params) {
		int result = 0;
		
		result = Integer.parseInt(sDao.selectSettlementListCnt(params).toString()); //-- 이벤트/공지사항 목록을 조회함.

		return result;
	}

	
	/**
	 * @Method Name : selectASettlement
	 * @Description : 결제사 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<Settlement>
	 * @author 김태리
	 * @since 2012.09.06
	 */
	@SuppressWarnings("unchecked")
	public Settlement selectASettlement(HashMap<String,Object> params){
		Settlement aSettlement = sDao.selectASettlement(params);
		
		return aSettlement;
	}
	
	/**
	 * @Method Name : insertSettlement
	 * @Description : 결제사 등록
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2012.09.06
	 */
	public int insertSettlement(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.insertSettlement(params);
		
		return result;
	}
	
	/**
	 * @Method Name : updateSettlement
	 * @Description : 결제사 수정
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2012.09.06
	 */
	public int updateSettlement(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.updateSettlement(params);
		
		return result;
	}
	
	/**
	 * @Method Name : deleteSettlement
	 * @Description : 결제사 삭제
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2012.09.07
	 */
	public int deleteSettlement(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.deleteSettlement(params);
		
		return result;
	}
}
