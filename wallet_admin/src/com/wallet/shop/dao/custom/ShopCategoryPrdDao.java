package com.wallet.shop.dao.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.shop.model.custom.ShopCategoryPrd;
import com.wallet.shop.mapper.custom.ShopCategoryPrdMapper;

public class ShopCategoryPrdDao extends MybatisCilent implements ShopCategoryPrdMapper {

	private String preMapperName = "com.wallet.shop.mapper.custom.ShopCategoryPrdMapper.";

	/**
	 * @Method Name : ShopCategoryPrdDao
	 * @Description : ������
	 * @param : 
	 * @return : 
	 * @author ���¸�
	 * @since 2013.04.15
	 */
	public ShopCategoryPrdDao() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @Method Name : commit
	 * @Description :
	 * @param : 
	 * @return : 
	 * @author ���¸�
	 * @since 2013.04.15
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
	 * @since 2013.04.15
	 */
	public void rollback(){
		sqlMapper.rollback();
	}
	

	
	/**
	 * @Method Name : selectTotCategoryCnt
	 * @Description : ��з� ��ǰ�� �� ���� ��ȸ�Ѵ�.
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
	 * @Description : �ߺз� ��ǰ�� �� ���� ��ȸ�Ѵ�.
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
	 * @Method Name : selectShopCategoryPrdList
	 * @Description : ��ǰ ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<ShopCategoryPrd>
	 * @author ���¸�
	 * @since 2013.04.15
	 */
	@SuppressWarnings("unchecked")
	public List<ShopCategoryPrd> selectShopCategoryPrdList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectShopCategoryPrdList", params);
	}
	
	
	/**
	 * @Method Name : selectShopCategoryPrdListCnt
	 * @Description : ��ǰ ��� �� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2013.04.15
	 */
	@SuppressWarnings("unchecked")
	public Integer selectShopCategoryPrdListCnt(HashMap<String,Object> params){
		Integer cnt = 0;
		
		cnt = (Integer) sqlMapper.selectOne(preMapperName + "selectShopCategoryPrdListCnt", params);
		return cnt;
	}

	/**
	 * @Method Name : selectAShopCategoryPrd
	 * @Description : ��ǰ ��ȸ
	 * @return : List<ShopCategoryPrd>
	 * @author ���¸�
	 * @since 2013.04.15
	 */
	@SuppressWarnings("unchecked")
	public ShopCategoryPrd selectAShopCategoryPrdInfo(HashMap<String,Object> params){
		return (ShopCategoryPrd) sqlMapper.selectOne(preMapperName + "selectShopCategoryPrdInfo", params);
	}
	
	
	/**
	 * @Method Name : insertShopCategoryPrd
	 * @Description : ��ǰ ���
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2013.04.15
	 */
	@SuppressWarnings("unchecked")
	public Integer insertShopCategoryPrd(HashMap<String,Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.insert(preMapperName + "insertShopCategoryPrd", params));
		return result;
	}
	
	
	/**
	 * @Method Name : updateShopCategoryPrd
	 * @Description : ��ǰ ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2013.04.15
	 */
	@SuppressWarnings("unchecked")
	public Integer updateShopCategoryPrd(HashMap<String,Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.update(preMapperName + "updateShopCategoryPrd", params));
		return result;
	}
	
	/**
	 * @Method Name : updateShopCategoryPrd
	 * @Description : ��ǰ ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2013.04.15
	 */
	@SuppressWarnings("unchecked")
	public Integer deleteShopCategoryPrd(HashMap<String, Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.delete(preMapperName + "deleteShopCategoryPrd", params));
		return result;
	}
	/**
	 * @Method Name : updateDelCategoryInfo
	 * @Description : ��ǰ �������� ����
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
	 * @Description : ������ ��ϵ� ��ǰ ID ����
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
	 * @Method Name : selectShopCategoryPrdInfo
	 * @Description : ��ǰ ��ȸ
	 * @return : ShopCategoryPrd
	 * @author ���¸�
	 * @since 2013.04.15
	 */
	@SuppressWarnings("unchecked")
	public ShopCategoryPrd selectShopCategoryPrdInfo(HashMap<String,Object> params){
		return (ShopCategoryPrd) sqlMapper.selectOne(preMapperName + "selectShopCategoryPrdInfo", params);
	}
	
	/**
	 * @Method Name : selectShopCategoryPrdInfo
	 * @Description : ��ǰ ��ȸ
	 * @return : ShopCategoryPrd
	 * @author ���¸�
	 * @since 2013.04.15
	 */
	@SuppressWarnings("unchecked")
	public Integer selectShowCategoryPrdListCnt(HashMap<String, Object> params){
		Integer cnt = 0;
		cnt = (Integer) sqlMapper.selectOne(preMapperName + "selectShowCategoryPrdListCnt", params);
		return cnt;
	}
	
	@SuppressWarnings("unchecked")
	public Integer selectTotPrdCnt(HashMap<String, Object> params){
		Integer cnt = 0;
		cnt = (Integer) sqlMapper.selectOne(preMapperName + "selectTotPrdCnt", params);
		return cnt;
	}
	
	@SuppressWarnings("unchecked")
	public String selectLastPrdId(HashMap<String, Object> params){
		String result = "";
		result = sqlMapper.selectOne(preMapperName + "selectLastPrdId", params).toString();
		return result;
	}
}
