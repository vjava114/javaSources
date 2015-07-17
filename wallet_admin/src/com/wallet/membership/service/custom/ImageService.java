package com.wallet.membership.service.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.dao.custom.ImageDao;
import com.wallet.membership.model.custom.Image;

public class ImageService {
	private final ImageDao sDao;

	/**
	 * @Method Name : ImageService
	 * @Description : ������
	 * @param : 
	 * @return : 
	 * @author ���¸�
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
	 * @author ���¸�
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
	 * @author ���¸�
	 * @since 2012.09.14
	 */
	public void rollback(){
		sDao.rollback();
	}
	
	/**
	 * @Method Name : selectImageList
	 * @Description : ������ ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<Image>
	 * @author ���¸�
	 * @since 2012.09.15
	 */
	public List<Image> selectImageList(HashMap<String ,Object> params) {
		List<Image> result = null;
		
		result = sDao.selectImageList(params); //-- �̺�Ʈ/�������� ����� ��ȸ��.

		return result;
	}
	
	/**
	 * @Method Name : selectImageListCnt
	 * @Description : ������ ��� �� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ���¸�
	 * @since 2012.09.15
	 */
	public int selectImageListCnt(HashMap<String, Object> params) {
		int result = 0;
		
		result = Integer.parseInt(sDao.selectImageListCnt(params).toString()); //-- �̺�Ʈ/�������� ����� ��ȸ��.

		return result;
	}

	
	/**
	 * @Method Name : selectAImage
	 * @Description : ������ ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<Image>
	 * @author ���¸�
	 * @since 2012.09.15
	 */
	@SuppressWarnings("unchecked")
	public Image selectAImage(HashMap<String,Object> params){
		Image aImage = sDao.selectAImage(params);
		
		return aImage;
	}
	
	/**
	 * @Method Name : insertImage
	 * @Description : ������ ���
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ���¸�
	 * @since 2012.09.15
	 */
	public int insertImage(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.insertImage(params);
		
		return result;
	}
	
	/**
	 * @Method Name : updateImage
	 * @Description : ������ ����
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ���¸�
	 * @since 2012.09.15
	 */
	public int updateImage(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.updateImage(params);
		
		return result;
	}
	
	/**
	 * @Method Name : deleteImage
	 * @Description : ������ ����
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ���¸�
	 * @since 2012.09.15
	 */
	public int deleteImage(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.deleteImage(params);
		
		return result;
	}
}
