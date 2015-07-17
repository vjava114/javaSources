package com.wallet.membership.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.Settlement;

public interface SettlementMapper {
	/**
	 * @Method Name : selectSettlementList
	 * @Description : 결제사 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<Settlement>
	 * @author 김태리
	 * @since 2012.09.05
	 */
	List<Settlement> selectSettlementList(HashMap<String ,Object> params);
	
	
	/**
	 * @Method Name : selectSettlementListCnt
	 * @Description : 결제사 목록 수 조회
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2012.09.05
	 */
	Integer selectSettlementListCnt(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : insertSettlement
	 * @Description : 결제사 등록
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2012.09.06
	 */
	Integer insertSettlement(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : updateSettlement
	 * @Description : 결제사 수정
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2012.09.06
	 */
	Integer updateSettlement(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : deleteSettlement
	 * @Description : 결제사 삭제
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2012.09.06
	 */
	Integer deleteSettlement(HashMap<String ,Object> params);
}
