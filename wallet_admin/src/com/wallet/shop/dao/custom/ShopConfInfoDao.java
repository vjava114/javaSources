package com.wallet.shop.dao.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.shop.mapper.custom.ShopConfInfoMapper;
import com.wallet.shop.model.custom.ShopConfInfo;

public class ShopConfInfoDao extends MybatisCilent implements ShopConfInfoMapper {

	private String preMapperName = "com.wallet.shop.mapper.custom.ShopConfInfoMapper.";
	

	/**
	 * @Method Name : ShopConfInfoDao
	 * @Description : 생성자
	 * @param : 
	 * @return : 
	 * @author 김태리
	 * @since 2013.04.18
	 */
	public ShopConfInfoDao() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @Method Name : commit
	 * @Description :
	 * @param : 
	 * @return : 
	 * @author 김태리
	 * @since 2013.04.18
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
	 * @since 2013.04.18
	 */
	public void rollback(){
		sqlMapper.rollback();
	}
	
	
	/**
	 * @Method Name : selectShopConfInfoList
	 * @Description : 설정정보 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<ShopConfInfo>
	 * @author 김태리
	 * @since 2013.04.18
	 */
	@SuppressWarnings("unchecked")
	public List<ShopConfInfo> selectShopConfInfoList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectShopConfInfoList", params);
	}
	
	
	/**
	 * @Method Name : insertShopConfInfo
	 * @Description : 설정정보 등록
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2013.04.18
	 */
	@SuppressWarnings("unchecked")
	public Integer insertShopConfInfo(HashMap<String,Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.insert(preMapperName + "insertShopConfInfo", params));
		return result;
	}
	
	
	/**
	 * @Method Name : updateShopConfInfo
	 * @Description : 설정정보 수정
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2013.04.18
	 */
	@SuppressWarnings("unchecked")
	public Integer updateShopConfInfo(HashMap<String,Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.update(preMapperName + "updateShopConfInfo", params));
		return result;
	}
	
	/**
	 * @Method Name : deleteShopConfInfo
	 * @Description : 설정정보 삭제
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2013.04.18
	 */
	@SuppressWarnings("unchecked")
	public Integer deleteShopConfInfo(HashMap<String, Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.delete(preMapperName + "deleteShopConfInfo", params));
		return result;
	}
	

	
	
	/**
	 * @Method Name : selectUpperConfIdList
	 * @Description : 설정정보 최상위 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<ShopConfInfo>
	 * @author 김태리
	 * @since 2013.04.18
	 */
	@SuppressWarnings("unchecked")
	public List<ShopConfInfo> selectUpperConfIdList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectUpperConfIdList", params);
	}
	
}
