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
	 * @Description : 생성자
	 * @param : 
	 * @return : 
	 * @author 김태리
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
	 * @author 김태리
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
	 * @author 김태리
	 * @since 2013.04.08
	 */
	public void rollback(){
		sqlMapper.rollback();
	}
	

	
	/**
	 * @Method Name : selectTotCategoryCnt
	 * @Description : 대분류 카테고리의 총 수를 조회한다.
	 * @param : HashMap<String,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2013.04.11
	 */
	public Integer selectTotCategoryCnt(HashMap<String,Object> params){
		Integer cnt = 0;
		
		cnt = (Integer) sqlMapper.selectOne(preMapperName + "selectTotCategoryCnt", params);
		return cnt;
	}
	
	/**
	 * @Method Name : selectTotSecondCategoryCnt
	 * @Description : 중분류 카테고리의 총 수를 조회한다.
	 * @param : HashMap<String,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2013.04.11
	 */
	public Integer selectTotSecondCategoryCnt(HashMap<String,Object> params){
		Integer cnt = 0;
		
		cnt = (Integer) sqlMapper.selectOne(preMapperName + "selectTotSecondCategoryCnt", params);
		return cnt;
	}
	
	/**
	 * @Method Name : selectShopCategoryList
	 * @Description : 카테고리 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<ShopCategory>
	 * @author 김태리
	 * @since 2013.04.08
	 */
	@SuppressWarnings("unchecked")
	public List<ShopCategory> selectShopCategoryList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectShopCategoryList", params);
	}
	
	
	/**
	 * @Method Name : selectShopCategoryListCnt
	 * @Description : 카테고리 목록 수 조회
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
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
	 * @Description : 카테고리  중분류  조회
	 * @param : HashMap<String ,Object>
	 * @return : List<ShopCategory>
	 * @author 김태리
	 * @since 2013.04.10
	 */
	@SuppressWarnings("unchecked")
	public List<ShopCategory> selectShopSecondCategoryList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectShopSecondCategoryList", params);
	}
	
	
	/**
	 * @Method Name : selectAShopCategory
	 * @Description : 카테고리 조회
	 * @return : List<ShopCategory>
	 * @author 김태리
	 * @since 2013.04.08
	 */
	@SuppressWarnings("unchecked")
	public ShopCategory selectAShopCategory(HashMap<String,Object> params){
		return (ShopCategory) sqlMapper.selectOne(preMapperName + "selectShopCategoryInfo", params);
	}
	
	
	/**
	 * @Method Name : insertShopCategory
	 * @Description : 카테고리 등록
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
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
	 * @Description : 카테고리 수정
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
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
	 * @Description : 카테고리 삭제
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
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
	 * @Description : 카테고리 삭제여부 수정
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
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
	 * @Description : 마지막 등록된 카테고리 ID 조히
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
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
	 * @Description : 카테고리의 순서를 변경함
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2013.04.11
	 */
	@SuppressWarnings("unchecked")
	public int updateShopCatogoryOrder(HashMap<String, Object> params){
		int result = sqlMapper.update(preMapperName + "updateShopCatogoryOrder", params);
		return result;
	}
	/**
	 * @Method Name : updateShopCatogoryOrderOpt
	 * @Description : 카테고리의 전시순서를 변경함2
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2013.04.11
	 */
	@SuppressWarnings("unchecked")
	public int updateShopCatogoryOrderOpt(HashMap<String, Object> params){
		int result = sqlMapper.update(preMapperName + "updateShopCatogoryOrderOpt", params);
		return result;
	}
	
	/**
	 * @Method Name : selectShopSecondCategoryInfo
	 * @Description : 카테고리 조회
	 * @return : ShopCategory
	 * @author 김태리
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
