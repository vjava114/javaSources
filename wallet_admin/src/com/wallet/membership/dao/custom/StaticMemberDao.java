package com.wallet.membership.dao.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.membership.mapper.custom.StaticMemberMapper;
import com.wallet.membership.model.custom.StaticMember;

public class StaticMemberDao extends MybatisCilent implements StaticMemberMapper {

	private String preMapperName = "com.wallet.membership.mapper.custom.StaticMemberMapper.";

	
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
	 * @Method Name : StaticMemberDao
	 * @Description : ������
	 * @param : 
	 * @return : 
	 * @author ���¸�
	 * @since 2012.09.05
	 */
	public StaticMemberDao() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @Method Name : selectMemberList
	 * @Description : ������ ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<StaticMember>
	 * @author ���¸�
	 * @since 2012.09.05
	 */
	@SuppressWarnings("unchecked")
	public List<StaticMember> selectMemberList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectMemberList", params);
	}
}
