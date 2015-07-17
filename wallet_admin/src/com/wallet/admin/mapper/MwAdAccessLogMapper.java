package com.wallet.admin.mapper;

import java.util.HashMap;
import java.util.List;

import com.wallet.admin.model.MwAdAccessLog;

public interface MwAdAccessLogMapper {
	
	List<MwAdAccessLog> selectAccessLogList(HashMap<String, Object> params);
	
	int insertAccessLogReg(HashMap<String, Object> params);
	
}