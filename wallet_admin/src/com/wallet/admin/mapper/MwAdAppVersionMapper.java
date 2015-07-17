package com.wallet.admin.mapper;


import java.util.HashMap;
import java.util.List;

import com.wallet.admin.model.MwAdAppVersion;


public interface MwAdAppVersionMapper {
	
	List<MwAdAppVersion> selectAppVersionList(HashMap<String, Object> params);
	
	int insertAppVersionReg(HashMap<String, Object> params);

	int deleteAppVersionDtl(HashMap<String, Object> params);

	int updateAppVersionDtl(HashMap<String, Object> params);
	

}