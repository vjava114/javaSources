package com.wallet.membership.service.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.dao.custom.SalesAgencyDao;
import com.wallet.membership.model.custom.SalesAgency;

public class SalesAgencyService {
	private final SalesAgencyDao sDao;
	
	/**
	 * @Method Name : SalesAgencyService
	 * @Description : 생성자
	 * @param : 
	 * @return : 
	 * @author 김완섭
	 * @since 2012.09.10
	 */
	public SalesAgencyService() {
		sDao = new SalesAgencyDao();
	}
	
	/**
	 * @Method Name : commit
	 * @Description : 
	 * @param : 
	 * @return : void
	 * @author 김완섭
	 * @since 2012.09.10
	 */
	public void commit(){
		sDao.commit();
	}
	
	/**
	 * @Method Name : selectSalesAgencyList
	 * @Description : 영업대행사 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<SalesAgency>
	 * @author 김완섭
	 * @since 2012.09.10
	 */
	public List<SalesAgency> selectSalesAgencyList(HashMap<String ,Object> params) {
		List<SalesAgency> result = null;
		
		result = sDao.selectSalesAgencyList(params); //-- 이벤트/공지사항 목록을 조회함.

		return result;
	}
	
	/**
	 * @Method Name : selectSalesAgencyListCnt
	 * @Description : 영업대행사 목록 수 조회
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김완섭
	 * @since 2012.09.10
	 */
	public int selectSalesAgencyListCnt(HashMap<String, Object> params) {
		int result = 0;
		
		result = Integer.parseInt(sDao.selectSalesAgencyListCnt(params).toString()); //-- 이벤트/공지사항 목록을 조회함.

		return result;
	}

	
	/**
	 * @Method Name : selectASalesAgency
	 * @Description : 영업대행사 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<SalesAgency>
	 * @author 김완섭
	 * @since 2012.09.10
	 */
	@SuppressWarnings("unchecked")
	public SalesAgency selectASalesAgency(HashMap<String,Object> params){
		SalesAgency aSalesAgency = sDao.selectASalesAgency(params);
		
		return aSalesAgency;
	}
	
	/**
	 * @Method Name : insertSalesAgency
	 * @Description : 영업대행사 등록
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김완섭
	 * @since 2012.09.10
	 */
	public int insertSalesAgency(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.insertSalesAgency(params);
		
		return result;
	}
	
	/**
	 * @Method Name : updateSalesAgency
	 * @Description : 영업대행사 수정
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김완섭
	 * @since 2012.09.10
	 */
	public int updateSalesAgency(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.updateSalesAgency(params);
		
		return result;
	}
	
	/**
	 * @Method Name : deleteSalesAgency
	 * @Description : 영업대행사 삭제
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김완섭
	 * @since 2012.09.10
	 */
	public int deleteSalesAgency(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.deleteSalesAgency(params);
		
		return result;
	}
}

