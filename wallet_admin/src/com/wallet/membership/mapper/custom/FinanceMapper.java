package com.wallet.membership.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.Finance;

public interface FinanceMapper {
	/**
	 * @Method Name : selectFinanceList
	 * @Description : ������ ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<Finance>
	 * @author ���¸�
	 * @since 2012.09.07
	 */
	List<Finance> selectFinanceList(HashMap<String ,Object> params);
	
	
	/**
	 * @Method Name : selectFinanceListCnt
	 * @Description : ������ ��� �� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.07
	 */
	Integer selectFinanceListCnt(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : insertFinance
	 * @Description : ������ ���
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.07
	 */
	Integer insertFinance(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : updateFinance
	 * @Description : ������ ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.07
	 */
	Integer updateFinance(HashMap<String ,Object> params);
}
