package com.wallet.admin.mapper;


import java.util.HashMap;
import java.util.List;

import com.wallet.admin.model.MwAdPush;

public interface MwAdPushMapper {
	
	List<MwAdPush> selectPushList(HashMap<String, Object> params);
	
	int insertPushReg(HashMap<String, Object> params);

	int deletePushDtl(HashMap<String, Object> params);

	int updatePushDtl(HashMap<String, Object> params);

}