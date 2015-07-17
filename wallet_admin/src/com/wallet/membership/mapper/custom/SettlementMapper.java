package com.wallet.membership.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.Settlement;

public interface SettlementMapper {
	/**
	 * @Method Name : selectSettlementList
	 * @Description : ������ ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<Settlement>
	 * @author ���¸�
	 * @since 2012.09.05
	 */
	List<Settlement> selectSettlementList(HashMap<String ,Object> params);
	
	
	/**
	 * @Method Name : selectSettlementListCnt
	 * @Description : ������ ��� �� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.05
	 */
	Integer selectSettlementListCnt(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : insertSettlement
	 * @Description : ������ ���
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.06
	 */
	Integer insertSettlement(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : updateSettlement
	 * @Description : ������ ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.06
	 */
	Integer updateSettlement(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : deleteSettlement
	 * @Description : ������ ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.06
	 */
	Integer deleteSettlement(HashMap<String ,Object> params);
}
