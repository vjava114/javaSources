package com.wallet.shop.service.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.shop.dao.custom.ShopConfInfoDao;
import com.wallet.shop.model.custom.ShopConfInfo;

public class ShopConfInfoService {
	private final ShopConfInfoDao sDao;

	/**
	 * @Method Name : ShopConfInfoService
	 * @Description : ������
	 * @param : 
	 * @return : 
	 * @author ���¸�
	 * @since 2013.04.15
	 */
	public ShopConfInfoService() {
		sDao = new ShopConfInfoDao();
	}
	
	/**
	 * @Method Name : commit
	 * @Description : 
	 * @param : 
	 * @return : void
	 * @author ���¸�
	 * @since 2013.04.15
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
	 * @Method Name : selectShopConfInfoList
	 * @Description : ��������/�̺�Ʈ ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<ShopConfInfo>
	 * @author ���¸�
	 * @since 2013.04.15
	 */
	public List<ShopConfInfo> selectShopConfInfoList(HashMap<String ,Object> params) {
		List<ShopConfInfo> result = null;
		
		result = sDao.selectShopConfInfoList(params); //-- ��������/�̺�Ʈ ����� ��ȸ��.

		return result;
	}
	
	
	/**
	 * @Method Name : insertShopConfInfo
	 * @Description : ��������/�̺�Ʈ ���
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ���¸�
	 * @since 2013.04.15
	 */
	public int insertShopConfInfo(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.insertShopConfInfo(params);
		
		return result;
	}
	
	/**
	 * @Method Name : updateShopConfInfo
	 * @Description : ��������/�̺�Ʈ ����
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ���¸�
	 * @since 2013.04.15
	 */
	public int updateShopConfInfo(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.updateShopConfInfo(params);
		
		return result;
	}
	
	/**
	 * @Method Name : deleteShopConfInfo
	 * @Description : ��������/�̺�Ʈ ����
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ���¸�
	 * @since 2013.04.15
	 */
	public int deleteShopConfInfo(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.deleteShopConfInfo(params);
		
		return result;
	}
	
	/**
	 * @Method Name : selectShopConfInfoList
	 * @Description : ��������/�̺�Ʈ ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<ShopConfInfo>
	 * @author ���¸�
	 * @since 2013.04.15
	 */
	public List<ShopConfInfo> selectUpperConfIdList(HashMap<String ,Object> params) {
		List<ShopConfInfo> result = null;
		
		result = sDao.selectUpperConfIdList(params); //-- ��������/�̺�Ʈ ����� ��ȸ��.

		return result;
	}
	
}
