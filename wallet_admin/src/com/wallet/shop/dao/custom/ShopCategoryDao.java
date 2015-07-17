package com.wallet.shop.dao.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.shop.model.custom.ShopCategory;
import com.wallet.shop.mapper.custom.ShopCategoryMapper;

public class ShopCategoryDao extends MybatisCilent implements ShopCategoryMapper {

	private String preMapperName = "com.wallet.shop.mapper.custom.ShopCategoryMapper.";
	
	/**
	 * @Method Name : ShopCategoryDao
	 * @Description : ������
	 * @param : 
	 * @return : 
	 * @author ���¸�
	 * @since 2013.04.08
	 */
	public ShopCategoryDao() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @Method Name : commit
	 * @Description :
	 * @param : 
	 * @return : 
	 * @author ���¸�
	 * @since 2013.04.08
	 */
	public void commit() {
		sqlMapper.commit();
	}
	
	/**
	 * @Method Name : rollback
	 * @Description :
	 * @param : 
	 * @return : 
	 * @author ���¸�
	 * @since 2013.04.08
	 */
	public void rollback(){
		sqlMapper.rollback();
	}
	

	
	/**
	 * @Method Name : selectTotCategoryCnt
	 * @Description : ��з� ī�װ��� �� ���� ��ȸ�Ѵ�.
	 * @param : HashMap<String,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2013.04.11
	 */
	public Integer selectTotCategoryCnt(HashMap<String,Object> params){
		Integer cnt = 0;
		
		cnt = (Integer) sqlMapper.selectOne(preMapperName + "selectTotCategoryCnt", params);
		return cnt;
	}
	
	/**
	 * @Method Name : selectTotSecondCategoryCnt
	 * @Description : �ߺз� ī�װ��� �� ���� ��ȸ�Ѵ�.
	 * @param : HashMap<String,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2013.04.11
	 */
	public Integer selectTotSecondCategoryCnt(HashMap<String,Object> params){
		Integer cnt = 0;
		
		cnt = (Integer) sqlMapper.selectOne(preMapperName + "selectTotSecondCategoryCnt", params);
		return cnt;
	}
	
	/**
	 * @Method Name : selectShopCategoryList
	 * @Description : ī�װ� ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<ShopCategory>
	 * @author ���¸�
	 * @since 2013.04.08
	 */
	@SuppressWarnings("unchecked")
	public List<ShopCategory> selectShopCategoryList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectShopCategoryList", params);
	}
	
	
	/**
	 * @Method Name : selectShopCategoryListCnt
	 * @Description : ī�װ� ��� �� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2013.04.08
	 */
	@SuppressWarnings("unchecked")
	public Integer selectShopCategoryListCnt(HashMap<String,Object> params){
		Integer cnt = 0;
		
		cnt = (Integer) sqlMapper.selectOne(preMapperName + "selectShopCategoryListCnt", params);
		return cnt;
	}

	/**
	 * @Method Name : selectShopSecondCategoryList
	 * @Description : ī�װ�  �ߺз�  ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<ShopCategory>
	 * @author ���¸�
	 * @since 2013.04.10
	 */
	@SuppressWarnings("unchecked")
	public List<ShopCategory> selectShopSecondCategoryList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectShopSecondCategoryList", params);
	}
	
	
	/**
	 * @Method Name : selectAShopCategory
	 * @Description : ī�װ� ��ȸ
	 * @return : List<ShopCategory>
	 * @author ���¸�
	 * @since 2013.04.08
	 */
	@SuppressWarnings("unchecked")
	public ShopCategory selectAShopCategory(HashMap<String,Object> params){
		return (ShopCategory) sqlMapper.selectOne(preMapperName + "selectShopCategoryInfo", params);
	}
	
	
	/**
	 * @Method Name : insertShopCategory
	 * @Description : ī�װ� ���
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2013.04.08
	 */
	@SuppressWarnings("unchecked")
	public Integer insertShopCategory(HashMap<String,Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.insert(preMapperName + "insertShopCategory", params));
		return result;
	}
	
	
	/**
	 * @Method Name : updateShopCategory
	 * @Description : ī�װ� ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2013.04.08
	 */
	@SuppressWarnings("unchecked")
	public Integer updateShopCategory(HashMap<String,Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.update(preMapperName + "updateShopCategory", params));
		return result;
	}
	
	/**
	 * @Method Name : updateShopCategory
	 * @Description : ī�װ� ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2013.04.08
	 */
	@SuppressWarnings("unchecked")
	public Integer deleteShopCategory(HashMap<String, Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.delete(preMapperName + "deleteShopCategory", params));
		return result;
	}
	/**
	 * @Method Name : updateDelCategoryInfo
	 * @Description : ī�װ� �������� ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2013.04.12
	 */
	@SuppressWarnings("unchecked")
	public Integer updateDelCategoryInfo(HashMap<String, Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.update(preMapperName + "updateDelCategoryInfo", params));
		return result;
	}
	
	/**
	 * @Method Name : selectLastCateId
	 * @Description : ������ ��ϵ� ī�װ� ID ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2013.04.09
	 */
	@SuppressWarnings("unchecked")
	public String selectLastCateId(HashMap<String, Object> params){
		String result = "";
		result = sqlMapper.selectOne(preMapperName + "selectLastCateId", params).toString();
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
	@SuppressWarnings("unchecked")
	public int updateShopCatogoryOrder(HashMap<String, Object> params){
		int result = sqlMapper.update(preMapperName + "updateShopCatogoryOrder", params);
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
	@SuppressWarnings("unchecked")
	public int updateShopCatogoryOrderOpt(HashMap<String, Object> params){
		int result = sqlMapper.update(preMapperName + "updateShopCatogoryOrderOpt", params);
		return result;
	}
	
	/**
	 * @Method Name : selectShopSecondCategoryInfo
	 * @Description : ī�װ� ��ȸ
	 * @return : ShopCategory
	 * @author ���¸�
	 * @since 2013.04.08
	 */
	@SuppressWarnings("unchecked")
	public ShopCategory selectShopSecondCategoryInfo(HashMap<String,Object> params){
		return (ShopCategory) sqlMapper.selectOne(preMapperName + "selectShopSecondCategoryInfo", params);
	}
	
	
	@SuppressWarnings("unchecked")
	public Integer selectShowCategoryListCnt(HashMap<String, Object> params){
		Integer result = (Integer) sqlMapper.selectOne(preMapperName + "selectShowCategoryListCnt", params);
		return result;
	}
	
	
}
