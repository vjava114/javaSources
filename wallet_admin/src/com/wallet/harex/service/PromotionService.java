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
	 * ��������� (��������) ���θ�� ���
	 */
	public List<PromotionList> selectPromotionList(HashMap<String,Object> params) {
		
		List<PromotionList> result = null;
		result = dao.selectPromotionList(params);
		return result;
	}
	
	/**
	 * ��������� (��������) ���θ�� ��� ī��Ʈ
	 */
	public int selectPromotionListCnt(HashMap<String,Object> params) {
		return Integer.parseInt(dao.selectPromotionListCnt(params).toString());
	}
	
	/**
	 * ��������� (��������) ���θ�� ��ȭ��
	 */
	public List<PromotionInfo> selectPromotionInfo(HashMap<String,Object> params) {
		
		List<PromotionInfo> result = null;
		result = dao.selectPromotionInfo(params);
		return result;
	}
	
	/**
	 *  combo �������񽺻� ��� ��ȸ
	 */
	public List<PromotionInfo> selectPayCompList() {
		
		List<PromotionInfo> result = null;
		result = dao.selectPayCompList();
		return result;
	}
	
	/**
	 *  combo ������ ��� ��ȸ
	 */
	public List<PromotionInfo> selectShopList(HashMap<String, Object>params) {
		
		List<PromotionInfo> result = null;
		result = dao.selectShopList(params);
		return result;
	}
	
	/**
	 * combo ���� ��� ��ȸ
	 */
	public List<PromotionInfo> selectBankList() {
		
		List<PromotionInfo> result = null;
		result = dao.selectBankList();
		return result;
	}
	
	/**
	 * combo �������� �ٻ������ ��� ��ȸ
	 */
	public List<PromotionInfo> selectDcUnitList() {
		
		List<PromotionInfo> result = null;
		result = dao.selectDcUnitList();
		return result;
	}
	
	/**
	 * combo �������� �ٻ��� ��� ��ȸ
	 */
	public List<PromotionInfo> selectRoundTypeList() {
		
		List<PromotionInfo> result = null;
		result = dao.selectRoundTypeList();
		return result;
	}
	
	/**
	 * combo ī�� ��� ��ȸ
	 */
	public List<PromotionInfo> selectCardList() {
		
		List<PromotionInfo> result = null;
		result = dao.selectCardList();
		return result;
	}
	
	/**
	 * combo ����Ƚ������ ��� ��ȸ
	 */
	public List<PromotionInfo> selectDcCntBaseList() {
		
		List<PromotionInfo> result = null;
		result = dao.selectDcCntBaseList();
		return result;
	}

	/**
	 * combo �󼼺��� - ���������
	 */
	public List<PromotionInfo> selectPromInfoShopList(HashMap<String, Object> params) {
		
		List<PromotionInfo> result = null;
		result = dao.selectPromInfoShopList(params);
		return result;
	}
	
	/**
	 * combo ����ȭ�� - ��������� ��ȸ
	 */
	public List<PromotionInfo> selectModifyShopList(HashMap<String, Object> params) {
		
		List<PromotionInfo> result = null;
		result = dao.selectModifyShopList(params);
		return result;
	}
	
	/**
	 * ��������� (��������) ���θ�� �������
	 */
	public int insertPromotionInfo(HashMap<String, Object> params){
		
		return dao.insertPromotionInfo(params);
		
	}
	
	/**
	 * ��������� (��������) ���θ�� ������� - MW_PS_PROMO
	 */
	public int insertPromotion(HashMap<String, Object> params){
		
		return dao.insertPromotion(params);
		
	}
	
	/**
	 * ��������� (��������) ���θ�� ������� - MW_PS_PROMO_DTL
	 */
	public int insertPromotionDtl(HashMap<String, Object> params){
		
		return dao.insertPromotionDtl(params);
		
	}
	
	/**
	 * ��������� (��������) ���θ�� ������� - MW_PS_PROMO_SHOP
	 */
	public int insertPromotionShop(HashMap<String, Object> params){
		
		return dao.insertPromotionShop(params);
		
	}
	
	/**
	 * ��������� (��������) ���θ�� �������� ��ȸ
	 */
	public List<PromotionInfo> selectPromotionUpdate(HashMap<String, Object> params) {
		
		List<PromotionInfo> result = null;
		result = dao.selectPromotionUpdate(params);
		return result;
	}
	
	/**
	 * ��������� (��������) ���θ�� �������� ������ ���� ó��  MW_PS_PROMO_SHOP
	 */
	public int deletePromotion(HashMap<String, Object> params){
		
		return dao.deletePromotion(params);
		
	}
	
	/**
	 * ��������� (��������) ���θ�� �������� ó�� MW_PS_PROMO
	 */
	public int updatePromotionPromo(HashMap<String, Object> params){
		
		return dao.updatePromotionPromo(params);
		
	}
	
	/**
	 * ��������� (��������) ���θ�� �������� ó�� MW_PS_PROMO_DTL
	 */
	public int updatePromotionPromoDtl(HashMap<String, Object> params){
		
		return dao.updatePromotionPromoDtl(params);
		
	}
}