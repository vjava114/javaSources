package com.wallet.membership.service.custom;


import java.util.HashMap;
import java.util.List;

import com.wallet.membership.dao.custom.MemberEventDao;
import com.wallet.membership.model.custom.MemberEvent;

public class MemberEventService {
	private final MemberEventDao sDao;

	/**
	 * @Method Name : MemberEventService
	 * @Description : ������
	 * @param : 
	 * @return : 
	 * @author ��ϼ�
	 * @since 2012.09.17
	 */
	public MemberEventService() {
		sDao = new MemberEventDao();
	}
	
	/**
	 * @Method Name : commit
	 * @Description : 
	 * @param : 
	 * @return : void
	 * @author ��ϼ�
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
	 * @author ��ϼ�
	 * @since 2012.09.17
	 */
	public void rollback(){
		sDao.rollback();
	}
	
	/**
	 * @Method Name : selectMemberEventList
	 * @Description : ��������/�̺�Ʈ ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberEvent>
	 * @author ��ϼ�
	 * @since 2012.09.17
	 */
	public List<MemberEvent> selectMemberEventList(HashMap<String ,Object> params) {
		List<MemberEvent> result = null;
		
		result = sDao.selectMemberEventList(params); //-- �̺�Ʈ/�������� ����� ��ȸ��.

		return result;
	}
	
	/**
	 * @Method Name : selectMemberEventListCnt
	 * @Description : ��������/�̺�Ʈ ��� �� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ��ϼ�
	 * @since 2012.09.17
	 */
	public int selectMemberEventListCnt(HashMap<String, Object> params) {
		int result = 0;
		
		result = Integer.parseInt(sDao.selectMemberEventListCnt(params).toString()); //-- �̺�Ʈ/�������� ����� ��ȸ��.

		return result;
	}

	
	/**
	 * @Method Name : selectAMemberEvent
	 * @Description : ��������/�̺�Ʈ ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberEvent>
	 * @author ��ϼ�
	 * @since 2012.09.17
	 */

	public MemberEvent selectAMemberEvent(HashMap<String,Object> params){
		MemberEvent aMemberEvent = sDao.selectAMemberEvent(params);
		
		return aMemberEvent;
	}
	
	/**
	 * @Method Name : insertMemberEvent
	 * @Description : ��������/�̺�Ʈ ���
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ��ϼ�
	 * @since 2012.09.17
	 */
	public int insertMemberEvent(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.insertMemberEvent(params);
		
		return result;
	}
	
	/**
	 * @Method Name : updateMemberEvent
	 * @Description : ��������/�̺�Ʈ ����
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ��ϼ�
	 * @since 2012.09.17
	 */
	public int updateMemberEvent(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.updateMemberEvent(params);
		
		return result;
	}
	
	/**
	 * @Method Name : deleteSettlement
	 * @Description : ������ ����
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ���¸�
	 * @since 2012.09.07
	 */
	public int deleteMemberEvent(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.deleteMemberEvent(params);
		
		return result;
	}
}
