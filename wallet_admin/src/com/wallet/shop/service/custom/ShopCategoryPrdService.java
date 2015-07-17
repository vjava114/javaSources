package com.wallet.shop.service.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.shop.dao.custom.ShopCategoryDao;
import com.wallet.shop.dao.custom.ShopCategoryPrdDao;
import com.wallet.shop.model.custom.ShopCategory;
import com.wallet.shop.model.custom.ShopCategoryPrd;

public class ShopCategoryPrdService {
	private final ShopCategoryPrdDao sDao;

	/**
	 * @Method Name : ShopCategoryPrdService
	 * @Description : 생성자
	 * @param : 
	 * @return : 
	 * @author 김태리
	 * @since 2013.04.08
	 */
	public ShopCategoryPrdService() {
		sDao = new ShopCategoryPrdDao();
	}
	
	/**
	 * @Method Name : commit
	 * @Description : 
	 * @param : 
	 * @return : void
	 * @author 김태리
	 * @since 2013.04.08
	 */
	public void commit(){
		sDao.commit();
	}
	
	/**
	 * @Method Name : rollback
	 * @Description :
	 * @param : 
	 * @return : 
	 * @author 김태리
	 * @since 2012.09.14
	 */
	public void rollback(){
		sDao.rollback();
	}
	
	/**
	 * @Method Name : selectTotCategoryCnt
	 * @Description : 카테고리 대분류 총 수 조회
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2013.04.11
	 */
	public Integer selectTotCategoryCnt(HashMap<String ,Object> params) {
		Integer result = null;
		
		result = sDao.selectTotCategoryCnt(params); 

		return result;
	}
	
	/**
	 * @Method Name : selectTotPrdCnt
	 * @Description : 카테고리 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2013.04.11
	 */
	public Integer selectTotPrdCnt(HashMap<String ,Object> params) {
		Integer result = null;
		
		result = sDao.selectTotPrdCnt(params); 

		return result;
	}
	
	
	/**
	 * @Method Name : selectShopCategoryPrdList
	 * @Description : 카테고리 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<ShopCategoryPrd>
	 * @author 김태리
	 * @since 2013.04.08
	 */
	public List<ShopCategoryPrd> selectShopCategoryPrdList(HashMap<String ,Object> params) {
		List<ShopCategoryPrd> result = null;
		
		result = sDao.selectShopCategoryPrdList(params); //-- 카테고리 목록을 조회함.

		return result;
	}
	
	/**
	 * @Method Name : selectShopCategoryPrdListCnt
	 * @Description : 카테고리 목록 수 조회
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2013.04.08
	 */
	public int selectShopCategoryPrdListCnt(HashMap<String, Object> params) {
		int result = 0;
		
		result = Integer.parseInt(sDao.selectShopCategoryPrdListCnt(params).toString()); //-- 카테고리 목록을 조회함.

		return result;
	}
	
	/**
	 * @Method Name : selectShopPrdList
	 * @Description : 카테고리 중분류 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<ShopCategoryPrd>
	 * @author 김태리
	 * @since 2013.04.10
	 */
	public List<ShopCategoryPrd> selectShopPrdList(HashMap<String, Object> params){
		List<ShopCategoryPrd> result = null;
		
		result = sDao.selectShopCategoryPrdList(params); //-- 카테고리 목록을 조회함.

		return result;
	}
	/**
	 * @Method Name : selectAShopCategoryPrdInfo
	 * @Description : 카테고리 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<ShopCategoryPrd>
	 * @author 김태리
	 * @since 2013.04.08
	 */
	@SuppressWarnings("unchecked")
	public ShopCategoryPrd selectAShopCategoryPrdInfo(HashMap<String,Object> params){
		ShopCategoryPrd aShopPrd = sDao.selectAShopCategoryPrdInfo(params);
		
		return aShopPrd;
	}
	
	/**
	 * @Method Name : insertShopCategoryPrd
	 * @Description : 카테고리 등록
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2013.04.08
	 */
	public int insertShopCategoryPrd(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.insertShopCategoryPrd(params);
		
		return result;
	}
	
	/**
	 * @Method Name : updateShopCategoryPrd
	 * @Description : 카테고리 수정
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2013.04.08
	 */
	public int updateShopCategoryPrd(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.updateShopCategoryPrd(params);
		
		return result;
	}
	
	/**
	 * @Method Name : deleteShopCategoryPrd
	 * @Description : 카테고리 삭제
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2013.04.08
	 */
	public int deleteShopCategoryPrd(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.deleteShopCategoryPrd(params);
		
		return result;
	}
	
	/**
	 * @Method Name : updateDelCategoryInfo
	 * @Description : 카테고리 삭제여부 수정
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2013.04.12
	 */
	public int updateDelCategoryInfo(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.updateDelCategoryInfo(params);
		
		return result;
	}
	/**
	 * @Method Name : selectLastPrdId
	 * @Description : 마지막 등록된 카테고리 ID 조회
	 * @param : HashMap<String ,Object>
	 * @return : String
	 * @author 김태리
	 * @since 2013.04.09
	 */
	public String selectLastPrdId(HashMap<String, Object> params){
		String result = "";
		
		result = sDao.selectLastPrdId(params);
		
		return result;
	}
	
	public int selectShowCategoryPrdListCnt(HashMap<String, Object> params){
		int result = 0;
		result = Integer.parseInt(sDao.selectShowCategoryPrdListCnt(params).toString()); //-- 멤버십 목록을 조회함.
		return result;
	}
}
