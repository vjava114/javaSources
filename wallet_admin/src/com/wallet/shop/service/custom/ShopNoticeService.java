package com.wallet.shop.service.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.shop.dao.custom.ShopNoticeDao;
import com.wallet.shop.model.custom.ShopNotice;

public class ShopNoticeService {
	private final ShopNoticeDao sDao;

	/**
	 * @Method Name : ShopNoticeService
	 * @Description : ������
	 * @param : 
	 * @return : 
	 * @author ���¸�
	 * @since 2013.04.15
	 */
	public ShopNoticeService() {
		sDao = new ShopNoticeDao();
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
	 * @Method Name : selectShopNoticeList
	 * @Description : ��������/�̺�Ʈ ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<ShopNotice>
	 * @author ���¸�
	 * @since 2013.04.15
	 */
	public List<ShopNotice> selectShopNoticeList(HashMap<String ,Object> params) {
		List<ShopNotice> result = null;
		
		result = sDao.selectShopNoticeList(params); //-- ��������/�̺�Ʈ ����� ��ȸ��.

		return result;
	}
	
	/**
	 * @Method Name : selectShopNoticeListCnt
	 * @Description : ��������/�̺�Ʈ ��� �� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ���¸�
	 * @since 2013.04.15
	 */
	public int selectShopNoticeListCnt(HashMap<String, Object> params) {
		int result = 0;
		
		result = Integer.parseInt(sDao.selectShopNoticeListCnt(params).toString()); //-- ��������/�̺�Ʈ ����� ��ȸ��.

		return result;
	}
	
	/**
	 * @Method Name : selectAShopNotice
	 * @Description : ��������/�̺�Ʈ ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<ShopNotice>
	 * @author ���¸�
	 * @since 2013.04.15
	 */
	@SuppressWarnings("unchecked")
	public ShopNotice selectAShopNoticeInfo(HashMap<String,Object> params){
		ShopNotice aShopNotice = sDao.selectAShopNoticeInfo(params);
		
		return aShopNotice;
	}
	
	/**
	 * @Method Name : insertShopNotice
	 * @Description : ��������/�̺�Ʈ ���
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ���¸�
	 * @since 2013.04.15
	 */
	public int insertShopNotice(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.insertShopNotice(params);
		
		return result;
	}
	
	/**
	 * @Method Name : updateShopNotice
	 * @Description : ��������/�̺�Ʈ ����
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ���¸�
	 * @since 2013.04.15
	 */
	public int updateShopNotice(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.updateShopNotice(params);
		
		return result;
	}
	
	/**
	 * @Method Name : deleteShopNotice
	 * @Description : ��������/�̺�Ʈ ����
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ���¸�
	 * @since 2013.04.15
	 */
	public int deleteShopNotice(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.deleteShopNotice(params);
		
		return result;
	}
	
	/**
	 * @Method Name : updateDelNoticeInfo
	 * @Description : ��������/�̺�Ʈ �������� ����
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ���¸�
	 * @since 2013.04.12
	 */
	public int updateDelNoticeInfo(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.updateDelNoticeInfo(params);
		
		return result;
	}
	
	/**
	 * @Method Name : selectLastNoticeId
	 * @Description : ������ ��ϵ� ��������/�̺�Ʈ ID ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : String
	 * @author ���¸�
	 * @since 2013.04.09
	 */
	public String selectLastNoticeId(HashMap<String, Object> params){
		String result = "";
		
		result = sDao.selectLastNoticeId(params);
		
		return result;
	}
	
	/**
	 * @Method Name : selectShowNoticeListCnt
	 * @Description : �������� ��ϵ� ��������/�̺�Ʈ �� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : String
	 * @author ���¸�
	 * @since 2013.04.09
	 */
	public int selectShowNoticeListCnt(HashMap<String, Object> params){
		int result = 0;
		result = Integer.parseInt(sDao.selectShowNoticeListCnt(params).toString()); //-- ����� ����� ��ȸ��.
		return result;
	}
}
