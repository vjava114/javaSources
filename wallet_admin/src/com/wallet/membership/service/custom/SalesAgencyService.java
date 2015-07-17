package com.wallet.membership.service.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.dao.custom.SalesAgencyDao;
import com.wallet.membership.model.custom.SalesAgency;

public class SalesAgencyService {
	private final SalesAgencyDao sDao;
	
	/**
	 * @Method Name : SalesAgencyService
	 * @Description : ������
	 * @param : 
	 * @return : 
	 * @author ��ϼ�
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
	 * @author ��ϼ�
	 * @since 2012.09.10
	 */
	public void commit(){
		sDao.commit();
	}
	
	/**
	 * @Method Name : selectSalesAgencyList
	 * @Description : ��������� ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<SalesAgency>
	 * @author ��ϼ�
	 * @since 2012.09.10
	 */
	public List<SalesAgency> selectSalesAgencyList(HashMap<String ,Object> params) {
		List<SalesAgency> result = null;
		
		result = sDao.selectSalesAgencyList(params); //-- �̺�Ʈ/�������� ����� ��ȸ��.

		return result;
	}
	
	/**
	 * @Method Name : selectSalesAgencyListCnt
	 * @Description : ��������� ��� �� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ��ϼ�
	 * @since 2012.09.10
	 */
	public int selectSalesAgencyListCnt(HashMap<String, Object> params) {
		int result = 0;
		
		result = Integer.parseInt(sDao.selectSalesAgencyListCnt(params).toString()); //-- �̺�Ʈ/�������� ����� ��ȸ��.

		return result;
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
		SalesAgency aSalesAgency = sDao.selectASalesAgency(params);
		
		return aSalesAgency;
	}
	
	/**
	 * @Method Name : insertSalesAgency
	 * @Description : ��������� ���
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ��ϼ�
	 * @since 2012.09.10
	 */
	public int insertSalesAgency(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.insertSalesAgency(params);
		
		return result;
	}
	
	/**
	 * @Method Name : updateSalesAgency
	 * @Description : ��������� ����
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ��ϼ�
	 * @since 2012.09.10
	 */
	public int updateSalesAgency(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.updateSalesAgency(params);
		
		return result;
	}
	
	/**
	 * @Method Name : deleteSalesAgency
	 * @Description : ��������� ����
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ��ϼ�
	 * @since 2012.09.10
	 */
	public int deleteSalesAgency(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.deleteSalesAgency(params);
		
		return result;
	}
}

