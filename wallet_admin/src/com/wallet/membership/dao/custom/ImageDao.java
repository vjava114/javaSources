package com.wallet.membership.dao.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.membership.mapper.custom.ImageMapper;
import com.wallet.membership.model.custom.Image;

public class ImageDao extends MybatisCilent implements ImageMapper {

	private String preMapperName = "com.wallet.membership.mapper.custom.ImageMapper.";

	
	/**
	 * @Method Name : commit
	 * @Description :
	 * @param : 
	 * @return : 
	 * @author ���¸�
	 * @since 2012.09.10
	 */
	public void commit() {
		sqlMapper.commit();
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
		sqlMapper.rollback();
	}
	
	
	/**
	 * @Method Name : ImageDao
	 * @Description : ������
	 * @param : 
	 * @return : 
	 * @author ���¸�
	 * @since 2012.09.10
	 */
	public ImageDao() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @Method Name : selectImageList
	 * @Description : �����(ī������) ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<Image>
	 * @author ���¸�
	 * @since 2012.09.10
	 */
	@SuppressWarnings("unchecked")
	public List<Image> selectImageList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectImageList", params);
	}
	
	
	/**
	 * @Method Name : selectImageListCnt
	 * @Description : �����(ī������) ��� �� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.10
	 */
	@SuppressWarnings("unchecked")
	public Integer selectImageListCnt(HashMap<String,Object> params){
		Integer cnt = 0;
		
		cnt = (Integer) sqlMapper.selectOne(preMapperName + "selectImageListCnt", params);
		return cnt;
	}

	
	/**
	 * @Method Name : selectAImage
	 * @Description : �����(ī������) ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<Image>
	 * @author ���¸�
	 * @since 2012.09.10
	 */
	@SuppressWarnings("unchecked")
	public Image selectAImage(HashMap<String,Object> params){
		return (Image) sqlMapper.selectOne(preMapperName + "selectImageInfo", params);
	}
	
	
	/**
	 * @Method Name : insertImage
	 * @Description : �����(ī������) ���
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.10
	 */
	@SuppressWarnings("unchecked")
	public Integer insertImage(HashMap<String,Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.insert(preMapperName + "insertImage", params));
		return result;
	}
	
	
	/**
	 * @Method Name : updateImage
	 * @Description : �����(ī������) ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.10
	 */
	@SuppressWarnings("unchecked")
	public Integer updateImage(HashMap<String,Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.update(preMapperName + "updateImage", params));
		return result;
	}
	
	/**
	 * @Method Name : updateImage
	 * @Description : �����(ī������) ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.10
	 */
	@SuppressWarnings("unchecked")
	public Integer deleteImage(HashMap<String, Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.delete(preMapperName + "deleteImage", params));
		return result;
	}
	
	/**
	 * @Method Name : memberDupCheck
	 * @Description : ����� ID�� ��� ��, �ߺ����θ� Ȯ���Ѵ�.
	 * @param : String
	 * @return : Integer 0/1 (0:�ߺ��ƴ�, 1:�ߺ�)
	 * @author trkim
	 * @since 2012.09.11
	 */
	public Integer memberDupCheck(String membId){
		Integer result = 0;
		
		result = (Integer) sqlMapper.selectOne(preMapperName + "memberDupCheck", membId);
		
		return result;
	}
}
