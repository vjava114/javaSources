package com.wallet.admin.mapper;


import java.util.HashMap;
import java.util.List;

import com.wallet.admin.model.MwAdGift;

public interface MwAdGiftMapper {

	List<MwAdGift> selectGiftList(HashMap<String, Object> params);

	int insertGiftReg(HashMap<String, Object> params);

	int deleteGiftDtl(HashMap<String, Object> params);

	int updateGiftDtl(HashMap<String, Object> params);

	int updateGiftIdx(HashMap<String, Object> params);



}