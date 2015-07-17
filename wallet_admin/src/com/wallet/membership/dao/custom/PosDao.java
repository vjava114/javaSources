package com.wallet.membership.dao.custom;


import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.membership.mapper.custom.PosMapper;
import com.wallet.membership.model.custom.Pos;

public class PosDao extends MybatisCilent implements PosMapper {

	private String preMapperName = "com.wallet.membership.mapper.custom.PosMapper.";

	
	/**
	 * @Method Name : commit
	 * @Description :
	 * @param : 
	 * @return : 
	 * @author ��ϼ�
	 * @since 2012.09.13
	 */
	public void commit() {
		sqlMapper.commit();
	}
	
	/**
	 * @Method Name : rollback
	 * @Description :
	 * @param : 
	 * @return : 
	 * @author ��ϼ�
	 * @since 2012.09.14
	 */
	public void rollback() {
		sqlMapper.rollback();
	}
	
	
	/**
	 * @Method Name : PosDao
	 * @Description : ������
	 * @param : 
	 * @return : 
	 * @author ��ϼ�
	 * @since 2012.09.13
	 */
	public PosDao() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @Method Name : selectPosList
	 * @Description : POS ��� ��ȸ
	 * @param : HashMap<String, Object>
	 * @return : 
	 * @author ��ϼ�
	 * @since 2012.09.13
	 */
	@SuppressWarnings("unchecked")
	public List<Pos> selectPosList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectPosList", params);
	}
	
	
	/**
	 * @Method Name : selectPosListCnt
	 * @Description : POS ��� �� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ��ϼ�
	 * @since 2012.09.13
	 */
	@SuppressWarnings("unchecked")
	public Integer selectPosListCnt(HashMap<String,Object> params){
		Integer cnt = 0;
		
		cnt = (Integer) sqlMapper.selectOne(preMapperName + "selectPosListCnt", params);
		return cnt;
	}

	/**
	 * @Method Name : selectAPos
	 * @Description : POS �� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<Pos>
	 * @author ��ϼ�
	 * @since 2012.09.13
	 */
	@SuppressWarnings("unchecked")
	public Pos selectAPos(HashMap<String,Object> params){
		return (Pos) sqlMapper.selectOne(preMapperName + "selectPosInfo", params);
	}
	
	/**
	 * @Method Name : insertPos
	 * @Description : POS ���
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ��ϼ�
	 * @since 2012.09.13
	 */
	@SuppressWarnings("unchecked")
	public Integer insertPos(HashMap<String,Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.insert(preMapperName + "insertPos", params));
		return result;
	}
	
	
	/**
	 * @Method Name : updatePos
	 * @Description : POS ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ��ϼ�
	 * @since 2012.09.13
	 */
	@SuppressWarnings("unchecked")
	public Integer updatePos(HashMap<String,Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.update(preMapperName + "updatePos", params));
		return result;
	}
	
	/**
	 * @Method Name : deletePos
	 * @Description : POS ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ��ϼ�
	 * @since 2012.09.13
	 */
	@SuppressWarnings("unchecked")
	public Integer deletePos(HashMap<String, Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.delete(preMapperName + "deletePos", params));
		return result;
	}

}
