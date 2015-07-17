package com.wallet.membership.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.SalesAgency;

public interface SalesAgencyMapper {
	/**
	 * @Method Name : selectSalesAgencyList
	 * @Description : ��������� ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<SalesAgency>
	 * @author ��ϼ�
	 * @since 2012.09.10
	 */
	List<SalesAgency> selectSalesAgencyList(HashMap<String ,Object> params);
	
	
	/**
	 * @Method Name : selectSalesAgencyListCnt
	 * @Description : ������ ��� �� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.05
	 */
	Integer selectSalesAgencyListCnt(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : insertSalesAgency
	 * @Description : ��������� ���
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ��ϼ�
	 * @since 2012.09.10
	 */
	Integer insertSalesAgency(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : updateSalesAgency
	 * @Description : ��������� ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ��ϼ�
	 * @since 2012.09.10
	 */
	Integer updateSalesAgency(HashMap<String ,Object> params);
}
