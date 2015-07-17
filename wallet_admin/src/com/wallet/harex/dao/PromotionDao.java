package com.wallet.harex.dao;

import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.harex.common.JStringTokenizer;
import com.wallet.harex.mapper.PromotionMapper;
import com.wallet.harex.model.PromotionInfo;
import com.wallet.harex.model.PromotionList;

public class PromotionDao extends MybatisCilent implements PromotionMapper {
	
	public void commit() {
		sqlMapper.commit();
	}


	public void rollback() {
		sqlMapper.rollback();
	}
	
	@SuppressWarnings("unchecked")
	public List<PromotionList> selectPromotionList(HashMap<String, Object> params) {
		
		return sqlMapper.selectList("com.wallet.harex.mapper.PromotionMapper.selectPromotionList", params);
		
	}
	
	@SuppressWarnings("unchecked")
	public Integer selectPromotionListCnt(HashMap<String, Object> params) {
		
		return (Integer) sqlMapper.selectOne("com.wallet.harex.mapper.PromotionMapper.selectPromotionListCnt", params);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<PromotionInfo> selectPromotionInfo(HashMap<String, Object> params) {
		
		return sqlMapper.selectList("com.wallet.harex.mapper.PromotionMapper.selectPromotionInfo", params);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<PromotionInfo> selectPromInfoShopList(HashMap<String, Object> params) {
		
		return sqlMapper.selectList("com.wallet.harex.mapper.PromotionMapper.selectPromInfoShopList", params);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<PromotionInfo> selectModifyShopList(HashMap<String, Object> params) {
		
		return sqlMapper.selectList("com.wallet.harex.mapper.PromotionMapper.selectModifyShopList", params);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<PromotionInfo> selectBankList() {
		
		return sqlMapper.selectList("com.wallet.harex.mapper.PromotionMapper.selectBankList");
		
	}
	
	@SuppressWarnings("unchecked")
	public List<PromotionInfo> selectCardList() {
		
		return sqlMapper.selectList("com.wallet.harex.mapper.PromotionMapper.selectCardList");
		
	}
	
	@SuppressWarnings("unchecked")
	public List<PromotionInfo> selectDcCntBaseList() {
		
		return sqlMapper.selectList("com.wallet.harex.mapper.PromotionMapper.selectDcCntBaseList");
		
	}
	
	@SuppressWarnings("unchecked")
	public List<PromotionInfo> selectPayCompList() {
		
		return sqlMapper.selectList("com.wallet.harex.mapper.PromotionMapper.selectPayCompList");
		
	}
	
	@SuppressWarnings("unchecked")
	public List<PromotionInfo> selectDcUnitList() {
		
		return sqlMapper.selectList("com.wallet.harex.mapper.PromotionMapper.selectDcUnitList");
		
	}
	
	@SuppressWarnings("unchecked")
	public List<PromotionInfo> selectRoundTypeList() {
		
		return sqlMapper.selectList("com.wallet.harex.mapper.PromotionMapper.selectRoundTypeList");
		
	}
	
	@SuppressWarnings("unchecked")
	public List<PromotionInfo> selectShopList(HashMap<String, Object> params) {
		
		return sqlMapper.selectList("com.wallet.harex.mapper.PromotionMapper.selectShopList", params);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<PromotionInfo> selectPromotionUpdate(HashMap<String, Object> params) {
		
		return sqlMapper.selectList("com.wallet.harex.mapper.PromotionMapper.selectPromotionUpdate", params);
		
	}
	
	public int insertPromotionInfo(HashMap<String, Object> params) {
		
		return new Integer(sqlMapper.insert("com.wallet.harex.mapper.PromotionMapper.insertPromotionInfo", params));
		
	}
	
	public int updatePromotionPromo(HashMap<String, Object> params) {
		
		return new Integer(sqlMapper.update("com.wallet.harex.mapper.PromotionMapper.updatePromotionPromo", params));
		
	}
	
	public int updatePromotionPromoDtl(HashMap<String, Object> params) {
		
		return new Integer(sqlMapper.update("com.wallet.harex.mapper.PromotionMapper.updatePromotionPromoDtl", params));
		
	}
	
	public int deletePromotion(HashMap<String, Object> params) {
		
		return new Integer(sqlMapper.update("com.wallet.harex.mapper.PromotionMapper.deletePromotion", params));
		
	}
	
	public int insertPromotion(HashMap<String, Object> params) {
		
		return new Integer(sqlMapper.update("com.wallet.harex.mapper.PromotionMapper.insertPromotion", params));
	}
	
	public int insertPromotionDtl(HashMap<String, Object> params) {
		
		return new Integer(sqlMapper.update("com.wallet.harex.mapper.PromotionMapper.insertPromotionDtl", params));
	}
	
	public int insertPromotionShop(HashMap<String, Object> params) {

		return new Integer(sqlMapper.update("com.wallet.harex.mapper.PromotionMapper.insertPromotionShop", params));
	}
}
