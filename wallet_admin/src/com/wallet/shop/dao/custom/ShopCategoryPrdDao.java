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
	 * @Description : 생성자
	 * @param : 
	 * @return : 
	 * @author 김태리
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
	 * @author 김태리
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
	 * @author 김태리
	 * @since 2013.04.15
	 */
	public void rollback(){
		sqlMapper.rollback();
	}
	

	
	/**
	 * @Method Name : selectTotCategoryCnt
	 * @Description : 대분류 상품의 총 수를 조회한다.
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
	 * @Description : 중분류 상품의 총 수를 조회한다.
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
	 * @Method Name : selectShopCategoryPrdList
	 * @Description : 상품 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<ShopCategoryPrd>
	 * @author 김태리
	 * @since 2013.04.15
	 */
	@SuppressWarnings("unchecked")
	public List<ShopCategoryPrd> selectShopCategoryPrdList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectShopCategoryPrdList", params);
	}
	
	
	/**
	 * @Method Name : selectShopCategoryPrdListCnt
	 * @Description : 상품 목록 수 조회
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
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
	 * @Description : 상품 조회
	 * @return : List<ShopCategoryPrd>
	 * @author 김태리
	 * @since 2013.04.15
	 */
	@SuppressWarnings("unchecked")
	public ShopCategoryPrd selectAShopCategoryPrdInfo(HashMap<String,Object> params){
		return (ShopCategoryPrd) sqlMapper.selectOne(preMapperName + "selectShopCategoryPrdInfo", params);
	}
	
	
	/**
	 * @Method Name : insertShopCategoryPrd
	 * @Description : 상품 등록
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
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
	 * @Description : 상품 수정
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
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
	 * @Description : 상품 삭제
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
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
	 * @Description : 상품 삭제여부 수정
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
	 * @Description : 마지막 등록된 상품 ID 조히
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
	 * @Method Name : selectShopCategoryPrdInfo
	 * @Description : 상품 조회
	 * @return : ShopCategoryPrd
	 * @author 김태리
	 * @since 2013.04.15
	 */
	@SuppressWarnings("unchecked")
	public ShopCategoryPrd selectShopCategoryPrdInfo(HashMap<String,Object> params){
		return (ShopCategoryPrd) sqlMapper.selectOne(preMapperName + "selectShopCategoryPrdInfo", params);
	}
	
	/**
	 * @Method Name : selectShopCategoryPrdInfo
	 * @Description : 상품 조회
	 * @return : ShopCategoryPrd
	 * @author 김태리
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
