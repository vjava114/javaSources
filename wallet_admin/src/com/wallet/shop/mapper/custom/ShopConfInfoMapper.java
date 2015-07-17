package com.wallet.shop.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.shop.model.custom.ShopConfInfo;


public interface ShopConfInfoMapper {
	/**
	 * @Method Name : selectShopConfInfoList
	 * @Description : 설정정보 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<ShopConfInfo>
	 * @author 김태리
	 * @since 2013.04.18
	 */
	List<ShopConfInfo> selectShopConfInfoList(HashMap<String ,Object> params);
	

	/**
	 * @Method Name : insertShopConfInfo
	 * @Description : 설정정보 등록
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2013.04.18
	 */
	Integer insertShopConfInfo(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : updateShopConfInfo
	 * @Description : 설정정보 수정
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2013.04.18
	 */
	Integer updateShopConfInfo(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : deleteShopConfInfo
	 * @Description : 설정정보 수정
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2013.04.18
	 */
	Integer deleteShopConfInfo(HashMap<String ,Object> params);

	/**
	 * @Method Name : selectUpperConfIdList
	 * @Description : 설정정보 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<ShopConfInfo>
	 * @author 김태리
	 * @since 2013.04.18
	 */
	List<ShopConfInfo> selectUpperConfIdList(HashMap<String ,Object> params);
	
}
