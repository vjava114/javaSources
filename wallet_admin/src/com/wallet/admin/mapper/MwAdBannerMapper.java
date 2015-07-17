package com.wallet.admin.mapper;


import java.util.HashMap;
import java.util.List;

import com.wallet.admin.model.MwAdBanner;


public interface MwAdBannerMapper {
	
	List<MwAdBanner> selectBannerList(HashMap<String, Object> params);
	
	int selectBannerListTotalCnt(HashMap<String, Object> params);
	
	int insertBannerReg(HashMap<String, Object> params);

	int deleteBannerDtl(HashMap<String, Object> params);

	int updateBannerDtl(HashMap<String, Object> params);


}