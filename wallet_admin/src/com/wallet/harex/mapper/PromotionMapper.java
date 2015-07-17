package com.wallet.harex.mapper;

import java.util.HashMap;
import java.util.List;

import com.wallet.harex.model.PromotionInfo;
import com.wallet.harex.model.PromotionList;

public interface PromotionMapper {

	List<PromotionList> selectPromotionList(HashMap<String, Object> params);
	
	Integer selectPromotionListCnt(HashMap<String, Object> params);
	
	List<PromotionInfo> selectPromotionInfo(HashMap<String, Object> params);
	
	List<PromotionInfo> selectDcUnitList();
	
	List<PromotionInfo> selectRoundTypeList();
	
	int insertPromotionInfo(HashMap<String, Object> params);
	
	List<PromotionInfo> selectPromotionUpdate(HashMap<String, Object> params);
	
	int insertPromotion(HashMap<String, Object> params);

	int insertPromotionDtl(HashMap<String, Object> params);
	
	int insertPromotionShop(HashMap<String, Object> params);
	
	int deletePromotion(HashMap<String, Object> params);

	int updatePromotionPromo(HashMap<String, Object> params);
	
	int updatePromotionPromoDtl(HashMap<String, Object> params);
}
