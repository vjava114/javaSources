package com.wallet.shop.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.shop.model.custom.ShopNotice;

public interface ShopNoticeMapper {

	/**
	 * @Method Name : selectShopNoticeList
	 * @Description : 이벤트/공지사항 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<ShopNotice>
	 * @author 김태리
	 * @since 2013.04.15
	 */
	List<ShopNotice> selectShopNoticeList(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : selectAShopNoticeInfo
	 * @Description : 이벤트/공지사항 조회
	 * @param : HashMap<String ,Object>
	 * @return : ShopNotice
	 * @author 김태리
	 * @since 2013.04.15
	 */
	ShopNotice selectAShopNoticeInfo(HashMap<String, Object> params);

	/**
	 * @Method Name : selectShopNoticeListCnt
	 * @Description : 이벤트/공지사항 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2013.04.15
	 */
	Integer selectShopNoticeListCnt(HashMap<String ,Object> params);

	/**
	 * @Method Name : insertShopNotice
	 * @Description : 이벤트/공지사항 등록
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2013.04.15
	 */
	Integer insertShopNotice(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : updateShopNotice
	 * @Description : 이벤트/공지사항 수정
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2013.04.15
	 */
	Integer updateShopNotice(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : updateDelNoticeInfo
	 * @Description : 이벤트/공지사항 삭제정보 수정
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2013.04.15
	 */
	Integer updateDelNoticeInfo(HashMap<String, Object> params);
	
}
