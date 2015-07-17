package com.wallet.shop.service.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.shop.dao.custom.ShopConfInfoDao;
import com.wallet.shop.model.custom.ShopConfInfo;

public class ShopConfInfoService {
	private final ShopConfInfoDao sDao;

	/**
	 * @Method Name : ShopConfInfoService
	 * @Description : 생성자
	 * @param : 
	 * @return : 
	 * @author 김태리
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
	 * @author 김태리
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
	 * @author 김태리
	 * @since 2012.09.14
	 */
	public void rollback(){
		sDao.rollback();
	}
	
	
	/**
	 * @Method Name : selectShopConfInfoList
	 * @Description : 공지사항/이벤트 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<ShopConfInfo>
	 * @author 김태리
	 * @since 2013.04.15
	 */
	public List<ShopConfInfo> selectShopConfInfoList(HashMap<String ,Object> params) {
		List<ShopConfInfo> result = null;
		
		result = sDao.selectShopConfInfoList(params); //-- 공지사항/이벤트 목록을 조회함.

		return result;
	}
	
	
	/**
	 * @Method Name : insertShopConfInfo
	 * @Description : 공지사항/이벤트 등록
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2013.04.15
	 */
	public int insertShopConfInfo(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.insertShopConfInfo(params);
		
		return result;
	}
	
	/**
	 * @Method Name : updateShopConfInfo
	 * @Description : 공지사항/이벤트 수정
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2013.04.15
	 */
	public int updateShopConfInfo(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.updateShopConfInfo(params);
		
		return result;
	}
	
	/**
	 * @Method Name : deleteShopConfInfo
	 * @Description : 공지사항/이벤트 삭제
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2013.04.15
	 */
	public int deleteShopConfInfo(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.deleteShopConfInfo(params);
		
		return result;
	}
	
	/**
	 * @Method Name : selectShopConfInfoList
	 * @Description : 공지사항/이벤트 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<ShopConfInfo>
	 * @author 김태리
	 * @since 2013.04.15
	 */
	public List<ShopConfInfo> selectUpperConfIdList(HashMap<String ,Object> params) {
		List<ShopConfInfo> result = null;
		
		result = sDao.selectUpperConfIdList(params); //-- 공지사항/이벤트 목록을 조회함.

		return result;
	}
	
}
