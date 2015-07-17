package com.wallet.harex.service;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.wallet.common.util.Log;
import com.wallet.harex.dao.PromotionDao;
import com.wallet.harex.model.PromotionInfo;
import com.wallet.harex.model.PromotionList;

public class PromotionService {
	
	private Logger log = Log.getLogger("logs");
	private final PromotionDao dao;
	
	public PromotionService() {
		dao = new PromotionDao();
	}
	
	public void commit(){
		dao.commit();
	}


	public void rollback() {
		dao.rollback();
	}
	
	/**
	 * 결제기관별 (가맹점별) 프로모션 목록
	 */
	public List<PromotionList> selectPromotionList(HashMap<String,Object> params) {
		
		List<PromotionList> result = null;
		result = dao.selectPromotionList(params);
		return result;
	}
	
	/**
	 * 결제기관별 (가맹점별) 프로모션 목록 카운트
	 */
	public int selectPromotionListCnt(HashMap<String,Object> params) {
		return Integer.parseInt(dao.selectPromotionListCnt(params).toString());
	}
	
	/**
	 * 결제기관별 (가맹점별) 프로모션 상세화면
	 */
	public List<PromotionInfo> selectPromotionInfo(HashMap<String,Object> params) {
		
		List<PromotionInfo> result = null;
		result = dao.selectPromotionInfo(params);
		return result;
	}
	
	/**
	 *  combo 결제서비스사 목록 조회
	 */
	public List<PromotionInfo> selectPayCompList() {
		
		List<PromotionInfo> result = null;
		result = dao.selectPayCompList();
		return result;
	}
	
	/**
	 *  combo 가맹점 목록 조회
	 */
	public List<PromotionInfo> selectShopList(HashMap<String, Object>params) {
		
		List<PromotionInfo> result = null;
		result = dao.selectShopList(params);
		return result;
	}
	
	/**
	 * combo 은행 목록 조회
	 */
	public List<PromotionInfo> selectBankList() {
		
		List<PromotionInfo> result = null;
		result = dao.selectBankList();
		return result;
	}
	
	/**
	 * combo 정율할인 근사계산단위 목록 조회
	 */
	public List<PromotionInfo> selectDcUnitList() {
		
		List<PromotionInfo> result = null;
		result = dao.selectDcUnitList();
		return result;
	}
	
	/**
	 * combo 정율할인 근사계산 목록 조회
	 */
	public List<PromotionInfo> selectRoundTypeList() {
		
		List<PromotionInfo> result = null;
		result = dao.selectRoundTypeList();
		return result;
	}
	
	/**
	 * combo 카드 목록 조회
	 */
	public List<PromotionInfo> selectCardList() {
		
		List<PromotionInfo> result = null;
		result = dao.selectCardList();
		return result;
	}
	
	/**
	 * combo 할인횟수기준 목록 조회
	 */
	public List<PromotionInfo> selectDcCntBaseList() {
		
		List<PromotionInfo> result = null;
		result = dao.selectDcCntBaseList();
		return result;
	}

	/**
	 * combo 상세보기 - 가맹점목록
	 */
	public List<PromotionInfo> selectPromInfoShopList(HashMap<String, Object> params) {
		
		List<PromotionInfo> result = null;
		result = dao.selectPromInfoShopList(params);
		return result;
	}
	
	/**
	 * combo 수정화면 - 가맹점목록 조회
	 */
	public List<PromotionInfo> selectModifyShopList(HashMap<String, Object> params) {
		
		List<PromotionInfo> result = null;
		result = dao.selectModifyShopList(params);
		return result;
	}
	
	/**
	 * 결제기관별 (가맹점별) 프로모션 정보등록
	 */
	public int insertPromotionInfo(HashMap<String, Object> params){
		
		return dao.insertPromotionInfo(params);
		
	}
	
	/**
	 * 결제기관별 (가맹점별) 프로모션 정보등록 - MW_PS_PROMO
	 */
	public int insertPromotion(HashMap<String, Object> params){
		
		return dao.insertPromotion(params);
		
	}
	
	/**
	 * 결제기관별 (가맹점별) 프로모션 정보등록 - MW_PS_PROMO_DTL
	 */
	public int insertPromotionDtl(HashMap<String, Object> params){
		
		return dao.insertPromotionDtl(params);
		
	}
	
	/**
	 * 결제기관별 (가맹점별) 프로모션 정보등록 - MW_PS_PROMO_SHOP
	 */
	public int insertPromotionShop(HashMap<String, Object> params){
		
		return dao.insertPromotionShop(params);
		
	}
	
	/**
	 * 결제기관별 (가맹점별) 프로모션 수정정보 조회
	 */
	public List<PromotionInfo> selectPromotionUpdate(HashMap<String, Object> params) {
		
		List<PromotionInfo> result = null;
		result = dao.selectPromotionUpdate(params);
		return result;
	}
	
	/**
	 * 결제기관별 (가맹점별) 프로모션 정보수정 가맹점 삭제 처리  MW_PS_PROMO_SHOP
	 */
	public int deletePromotion(HashMap<String, Object> params){
		
		return dao.deletePromotion(params);
		
	}
	
	/**
	 * 결제기관별 (가맹점별) 프로모션 정보수정 처리 MW_PS_PROMO
	 */
	public int updatePromotionPromo(HashMap<String, Object> params){
		
		return dao.updatePromotionPromo(params);
		
	}
	
	/**
	 * 결제기관별 (가맹점별) 프로모션 정보수정 처리 MW_PS_PROMO_DTL
	 */
	public int updatePromotionPromoDtl(HashMap<String, Object> params){
		
		return dao.updatePromotionPromoDtl(params);
		
	}
}