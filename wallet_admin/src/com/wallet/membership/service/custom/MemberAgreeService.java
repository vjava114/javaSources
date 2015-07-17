package com.wallet.membership.service.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.dao.custom.MemberAgreeDao;
import com.wallet.membership.model.custom.MemberAgree;

public class MemberAgreeService {
	private final MemberAgreeDao sDao;

	/**
	 * @Method Name : MemberAgreeService
	 * @Description : ������
	 * @param : 
	 * @return : 
	 * @author ���¸�
	 * @since 2012.09.17
	 */
	public MemberAgreeService() {
		sDao = new MemberAgreeDao();
	}
	
	/**
	 * @Method Name : commit
	 * @Description : 
	 * @param : 
	 * @return : void
	 * @author ���¸�
	 * @since 2012.09.17
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
	 * @Method Name : selectMemberAgreeList
	 * @Description : �̿��� ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberAgree>
	 * @author ���¸�
	 * @since 2012.09.17
	 */
	public List<MemberAgree> selectMemberAgreeList(HashMap<String ,Object> params) {
		return sDao.selectMemberAgreeList(params); //-- �̺�Ʈ/�������� ����� ��ȸ��.
	}
	
	/**
	 * @Method Name : insertMemberAgree
	 * @Description : �̿��� ���
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ���¸�
	 * @since 2012.09.17
	 */
	public int insertMemberAgree(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.insertMemberAgree(params);
		
		return result;
	}
	
	/**
	 * @Method Name : updateMemberAgree
	 * @Description : �̿��� ����
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ���¸�
	 * @since 2012.09.17
	 */
	public int updateMemberAgree(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.updateMemberAgree(params);
		
		return result;
	}
	
	/**
	 * @Method Name : deleteMemberAgree
	 * @Description : �̿��� ����
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ���¸�
	 * @since 2012.09.17
	 */
	public int deleteMemberAgree(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.deleteMemberAgree(params);
		
		return result;
	}

}
