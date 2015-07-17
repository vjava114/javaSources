package com.wallet.shop.service.custom;


import java.util.HashMap;
import java.util.List;

import com.wallet.shop.dao.custom.ShopCategoryDao;
import com.wallet.shop.model.custom.ShopCategory;

public class ShopCategoryService {
	private final ShopCategoryDao sDao;

	/**
	 * @Method Name : ShopCategoryService
	 * @Description : ������
	 * @param : 
	 * @return : 
	 * @author ���¸�
	 * @since 2013.04.08
	 */
	public ShopCategoryService() {
		sDao = new ShopCategoryDao();
	}
	
	/**
	 * @Method Name : commit
	 * @Description : 
	 * @param : 
	 * @return : void
	 * @author ���¸�
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
	 * @author ���¸�
	 * @since 2012.09.14
	 */
	public void rollback(){
		sDao.rollback();
	}
	
	/**
	 * @Method Name : selectTotCategoryCnt
	 * @Description : ī�װ� ��з� �� �� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2013.04.11
	 */
	public Integer selectTotCategoryCnt(HashMap<String ,Object> params) {
		Integer result = null;
		
		result = sDao.selectTotCategoryCnt(params); 

		return result;
	}
	
	/**
	 * @Method Name : selectTotSecondCategoryCnt
	 * @Description : ī�װ� ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2013.04.11
	 */
	public Integer selectTotSecondCategoryCnt(HashMap<String ,Object> params) {
		Integer result = null;
		
		result = sDao.selectTotSecondCategoryCnt(params); 

		return result;
	}
	
	
	/**
	 * @Method Name : selectShopCategoryList
	 * @Description : ī�װ� ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<ShopCategory>
	 * @author ���¸�
	 * @since 2013.04.08
	 */
	public List<ShopCategory> selectShopCategoryList(HashMap<String ,Object> params) {
		List<ShopCategory> result = null;
		
		result = sDao.selectShopCategoryList(params); //-- ī�װ� ����� ��ȸ��.

		return result;
	}
	
	/**
	 * @Method Name : selectShopCategoryListCnt
	 * @Description : ī�װ� ��� �� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ���¸�
	 * @since 2013.04.08
	 */
	public int selectShopCategoryListCnt(HashMap<String, Object> params) {
		int result = 0;
		
		result = Integer.parseInt(sDao.selectShopCategoryListCnt(params).toString()); //-- ī�װ� ����� ��ȸ��.

		return result;
	}
	
	/**
	 * @Method Name : selectShopSecondCategoryList
	 * @Description : ī�װ� �ߺз� ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<ShopCategory>
	 * @author ���¸�
	 * @since 2013.04.10
	 */
	public List<ShopCategory> selectShopSecondCategoryList(HashMap<String, Object> params){
		List<ShopCategory> result = null;
		
		result = sDao.selectShopSecondCategoryList(params); //-- ī�װ� ����� ��ȸ��.

		return result;
	}
	/**
	 * @Method Name : selectAShopCategory
	 * @Description : ī�װ� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<ShopCategory>
	 * @author ���¸�
	 * @since 2013.04.08
	 */
	@SuppressWarnings("unchecked")
	public ShopCategory selectAShopCategory(HashMap<String,Object> params){
		ShopCategory aShopCategory = sDao.selectAShopCategory(params);
		
		return aShopCategory;
	}
	
	/**
	 * @Method Name : insertShopCategory
	 * @Description : ī�װ� ���
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ���¸�
	 * @since 2013.04.08
	 */
	public int insertShopCategory(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.insertShopCategory(params);
		
		return result;
	}
	
	/**
	 * @Method Name : updateShopCategory
	 * @Description : ī�װ� ����
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ���¸�
	 * @since 2013.04.08
	 */
	public int updateShopCategory(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.updateShopCategory(params);
		
		return result;
	}
	
	/**
	 * @Method Name : deleteShopCategory
	 * @Description : ī�װ� ����
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ���¸�
	 * @since 2013.04.08
	 */
	public int deleteShopCategory(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.deleteShopCategory(params);
		
		return result;
	}
	
	/**
	 * @Method Name : updateDelCategoryInfo
	 * @Description : ī�װ� �������� ����
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ���¸�
	 * @since 2013.04.12
	 */
	public int updateDelCategoryInfo(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.updateDelCategoryInfo(params);
		
		return result;
	}
	/**
	 * @Method Name : selectLastCateId
	 * @Description : ������ ��ϵ� ī�װ� ID ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : String
	 * @author ���¸�
	 * @since 2013.04.09
	 */
	public String selectLastCateId(HashMap<String, Object> params){
		String result = "";
		
		result = sDao.selectLastCateId(params);
		
		return result;
	}
	
	/**
	 * @Method Name : updateShopCatogoryOrder
	 * @Description : ī�װ��� ������ ������
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2013.04.11
	 */
	public int updateShopCatogoryOrder(HashMap<String, Object> params){
		int result = sDao.updateShopCatogoryOrder(params);
		return result;
	}
	
	/**
	 * @Method Name : updateShopCatogoryOrderOpt
	 * @Description : ī�װ��� ���ü����� ������2
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2013.04.11
	 */
	public int updateShopCatogoryOrderOpt(HashMap<String, Object> params){
		int result = sDao.updateShopCatogoryOrderOpt(params);
		return result;
	}
	
	/**
	 * @Method Name : updateShopCatogoryOrderOpt
	 * @Description : ī�װ��� ���ü����� ������2
	 * @param : HashMap<String ,Object>
	 * @return : ShopCategory
	 * @author ���¸�
	 * @since 2013.04.11
	 */

	public ShopCategory selectShopSecondCategoryInfo(HashMap<String,Object> params){
		ShopCategory aShopCategory = sDao.selectShopSecondCategoryInfo(params);
		return aShopCategory;
	}
	
	public int selectShowCategoryListCnt(HashMap<String, Object> params){
		int result = 0;
		result = Integer.parseInt(sDao.selectShowCategoryListCnt(params).toString()); //-- ����� ����� ��ȸ��.
		return result;
	}
	
}
