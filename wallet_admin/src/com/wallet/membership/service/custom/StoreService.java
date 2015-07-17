package com.wallet.membership.service.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.dao.custom.StoreDao;
import com.wallet.membership.model.custom.Store;

public class StoreService {
	private final StoreDao sDao;

	/**
	 * @Method Name : StoreService
	 * @Description : 생성자
	 * @param : 
	 * @return : 
	 * @author 김태리
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
	 * @author 김태리
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
	 * @author 김태리
	 * @since 2012.09.24
	 */
	public void rollback(){
		sDao.rollback();
	}
	
	/**
	 * @Method Name : selectStoreList
	 * @Description : 결제사 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<Store>
	 * @author 김태리
	 * @since 2012.09.24
	 */
	public List<Store> selectStoreList(HashMap<String ,Object> params) {
		List<Store> result = null;
		
		result = sDao.selectStoreList(params); //-- 이벤트/공지사항 목록을 조회함.

		return result;
	}

}
