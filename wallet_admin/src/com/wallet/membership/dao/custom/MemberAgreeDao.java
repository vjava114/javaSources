package com.wallet.membership.dao.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.membership.mapper.custom.MemberAgreeMapper;
import com.wallet.membership.model.custom.MemberAgree;

public class MemberAgreeDao extends MybatisCilent implements MemberAgreeMapper {

	private String preMapperName = "com.wallet.membership.mapper.custom.MemberAgreeMapper.";

	
	/**
	 * @Method Name : commit
	 * @Description :
	 * @param : 
	 * @return : 
	 * @author ���¸�
	 * @since 2012.09.05
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
	 * @Method Name : MemberAgreeDao
	 * @Description : ������
	 * @param : 
	 * @return : 
	 * @author ���¸�
	 * @since 2012.09.05
	 */
	public MemberAgreeDao() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @Method Name : selectMemberAgreeList
	 * @Description : �̿��� ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberAgree>
	 * @author ���¸�
	 * @since 2012.09.05
	 */
	@SuppressWarnings("unchecked")
	public List<MemberAgree> selectMemberAgreeList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectMemberAgreeList", params);
	}
	
	
	/**
	 * @Method Name : insertMemberAgree
	 * @Description : �̿��� ���
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.17
	 */
	@SuppressWarnings("unchecked")
	public Integer insertMemberAgree(HashMap<String,Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.insert(preMapperName + "insertMemberAgree", params));
		return result;
	}
	
	
	/**
	 * @Method Name : updateMemberAgree
	 * @Description : �̿��� ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.17
	 */
	@SuppressWarnings("unchecked")
	public Integer updateMemberAgree(HashMap<String,Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.update(preMapperName + "updateMemberAgree", params));
		return result;
	}
	
	/**
	 * @Method Name : updateMemberAgree
	 * @Description : �̿��� ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.17
	 */
	@SuppressWarnings("unchecked")
	public Integer deleteMemberAgree(HashMap<String, Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.delete(preMapperName + "deleteMemberAgree", params));
		return result;
	}

}
