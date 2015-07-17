package com.wallet.shop.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.shop.model.custom.ShopCategoryPrd;

public interface ShopCategoryPrdMapper {

	/**
	 * @Method Name : selectShopCategoryPrdList
	 * @Description : 카테고리 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<ShopCategoryPrd>
	 * @author 김태리
	 * @since 2013.04.15
	 */
	List<ShopCategoryPrd> selectShopCategoryPrdList(HashMap<String ,Object> params);
	
	
	/**
	 * @Method Name : selectShopCategoryPrdListCnt
	 * @Description : 카테고리 목록 수 조회
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2013.04.15
	 */
	Integer selectShopCategoryPrdListCnt(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : insertShopCategoryPrd
	 * @Description : 카테고리 등록
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2013.04.15
	 */
	Integer insertShopCategoryPrd(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : updateShopCategoryPrd
	 * @Description : 카테고리 수정
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2013.04.15
	 */
	Integer updateShopCategoryPrd(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : selectTotPrdCnt
	 * @Description : 노출중인 상품 총 수 조회
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2013.04.15
	 */
	Integer selectTotPrdCnt(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : selectLastPrdId
	 * @Description : 마지막 등록된 상품id 조회
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2013.04.15
	 */
	String selectLastPrdId(HashMap<String, Object> params);
}