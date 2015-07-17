package com.wallet.membership.service.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.dao.custom.ImageDao;
import com.wallet.membership.model.custom.Image;

public class ImageService {
	private final ImageDao sDao;

	/**
	 * @Method Name : ImageService
	 * @Description : 생성자
	 * @param : 
	 * @return : 
	 * @author 김태리
	 * @since 2012.09.15
	 */
	public ImageService() {
		sDao = new ImageDao();
	}
	
	/**
	 * @Method Name : commit
	 * @Description : 
	 * @param : 
	 * @return : void
	 * @author 김태리
	 * @since 2012.09.15
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
	 * @Method Name : selectImageList
	 * @Description : 결제사 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<Image>
	 * @author 김태리
	 * @since 2012.09.15
	 */
	public List<Image> selectImageList(HashMap<String ,Object> params) {
		List<Image> result = null;
		
		result = sDao.selectImageList(params); //-- 이벤트/공지사항 목록을 조회함.

		return result;
	}
	
	/**
	 * @Method Name : selectImageListCnt
	 * @Description : 결제사 목록 수 조회
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2012.09.15
	 */
	public int selectImageListCnt(HashMap<String, Object> params) {
		int result = 0;
		
		result = Integer.parseInt(sDao.selectImageListCnt(params).toString()); //-- 이벤트/공지사항 목록을 조회함.

		return result;
	}

	
	/**
	 * @Method Name : selectAImage
	 * @Description : 결제사 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<Image>
	 * @author 김태리
	 * @since 2012.09.15
	 */
	@SuppressWarnings("unchecked")
	public Image selectAImage(HashMap<String,Object> params){
		Image aImage = sDao.selectAImage(params);
		
		return aImage;
	}
	
	/**
	 * @Method Name : insertImage
	 * @Description : 결제사 등록
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2012.09.15
	 */
	public int insertImage(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.insertImage(params);
		
		return result;
	}
	
	/**
	 * @Method Name : updateImage
	 * @Description : 결제사 수정
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2012.09.15
	 */
	public int updateImage(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.updateImage(params);
		
		return result;
	}
	
	/**
	 * @Method Name : deleteImage
	 * @Description : 결제사 삭제
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2012.09.15
	 */
	public int deleteImage(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.deleteImage(params);
		
		return result;
	}
}
