package com.wallet.admin.mapper;


import java.util.HashMap;
import java.util.List;

import com.wallet.admin.model.MwAdIntroPopup;



public interface MwAdIntroPopupMapper {
	
	List<MwAdIntroPopup> selectIntroPopupList(HashMap<String, Object> params);
	
	int selectIntroPopupListTotalCnt(HashMap<String, Object> params);

	int insertIntroPopupReg(HashMap<String, Object> params);

	int updateIntroPopupStat(HashMap<String, Object> params);

	int deleteIntroPopupDtl(HashMap<String, Object> params);

	int updateIntroPopupDtl(HashMap<String, Object> params);
	

}