package com.wallet.admin.mapper;


import java.util.HashMap;
import java.util.List;


import com.wallet.admin.model.MwAdCard;


public interface MwAdCardMapper {

	List<MwAdCard> selectCardList(HashMap<String, Object> params);
	
	int updateCardIdx(HashMap<String, Object> params);

	int insertCardReg(HashMap<String, Object> params);

	int updateCardDtl(HashMap<String, Object> params);

	int deleteCardDtl(HashMap<String, Object> params);

}