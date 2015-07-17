package com.wallet.admin.mapper;


import java.util.HashMap;
import java.util.List;

import com.wallet.admin.model.MwAdEvent;

public interface MwAdEventMapper {
	
	List<MwAdEvent> selectEventList(HashMap<String, Object> params);
	
	int selectEventListTotalCnt(HashMap<String, Object> params);
	
	int updateEventDtl(HashMap<String, Object> params);

	int deleteEventDtl(HashMap<String, Object> params);

	int insertEventReg(HashMap<String, Object> params);



}