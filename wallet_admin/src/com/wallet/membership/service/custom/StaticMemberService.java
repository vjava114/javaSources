package com.wallet.membership.service.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.dao.custom.StaticMemberDao;
import com.wallet.membership.model.custom.StaticMember;

public class StaticMemberService {

	private final StaticMemberDao sDao;

	/**
	 * @Method Name : StaticMemberService
	 * @Description : ������
	 * @param : 
	 * @return : 
	 * @author ���¸�
	 * @since 2012.09.27
	 */
	public StaticMemberService() {
		sDao = new StaticMemberDao();
	}
	
	/**
	 * @Method Name : commit
	 * @Description : 
	 * @param : 
	 * @return : void
	 * @author ���¸�
	 * @since 2012.09.27
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
	 * @Method Name : selectStaticMemberList
	 * @Description : ������ ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<StaticMember>
	 * @author ���¸�
	 * @since 2012.09.27
	 */
	public List<StaticMember> selectMemberList(HashMap<String ,Object> params) {
		List<StaticMember> result = null;
		
		result = sDao.selectMemberList(params); //-- �̺�Ʈ/�������� ����� ��ȸ��.

		return result;
	}

}
