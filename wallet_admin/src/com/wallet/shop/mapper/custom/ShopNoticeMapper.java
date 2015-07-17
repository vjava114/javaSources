package com.wallet.shop.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.shop.model.custom.ShopNotice;

public interface ShopNoticeMapper {

	/**
	 * @Method Name : selectShopNoticeList
	 * @Description : �̺�Ʈ/�������� ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<ShopNotice>
	 * @author ���¸�
	 * @since 2013.04.15
	 */
	List<ShopNotice> selectShopNoticeList(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : selectAShopNoticeInfo
	 * @Description : �̺�Ʈ/�������� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : ShopNotice
	 * @author ���¸�
	 * @since 2013.04.15
	 */
	ShopNotice selectAShopNoticeInfo(HashMap<String, Object> params);

	/**
	 * @Method Name : selectShopNoticeListCnt
	 * @Description : �̺�Ʈ/�������� ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2013.04.15
	 */
	Integer selectShopNoticeListCnt(HashMap<String ,Object> params);

	/**
	 * @Method Name : insertShopNotice
	 * @Description : �̺�Ʈ/�������� ���
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2013.04.15
	 */
	Integer insertShopNotice(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : updateShopNotice
	 * @Description : �̺�Ʈ/�������� ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2013.04.15
	 */
	Integer updateShopNotice(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : updateDelNoticeInfo
	 * @Description : �̺�Ʈ/�������� �������� ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2013.04.15
	 */
	Integer updateDelNoticeInfo(HashMap<String, Object> params);
	
}
