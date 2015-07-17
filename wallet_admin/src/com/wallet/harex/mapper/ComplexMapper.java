package com.wallet.harex.mapper;

import java.util.HashMap;
import java.util.List;

import com.wallet.harex.model.ComplexInfo;
import com.wallet.harex.model.ComplexList;
import com.wallet.harex.model.ComplexShopHid;
import com.wallet.harex.model.ComplexUserList;

public interface ComplexMapper {
	
	List<ComplexList> selectComplexList(HashMap<String, Object> params);
	
	ComplexInfo selectComplexInfo(HashMap<String, Object> params);
	
	List<ComplexShopHid> selectComplexShopHidList(HashMap<String, Object> params);
	
	Integer selectComplexShopHidListCnt(HashMap<String, Object> params);
	
	List<ComplexUserList> selectComplexUserList(HashMap<String, Object> params);
	
	Integer updateComplexShopHid(HashMap<String ,Object> params);
	
	String selectComplexShopHidRegisterCheck(String h_shop_id);

}
