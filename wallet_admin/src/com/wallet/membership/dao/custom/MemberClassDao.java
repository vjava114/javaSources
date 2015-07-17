package com.wallet.membership.dao.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.membership.mapper.custom.MemberClassMapper;
import com.wallet.membership.model.custom.MemberClass;

public class MemberClassDao extends MybatisCilent implements MemberClassMapper {

	private String preMapperName = "com.wallet.membership.mapper.custom.MemberClassMapper.";

	
	/**
	 * @Method Name : commit
	 * @Description :
	 * @param : 
	 * @return : 
	 * @author ���¸�
	 * @since 2012.09.16
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
	 * @since 2012.09.16
	 */
	public void rollback(){
		sqlMapper.rollback();
	}
	
	/**
	 * @Method Name : MemberClassDao
	 * @Description : ������
	 * @param : 
	 * @return : 
	 * @author ���¸�
	 * @since 2012.09.16
	 */
	public MemberClassDao() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @Method Name : selectMemberClassList
	 * @Description : ����� ��� ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberClass>
	 * @author ���¸�
	 * @since 2012.09.16
	 */
	@SuppressWarnings("unchecked")
	public List<MemberClass> selectMemberClassList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectMemberClassList", params);
	}
	
	
	/**
	 * @Method Name : selectMemberClassListCnt
	 * @Description : ����� ��� ��� �� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.16
	 */
	@SuppressWarnings("unchecked")
	public Integer selectMemberClassListCnt(HashMap<String,Object> params){
		Integer cnt = 0;
		
		cnt = (Integer) sqlMapper.selectOne(preMapperName + "selectMemberClassListCnt", params);
		return cnt;
	}

	
	/**
	 * @Method Name : selectAMemberClass
	 * @Description : ����� ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberClass>
	 * @author ���¸�
	 * @since 2012.09.16
	 */
	@SuppressWarnings("unchecked")
	public MemberClass selectAMemberClass(HashMap<String,Object> params){
		return (MemberClass) sqlMapper.selectOne(preMapperName + "selectMemberClassInfo", params);
	}
	
	
	/**
	 * @Method Name : insertMemberClass
	 * @Description : ����� ��� ���
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.16
	 */
	@SuppressWarnings("unchecked")
	public Integer insertMemberClass(HashMap<String,Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.insert(preMapperName + "insertMemberClass", params));
		return result;
	}
	
	
	/**
	 * @Method Name : updateMemberClass
	 * @Description : ����� ��� ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.16
	 */
	@SuppressWarnings("unchecked")
	public Integer updateMemberClass(HashMap<String,Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.update(preMapperName + "updateMemberClass", params));
		return result;
	}
	
	/**
	 * @Method Name : updateMemberClass
	 * @Description : ����� ��� ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.16
	 */
	@SuppressWarnings("unchecked")
	public Integer deleteMemberClass(HashMap<String, Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.delete(preMapperName + "deleteMemberClass", params));
		return result;
	}
}
