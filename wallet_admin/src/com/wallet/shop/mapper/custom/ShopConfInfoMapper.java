package com.wallet.shop.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.shop.model.custom.ShopConfInfo;


public interface ShopConfInfoMapper {
	/**
	 * @Method Name : selectShopConfInfoList
	 * @Description : �������� ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<ShopConfInfo>
	 * @author ���¸�
	 * @since 2013.04.18
	 */
	List<ShopConfInfo> selectShopConfInfoList(HashMap<String ,Object> params);
	

	/**
	 * @Method Name : insertShopConfInfo
	 * @Description : �������� ���
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2013.04.18
	 */
	Integer insertShopConfInfo(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : updateShopConfInfo
	 * @Description : �������� ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2013.04.18
	 */
	Integer updateShopConfInfo(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : deleteShopConfInfo
	 * @Description : �������� ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2013.04.18
	 */
	Integer deleteShopConfInfo(HashMap<String ,Object> params);

	/**
	 * @Method Name : selectUpperConfIdList
	 * @Description : �������� ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<ShopConfInfo>
	 * @author ���¸�
	 * @since 2013.04.18
	 */
	List<ShopConfInfo> selectUpperConfIdList(HashMap<String ,Object> params);
	
}
