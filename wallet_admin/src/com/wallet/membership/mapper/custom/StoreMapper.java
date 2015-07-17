package com.wallet.membership.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.Store;

public interface StoreMapper {

	/**
	 * @Method Name : selectStoreList
	 * @Description : 가맹점 목록 조회(복합결제 쪽에서 사용)
	 * @param : HashMap<String ,Object>
	 * @return : List<Settlement>
	 * @author 김태리
	 * @since 2012.09.24
	 */
	List<Store> selectStoreList(HashMap<String ,Object> params);
	
}
