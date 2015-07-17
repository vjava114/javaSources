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
	 * @author 김태리
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
	 * @author 김태리
	 * @since 2012.09.14
	 */
	public void rollback(){
		sqlMapper.rollback();
	}
	
	
	/**
	 * @Method Name : ImageDao
	 * @Description : 생성자
	 * @param : 
	 * @return : 
	 * @author 김태리
	 * @since 2012.09.10
	 */
	public ImageDao() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @Method Name : selectImageList
	 * @Description : 멤버십(카드정보) 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<Image>
	 * @author 김태리
	 * @since 2012.09.10
	 */
	@SuppressWarnings("unchecked")
	public List<Image> selectImageList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectImageList", params);
	}
	
	
	/**
	 * @Method Name : selectImageListCnt
	 * @Description : 멤버십(카드정보) 목록 수 조회
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
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
	 * @Description : 멤버십(카드정보) 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<Image>
	 * @author 김태리
	 * @since 2012.09.10
	 */
	@SuppressWarnings("unchecked")
	public Image selectAImage(HashMap<String,Object> params){
		return (Image) sqlMapper.selectOne(preMapperName + "selectImageInfo", params);
	}
	
	
	/**
	 * @Method Name : insertImage
	 * @Description : 멤버십(카드정보) 등록
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
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
	 * @Description : 멤버십(카드정보) 수정
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
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
	 * @Description : 멤버십(카드정보) 삭제
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
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
	 * @Description : 멤버십 ID의 등록 전, 중복여부를 확인한다.
	 * @param : String
	 * @return : Integer 0/1 (0:중복아님, 1:중복)
	 * @author trkim
	 * @since 2012.09.11
	 */
	public Integer memberDupCheck(String membId){
		Integer result = 0;
		
		result = (Integer) sqlMapper.selectOne(preMapperName + "memberDupCheck", membId);
		
		return result;
	}
}
