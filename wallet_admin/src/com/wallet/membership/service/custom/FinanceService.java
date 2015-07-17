package com.wallet.membership.service.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.dao.custom.FinanceDao;
import com.wallet.membership.model.custom.Finance;

public class FinanceService {
	private final FinanceDao sDao;

	/**
	 * @Method Name : FinanceService
	 * @Description : 생성자
	 * @param : 
	 * @return : 
	 * @author 김태리
	 * @since 2012.09.07
	 */
	public FinanceService() {
		sDao = new FinanceDao();
	}
	
	/**
	 * @Method Name : commit
	 * @Description : 
	 * @param : 
	 * @return : void
	 * @author 김태리
	 * @since 2012.09.07
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
	 * @Method Name : selectFinanceList
	 * @Description : 금융사 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<Finance>
	 * @author 김태리
	 * @since 2012.09.07
	 */
	public List<Finance> selectFinanceList(HashMap<String ,Object> params) {
		List<Finance> result = null;
		
		result = sDao.selectFinanceList(params); //-- 금융사 목록을 조회함.

		return result;
	}
	
	/**
	 * @Method Name : selectFinanceListCnt
	 * @Description : 금융사 목록 수 조회
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2012.09.07
	 */
	public int selectFinanceListCnt(HashMap<String, Object> params) {
		int result = 0;
		
		result = Integer.parseInt(sDao.selectFinanceListCnt(params).toString()); //-- 금융사 목록을 조회함.

		return result;
	}

	
	/**
	 * @Method Name : selectAFinance
	 * @Description : 금융사 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<Finance>
	 * @author 김태리
	 * @since 2012.09.07
	 */
	@SuppressWarnings("unchecked")
	public Finance selectAFinance(HashMap<String,Object> params){
		Finance aFinance = sDao.selectAFinance(params);
		
		return aFinance;
	}
	
	/**
	 * @Method Name : insertFinance
	 * @Description : 금융사 등록
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2012.09.07
	 */
	public int insertFinance(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.insertFinance(params);
		
		return result;
	}
	
	/**
	 * @Method Name : updateFinance
	 * @Description : 금융사 수정
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2012.09.07
	 */
	public int updateFinance(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.updateFinance(params);
		
		return result;
	}
	
	/**
	 * @Method Name : deleteFinance
	 * @Description : 금융사 삭제
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2012.09.07
	 */
	public int deleteFinance(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.deleteFinance(params);
		
		return result;
	}
}
