package com.wallet.membership.service.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.dao.custom.StoreDao;
import com.wallet.membership.model.custom.Store;

public class StoreService {
	private final StoreDao sDao;

	/**
	 * @Method Name : StoreService
	 * @Description : ������
	 * @param : 
	 * @return : 
	 * @author ���¸�
	 * @since 2012.09.24
	 */
	public StoreService() {
		sDao = new StoreDao();
	}
	
	/**
	 * @Method Name : commit
	 * @Description : 
	 * @param : 
	 * @return : void
	 * @author ���¸�
	 * @since 2012.09.24
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
	 * @since 2012.09.24
	 */
	public void rollback(){
		sDao.rollback();
	}
	
	/**
	 * @Method Name : selectStoreList
	 * @Description : ������ ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<Store>
	 * @author ���¸�
	 * @since 2012.09.24
	 */
	public List<Store> selectStoreList(HashMap<String ,Object> params) {
		List<Store> result = null;
		
		result = sDao.selectStoreList(params); //-- �̺�Ʈ/�������� ����� ��ȸ��.

		return result;
	}

}
