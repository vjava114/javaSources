package com.wallet.shop.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.shop.model.custom.ShopCategory;

public interface ShopCategoryMapper {
	/**
	 * @Method Name : selectTotCategoryCnt
	 * @Description : ��ī�� ��з� ī�װ� ��ü �� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2013.04.11
	 */
	Integer selectTotCategoryCnt(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : selectTotSecondCategoryCnt
	 * @Description : ��ī�� �ߺз� ī�װ� ��ü  ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2013.04.11
	 */
	Integer selectTotSecondCategoryCnt(HashMap<String ,Object> params);
	
	
	/**
	 * @Method Name : selectShopCategoryList
	 * @Description : ī�װ� ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<ShopCategory>
	 * @author ���¸�
	 * @since 2013.04.05
	 */
	List<ShopCategory> selectShopCategoryList(HashMap<String ,Object> params);
	
		
	/**
	 * @Method Name : insertShopCategory
	 * @Description : ī�װ� ���
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2013.04.05
	 */
	Integer insertShopCategory(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : updateShopCategory
	 * @Description : ī�װ� ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2013.04.05
	 */
	Integer updateShopCategory(HashMap<String ,Object> params);
}
