package com.wallet.membership.service.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.dao.custom.FinanceDao;
import com.wallet.membership.model.custom.Finance;

public class FinanceService {
	private final FinanceDao sDao;

	/**
	 * @Method Name : FinanceService
	 * @Description : ������
	 * @param : 
	 * @return : 
	 * @author ���¸�
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
	 * @author ���¸�
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
	 * @author ���¸�
	 * @since 2012.09.14
	 */
	public void rollback(){
		sDao.rollback();
	}
	
	/**
	 * @Method Name : selectFinanceList
	 * @Description : ������ ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<Finance>
	 * @author ���¸�
	 * @since 2012.09.07
	 */
	public List<Finance> selectFinanceList(HashMap<String ,Object> params) {
		List<Finance> result = null;
		
		result = sDao.selectFinanceList(params); //-- ������ ����� ��ȸ��.

		return result;
	}
	
	/**
	 * @Method Name : selectFinanceListCnt
	 * @Description : ������ ��� �� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ���¸�
	 * @since 2012.09.07
	 */
	public int selectFinanceListCnt(HashMap<String, Object> params) {
		int result = 0;
		
		result = Integer.parseInt(sDao.selectFinanceListCnt(params).toString()); //-- ������ ����� ��ȸ��.

		return result;
	}

	
	/**
	 * @Method Name : selectAFinance
	 * @Description : ������ ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<Finance>
	 * @author ���¸�
	 * @since 2012.09.07
	 */
	@SuppressWarnings("unchecked")
	public Finance selectAFinance(HashMap<String,Object> params){
		Finance aFinance = sDao.selectAFinance(params);
		
		return aFinance;
	}
	
	/**
	 * @Method Name : insertFinance
	 * @Description : ������ ���
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ���¸�
	 * @since 2012.09.07
	 */
	public int insertFinance(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.insertFinance(params);
		
		return result;
	}
	
	/**
	 * @Method Name : updateFinance
	 * @Description : ������ ����
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ���¸�
	 * @since 2012.09.07
	 */
	public int updateFinance(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.updateFinance(params);
		
		return result;
	}
	
	/**
	 * @Method Name : deleteFinance
	 * @Description : ������ ����
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ���¸�
	 * @since 2012.09.07
	 */
	public int deleteFinance(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.deleteFinance(params);
		
		return result;
	}
}
