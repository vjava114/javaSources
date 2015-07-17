package com.wallet.harex.mapper;

import java.util.HashMap;
import java.util.List;

import com.wallet.harex.model.SystemM;
import com.wallet.harex.model.SystemStatusList;

public interface SystemMapper {

	List<SystemStatusList> selectSystemStatusList(HashMap<String, Object> params);
	List<SystemM> selectSystemSettingList(HashMap<String, Object> params);
	SystemM selectSystemSettingView(HashMap<String, Object> params);
	Integer selectSystemSettingListCnt();
	int updateSystemSettingSvr(HashMap<String, Object> params);
	int updateSystemSettingOper(HashMap<String, Object> params);
	int updateSystemSettingIf(HashMap<String, Object> params);
	int insertSystemSettingSvr(HashMap<String, Object> params);
	int insertSystemSettingOper(HashMap<String, Object> params);
	int insertSystemSettingIf(HashMap<String, Object> params);
	String selectCheckProcessIdYn(String id);
}