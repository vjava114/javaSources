package com.wallet.membership.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.SalesAgency;

public interface SalesAgencyMapper {
	/**
	 * @Method Name : selectSalesAgencyList
	 * @Description : 영업대행사 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<SalesAgency>
	 * @author 김완섭
	 * @since 2012.09.10
	 */
	List<SalesAgency> selectSalesAgencyList(HashMap<String ,Object> params);
	
	
	/**
	 * @Method Name : selectSalesAgencyListCnt
	 * @Description : 결제사 목록 수 조회
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2012.09.05
	 */
	Integer selectSalesAgencyListCnt(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : insertSalesAgency
	 * @Description : 영업대행사 등록
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김완섭
	 * @since 2012.09.10
	 */
	Integer insertSalesAgency(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : updateSalesAgency
	 * @Description : 영업대행사 수정
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김완섭
	 * @since 2012.09.10
	 */
	Integer updateSalesAgency(HashMap<String ,Object> params);
}
