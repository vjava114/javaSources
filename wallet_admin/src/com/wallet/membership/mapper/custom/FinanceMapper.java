package com.wallet.membership.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.Finance;

public interface FinanceMapper {
	/**
	 * @Method Name : selectFinanceList
	 * @Description : 금융사 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<Finance>
	 * @author 김태리
	 * @since 2012.09.07
	 */
	List<Finance> selectFinanceList(HashMap<String ,Object> params);
	
	
	/**
	 * @Method Name : selectFinanceListCnt
	 * @Description : 금융사 목록 수 조회
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2012.09.07
	 */
	Integer selectFinanceListCnt(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : insertFinance
	 * @Description : 금융사 등록
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2012.09.07
	 */
	Integer insertFinance(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : updateFinance
	 * @Description : 금융사 수정
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2012.09.07
	 */
	Integer updateFinance(HashMap<String ,Object> params);
}
