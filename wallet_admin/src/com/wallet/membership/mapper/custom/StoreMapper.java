package com.wallet.membership.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.Store;

public interface StoreMapper {

	/**
	 * @Method Name : selectStoreList
	 * @Description : ������ ��� ��ȸ(���հ��� �ʿ��� ���)
	 * @param : HashMap<String ,Object>
	 * @return : List<Settlement>
	 * @author ���¸�
	 * @since 2012.09.24
	 */
	List<Store> selectStoreList(HashMap<String ,Object> params);
	
}
