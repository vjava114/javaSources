package com.wallet.membership.dao.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.membership.mapper.custom.SalesAgencyMapper;
import com.wallet.membership.model.custom.SalesAgency;

public class SalesAgencyDao extends MybatisCilent implements SalesAgencyMapper {

	private String preMapperName = "com.wallet.membership.mapper.custom.SalesAgencyMapper.";

	
	/**
	 * @Method Name : commit
	 * @Description :
	 * @param : 
	 * @return : 
	 * @author ��ϼ�
	 * @since 2012.09.10
	 */
	public void commit() {
		sqlMapper.commit();
	}
	
	
	/**
	 * @Method Name : SalesAgencyDao
	 * @Description : ������
	 * @param : 
	 * @return : 
	 * @author ��ϼ�
	 * @since 2012.09.10
	 */
	public SalesAgencyDao() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @Method Name : selectSalesAgencyList
	 * @Description : ��������� ��� ��ȸ
	 * @param : HashMap<String, Object>
	 * @return : 
	 * @author ��ϼ�
	 * @since 2012.09.10
	 */
	@SuppressWarnings("unchecked")
	public List<SalesAgency> selectSalesAgencyList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectSalesAgencyList", params);
	}
	
	
	/**
	 * @Method Name : selectSalesAgencyListCnt
	 * @Description : ��������� ��� �� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ��ϼ�
	 * @since 2012.09.10
	 */
	@SuppressWarnings("unchecked")
	public Integer selectSalesAgencyListCnt(HashMap<String,Object> params){
		Integer cnt = 0;
		
		cnt = (Integer) sqlMapper.selectOne(preMapperName + "selectSalesAgencyListCnt", params);
		return cnt;
	}

	
	/**
	 * @Method Name : selectASalesAgency
	 * @Description : ��������� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<SalesAgency>
	 * @author ��ϼ�
	 * @since 2012.09.10
	 */
	@SuppressWarnings("unchecked")
	public SalesAgency selectASalesAgency(HashMap<String,Object> params){
		return (SalesAgency) sqlMapper.selectOne(preMapperName + "selectSalesAgencyInfo", params);
	}
	
	
	/**
	 * @Method Name : insertSalesAgency
	 * @Description : ��������� ���
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ��ϼ�
	 * @since 2012.09.10
	 */
	@SuppressWarnings("unchecked")
	public Integer insertSalesAgency(HashMap<String,Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.insert(preMapperName + "insertSalesAgency", params));
		return result;
	}
	
	
	/**
	 * @Method Name : updateSalesAgency
	 * @Description : ��������� ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ��ϼ�
	 * @since 2012.09.10
	 */
	@SuppressWarnings("unchecked")
	public Integer updateSalesAgency(HashMap<String,Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.update(preMapperName + "updateSalesAgency", params));
		return result;
	}
	
	/**
	 * @Method Name : deleteSalesAgency
	 * @Description : ��������� ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ��ϼ�
	 * @since 2012.09.12
	 */
	@SuppressWarnings("unchecked")
	public Integer deleteSalesAgency(HashMap<String, Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.delete(preMapperName + "deleteSalesAgency", params));
		return result;
	}

}
