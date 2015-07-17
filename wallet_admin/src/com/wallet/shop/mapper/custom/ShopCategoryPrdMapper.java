package com.wallet.shop.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.shop.model.custom.ShopCategoryPrd;

public interface ShopCategoryPrdMapper {

	/**
	 * @Method Name : selectShopCategoryPrdList
	 * @Description : ī�װ� ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<ShopCategoryPrd>
	 * @author ���¸�
	 * @since 2013.04.15
	 */
	List<ShopCategoryPrd> selectShopCategoryPrdList(HashMap<String ,Object> params);
	
	
	/**
	 * @Method Name : selectShopCategoryPrdListCnt
	 * @Description : ī�װ� ��� �� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2013.04.15
	 */
	Integer selectShopCategoryPrdListCnt(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : insertShopCategoryPrd
	 * @Description : ī�װ� ���
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2013.04.15
	 */
	Integer insertShopCategoryPrd(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : updateShopCategoryPrd
	 * @Description : ī�װ� ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2013.04.15
	 */
	Integer updateShopCategoryPrd(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : selectTotPrdCnt
	 * @Description : �������� ��ǰ �� �� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2013.04.15
	 */
	Integer selectTotPrdCnt(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : selectLastPrdId
	 * @Description : ������ ��ϵ� ��ǰid ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2013.04.15
	 */
	String selectLastPrdId(HashMap<String, Object> params);
}