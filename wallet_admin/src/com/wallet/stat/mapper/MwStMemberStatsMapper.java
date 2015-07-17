package com.wallet.stat.mapper;


import java.util.HashMap;
import java.util.List;

import com.wallet.stat.model.MwStMemberStatsMocaPay;
import com.wallet.stat.model.MwStMemberStatsOs;
import com.wallet.stat.model.MwStMemberStatsAge;


public interface MwStMemberStatsMapper {

	MwStMemberStatsOs selectMemberStatsAcmOs(HashMap<String, Object> params);
	
	MwStMemberStatsAge selectMemberStatsAcmAge(HashMap<String, Object> params);
	
	MwStMemberStatsMocaPay selectMemberStatsAcmMocaPay(HashMap<String, Object> params);
	
	List<MwStMemberStatsOs> selectMemberStatsNewOsDayList(HashMap<String, Object> params);
	
	List<MwStMemberStatsOs> selectMemberStatsNewOsMonthList(HashMap<String, Object> params);
	
	List<MwStMemberStatsOs> selectMemberStatsNewOsYearList(HashMap<String, Object> params);
	
	List<MwStMemberStatsAge> selectMemberStatsNewAgeList(HashMap<String, Object> params);
	
	List<MwStMemberStatsMocaPay> selectMemberStatsNewMocaPayList(HashMap<String, Object> params);
	
	
	
}