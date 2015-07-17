package com.wallet.shop.dao.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.shop.mapper.custom.ShopNoticeMapper;
import com.wallet.shop.model.custom.ShopNotice;

public class ShopNoticeDao extends MybatisCilent implements ShopNoticeMapper {

	private String preMapperName = "com.wallet.shop.mapper.custom.ShopNoticeMapper.";
	
	/**
	 * @Method Name : ShopNoticeDao
	 * @Description : ������
	 * @param : 
	 * @return : 
	 * @author ���¸�
	 * @since 2013.04.15
	 */
	public ShopNoticeDao() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @Method Name : commit
	 * @Description :
	 * @param : 
	 * @return : 
	 * @author ���¸�
	 * @since 2013.04.15
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
	 * @since 2013.04.15
	 */
	public void rollback(){
		sqlMapper.rollback();
	}
	
	
	/**
	 * @Method Name : selectShopNoticeList
	 * @Description : ��������/�̺�Ʈ ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<ShopNotice>
	 * @author ���¸�
	 * @since 2013.04.15
	 */
	@SuppressWarnings("unchecked")
	public List<ShopNotice> selectShopNoticeList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectShopNoticeList", params);
	}
	
	
	/**
	 * @Method Name : selectShopNoticeListCnt
	 * @Description : ��������/�̺�Ʈ ��� �� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2013.04.15
	 */
	@SuppressWarnings("unchecked")
	public Integer selectShopNoticeListCnt(HashMap<String,Object> params){
		Integer cnt = 0;
		
		cnt = (Integer) sqlMapper.selectOne(preMapperName + "selectShopNoticeListCnt", params);
		return cnt;
	}
	
	/**
	 * @Method Name : selectAShopNotice
	 * @Description : ��������/�̺�Ʈ ��ȸ
	 * @return : List<ShopNotice>
	 * @author ���¸�
	 * @since 2013.04.15
	 */
	@SuppressWarnings("unchecked")
	public ShopNotice selectAShopNoticeInfo(HashMap<String,Object> params){
		return (ShopNotice) sqlMapper.selectOne(preMapperName + "selectAShopNoticeInfo", params);
	}
	
	
	/**
	 * @Method Name : insertShopNotice
	 * @Description : ��������/�̺�Ʈ ���
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2013.04.15
	 */
	@SuppressWarnings("unchecked")
	public Integer insertShopNotice(HashMap<String,Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.insert(preMapperName + "insertShopNotice", params));
		return result;
	}
	
	
	/**
	 * @Method Name : updateShopNotice
	 * @Description : ��������/�̺�Ʈ ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2013.04.15
	 */
	@SuppressWarnings("unchecked")
	public Integer updateShopNotice(HashMap<String,Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.update(preMapperName + "updateShopNotice", params));
		return result;
	}
	
	/**
	 * @Method Name : deleteShopNotice
	 * @Description : ��������/�̺�Ʈ ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2013.04.15
	 */
	@SuppressWarnings("unchecked")
	public Integer deleteShopNotice(HashMap<String, Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.delete(preMapperName + "deleteShopNotice", params));
		return result;
	}
	
	/**
	 * @Method Name : updateDelCategoryInfo
	 * @Description : ��������/�̺�Ʈ �������� ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2013.04.15
	 */
	@SuppressWarnings("unchecked")
	public Integer updateDelCategoryInfo(HashMap<String, Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.update(preMapperName + "updateDelCategoryInfo", params));
		return result;
	}
	
	/**
	 * @Method Name : selectLastNoticeId
	 * @Description : ������ ��ϵ� ��������/�̺�Ʈ ID ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2013.04.15
	 */
	@SuppressWarnings("unchecked")
	public String selectLastNoticeId(HashMap<String, Object> params){
		String result = "";
		result = sqlMapper.selectOne(preMapperName + "selectLastNoticeId", params).toString();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public Integer selectShowNoticeListCnt(HashMap<String, Object> params){
		Integer result = (Integer) sqlMapper.selectOne(preMapperName + "selectShowNoticeListCnt", params);
		return result;
	}
	

	@SuppressWarnings("unchecked")
	public Integer updateDelNoticeInfo(HashMap<String, Object> params){
		Integer result = (Integer) sqlMapper.update(preMapperName + "updateDelNoticeInfo", params);
		return result;
	}
}
