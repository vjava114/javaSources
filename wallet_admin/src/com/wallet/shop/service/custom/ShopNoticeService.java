package com.wallet.shop.service.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.shop.dao.custom.ShopNoticeDao;
import com.wallet.shop.model.custom.ShopNotice;

public class ShopNoticeService {
	private final ShopNoticeDao sDao;

	/**
	 * @Method Name : ShopNoticeService
	 * @Description : 생성자
	 * @param : 
	 * @return : 
	 * @author 김태리
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
	 * @Method Name : selectShopNoticeList
	 * @Description : 공지사항/이벤트 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<ShopNotice>
	 * @author 김태리
	 * @since 2013.04.15
	 */
	public List<ShopNotice> selectShopNoticeList(HashMap<String ,Object> params) {
		List<ShopNotice> result = null;
		
		result = sDao.selectShopNoticeList(params); //-- 공지사항/이벤트 목록을 조회함.

		return result;
	}
	
	/**
	 * @Method Name : selectShopNoticeListCnt
	 * @Description : 공지사항/이벤트 목록 수 조회
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2013.04.15
	 */
	public int selectShopNoticeListCnt(HashMap<String, Object> params) {
		int result = 0;
		
		result = Integer.parseInt(sDao.selectShopNoticeListCnt(params).toString()); //-- 공지사항/이벤트 목록을 조회함.

		return result;
	}
	
	/**
	 * @Method Name : selectAShopNotice
	 * @Description : 공지사항/이벤트 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<ShopNotice>
	 * @author 김태리
	 * @since 2013.04.15
	 */
	@SuppressWarnings("unchecked")
	public ShopNotice selectAShopNoticeInfo(HashMap<String,Object> params){
		ShopNotice aShopNotice = sDao.selectAShopNoticeInfo(params);
		
		return aShopNotice;
	}
	
	/**
	 * @Method Name : insertShopNotice
	 * @Description : 공지사항/이벤트 등록
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2013.04.15
	 */
	public int insertShopNotice(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.insertShopNotice(params);
		
		return result;
	}
	
	/**
	 * @Method Name : updateShopNotice
	 * @Description : 공지사항/이벤트 수정
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2013.04.15
	 */
	public int updateShopNotice(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.updateShopNotice(params);
		
		return result;
	}
	
	/**
	 * @Method Name : deleteShopNotice
	 * @Description : 공지사항/이벤트 삭제
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2013.04.15
	 */
	public int deleteShopNotice(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.deleteShopNotice(params);
		
		return result;
	}
	
	/**
	 * @Method Name : updateDelNoticeInfo
	 * @Description : 공지사항/이벤트 삭제여부 수정
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2013.04.12
	 */
	public int updateDelNoticeInfo(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.updateDelNoticeInfo(params);
		
		return result;
	}
	
	/**
	 * @Method Name : selectLastNoticeId
	 * @Description : 마지막 등록된 공지사항/이벤트 ID 조회
	 * @param : HashMap<String ,Object>
	 * @return : String
	 * @author 김태리
	 * @since 2013.04.09
	 */
	public String selectLastNoticeId(HashMap<String, Object> params){
		String result = "";
		
		result = sDao.selectLastNoticeId(params);
		
		return result;
	}
	
	/**
	 * @Method Name : selectShowNoticeListCnt
	 * @Description : 노출중인 등록된 공지사항/이벤트 수 조회
	 * @param : HashMap<String ,Object>
	 * @return : String
	 * @author 김태리
	 * @since 2013.04.09
	 */
	public int selectShowNoticeListCnt(HashMap<String, Object> params){
		int result = 0;
		result = Integer.parseInt(sDao.selectShowNoticeListCnt(params).toString()); //-- 멤버십 목록을 조회함.
		return result;
	}
}
