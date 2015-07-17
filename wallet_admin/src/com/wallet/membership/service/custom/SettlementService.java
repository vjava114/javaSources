package com.wallet.membership.service.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.dao.custom.SettlementDao;
import com.wallet.membership.model.custom.Settlement;

public class SettlementService {
	private final SettlementDao sDao;

	/**
	 * @Method Name : SettlementService
	 * @Description : ������
	 * @param : 
	 * @return : 
	 * @author ���¸�
	 * @since 2012.09.05
	 */
	public SettlementService() {
		sDao = new SettlementDao();
	}
	
	/**
	 * @Method Name : commit
	 * @Description : 
	 * @param : 
	 * @return : void
	 * @author ���¸�
	 * @since 2012.09.05
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
	 * @Method Name : selectSettlementList
	 * @Description : ������ ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<Settlement>
	 * @author ���¸�
	 * @since 2012.09.05
	 */
	public List<Settlement> selectSettlementList(HashMap<String ,Object> params) {
		List<Settlement> result = null;
		
		result = sDao.selectSettlementList(params); //-- �̺�Ʈ/�������� ����� ��ȸ��.

		return result;
	}
	
	/**
	 * @Method Name : selectSettlementListCnt
	 * @Description : ������ ��� �� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ���¸�
	 * @since 2012.09.05
	 */
	public int selectSettlementListCnt(HashMap<String, Object> params) {
		int result = 0;
		
		result = Integer.parseInt(sDao.selectSettlementListCnt(params).toString()); //-- �̺�Ʈ/�������� ����� ��ȸ��.

		return result;
	}

	
	/**
	 * @Method Name : selectASettlement
	 * @Description : ������ ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<Settlement>
	 * @author ���¸�
	 * @since 2012.09.06
	 */
	@SuppressWarnings("unchecked")
	public Settlement selectASettlement(HashMap<String,Object> params){
		Settlement aSettlement = sDao.selectASettlement(params);
		
		return aSettlement;
	}
	
	/**
	 * @Method Name : insertSettlement
	 * @Description : ������ ���
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ���¸�
	 * @since 2012.09.06
	 */
	public int insertSettlement(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.insertSettlement(params);
		
		return result;
	}
	
	/**
	 * @Method Name : updateSettlement
	 * @Description : ������ ����
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ���¸�
	 * @since 2012.09.06
	 */
	public int updateSettlement(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.updateSettlement(params);
		
		return result;
	}
	
	/**
	 * @Method Name : deleteSettlement
	 * @Description : ������ ����
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ���¸�
	 * @since 2012.09.07
	 */
	public int deleteSettlement(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.deleteSettlement(params);
		
		return result;
	}
}
