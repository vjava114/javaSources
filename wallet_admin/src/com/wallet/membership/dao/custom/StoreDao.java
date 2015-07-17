package com.wallet.membership.dao.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.membership.mapper.custom.StoreMapper;
import com.wallet.membership.model.custom.Store;
import com.wallet.membership.model.custom.Store;

public class StoreDao extends MybatisCilent implements StoreMapper {
	private String preMapperName = "com.wallet.membership.mapper.custom.StoreMapper.";

	/**
	 * @Method Name : commit
	 * @Description :
	 * @param : 
	 * @return : 
	 * @author 김태리
	 * @since 2012.09.24
	 */
	public void commit() {
		sqlMapper.commit();
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
		sqlMapper.rollback();
	}
	
	/**
	 * @Method Name : StoreDao
	 * @Description : 생성자
	 * @param : 
	 * @return : 
	 * @author 김태리
	 * @since 2012.09.24
	 */
	public StoreDao() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @Method Name : selectStoreList
	 * @Description : 결제사 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<Store>
	 * @author 김태리
	 * @since 2012.09.24
	 */
	@SuppressWarnings("unchecked")
	public List<Store> selectStoreList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectStoreList", params);
	}

}
