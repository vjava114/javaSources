package com.wallet.membership.service.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.dao.custom.MemberManualDao;
import com.wallet.membership.model.custom.MemberManual;

public class MemberManualService {
	private final MemberManualDao sDao;

	/**
	 * @Method Name : MemberManualService
	 * @Description : ������
	 * @param : 
	 * @return : 
	 * @author ���¸�
	 * @since 2012.09.19
	 */
	public MemberManualService() {
		sDao = new MemberManualDao();
	}
	
	/**
	 * @Method Name : commit
	 * @Description : 
	 * @param : 
	 * @return : void
	 * @author ���¸�
	 * @since 2012.09.19
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
	 * @Method Name : selectMemberManualList
	 * @Description : ������� ����� ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberManual>
	 * @author ���¸�
	 * @since 2012.09.19
	 */
	public List<MemberManual> selectMemberManualList(HashMap<String ,Object> params) {
		List<MemberManual> result = null;
		
		result = sDao.selectMemberManualList(params); //-- �̺�Ʈ/�������� ����� ��ȸ��.

		return result;
	}
	
	/**
	 * @Method Name : selectMemberManualListCnt
	 * @Description : ������� ����� ��� �� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ���¸�
	 * @since 2012.09.19
	 */
	public int selectMemberManualListCnt(HashMap<String, Object> params) {
		int result = 0;
		
		result = Integer.parseInt(sDao.selectMemberManualListCnt(params).toString()); //-- �̺�Ʈ/�������� ����� ��ȸ��.

		return result;
	}

	
	/**
	 * @Method Name : selectAMemberManual
	 * @Description : ������� ����� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberManual>
	 * @author ���¸�
	 * @since 2012.09.19
	 */
	@SuppressWarnings("unchecked")
	public MemberManual selectAMemberManual(HashMap<String,Object> params){
		MemberManual aMemberManual = sDao.selectAMemberManual(params);
		
		return aMemberManual;
	}
	
	/**
	 * @Method Name : insertMemberManual
	 * @Description : ������� ����� ���
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ���¸�
	 * @since 2012.09.19
	 */
	public int insertMemberManual(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.insertMemberManual(params);
		
		return result;
	}
	
	/**
	 * @Method Name : updateMemberManual
	 * @Description : ������� ����� ����
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ���¸�
	 * @since 2012.09.19
	 */
	public int updateMemberManual(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.updateMemberManual(params);
		
		return result;
	}
	
	/**
	 * @Method Name : deleteMemberManual
	 * @Description : ������� ����� ����
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ���¸�
	 * @since 2012.09.19
	 */
	public int deleteMemberManual(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.deleteMemberManual(params);
		
		return result;
	}
	
	/**
	 * @Method Name : memberManualDupCheck
	 * @Description : ����� ID�� ��� ��, �ߺ����θ� Ȯ���Ѵ�.
	 * @param : String
	 * @return : int 0/1 (0:�ߺ��ƴ�, 1:�ߺ�)
	 * @author trkim
	 * @since 2012.09.19
	 */
	public int memberManualDupCheck(String selfMembId){
		int result = 0;
		
		result = Integer.parseInt(sDao.memberManualDupCheck(selfMembId).toString());
		
		return result;
	}
}
