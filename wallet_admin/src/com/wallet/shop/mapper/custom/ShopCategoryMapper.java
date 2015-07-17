package com.wallet.shop.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.shop.model.custom.ShopCategory;

public interface ShopCategoryMapper {
	/**
	 * @Method Name : selectTotCategoryCnt
	 * @Description : 모카샵 대분류 카테고리 전체 수 조회
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2013.04.11
	 */
	Integer selectTotCategoryCnt(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : selectTotSecondCategoryCnt
	 * @Description : 모카샵 중분류 카테고리 전체  조회
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2013.04.11
	 */
	Integer selectTotSecondCategoryCnt(HashMap<String ,Object> params);
	
	
	/**
	 * @Method Name : selectShopCategoryList
	 * @Description : 카테고리 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<ShopCategory>
	 * @author 김태리
	 * @since 2013.04.05
	 */
	List<ShopCategory> selectShopCategoryList(HashMap<String ,Object> params);
	
		
	/**
	 * @Method Name : insertShopCategory
	 * @Description : 카테고리 등록
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2013.04.05
	 */
	Integer insertShopCategory(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : updateShopCategory
	 * @Description : 카테고리 수정
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2013.04.05
	 */
	Integer updateShopCategory(HashMap<String ,Object> params);
}
