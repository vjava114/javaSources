package com.wallet.membership.service.custom;


import java.util.HashMap;
import java.util.List;

import com.wallet.membership.dao.custom.MemberConnectStaticDao;
import com.wallet.membership.model.custom.MemberConnectStatic;

public class MemberConnectStaticService {
	private final MemberConnectStaticDao sDao;

	/**
	 * @Method Name : MemberEventService
	 * @Description : ������
	 * @param : 
	 * @return : 
	 * @author ������
	 * @since 2012.09.17
	 */
	public MemberConnectStaticService() {
		sDao = new MemberConnectStaticDao();
	}
	

	/**
	 * @Method Name : 
	 * @Description : �������(�Ϻ�) ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author ������
	 * @since 2012.09.24
	 */
	public List<MemberConnectStatic> selectMemberConnectOSStaticDayList(HashMap<String ,Object> params) {
		List<MemberConnectStatic> result = null;
		
		result = sDao.selectMemberConnectOSStaticDayList(params); //-- �̺�Ʈ/�������� ����� ��ȸ��.

		return result;
	}

	/**
	 * @Method Name : 
	 * @Description : ������� ����/����(�Ϻ�) ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author ������
	 * @since 2012.09.24
	 */
	public List<MemberConnectStatic> selectMemberConnectAgeCancelStaticDayList(HashMap<String ,Object> params) {
		List<MemberConnectStatic> result = null;
		
		result = sDao.selectMemberConnectAgeCancelStaticDayList(params); //-- �̺�Ʈ/�������� ����� ��ȸ��.

		return result;
	}
	

	/**
	 * @Method Name : 
	 * @Description : �������(����) ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author ������
	 * @since 2012.09.24
	 */
	public List<HashMap<String, Object>> selectMemberConnectNomalStaticDayList(HashMap<String, Object> params){
		List<HashMap<String, Object>> result = null;
		
		result = sDao.selectMemberConnectNomalStaticDayList(params);
		
		return result;
				
	}

	/**
	 * @Method Name : 
	 * @Description : �������(����) OS ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author ������
	 * @since 2012.09.24
	 */
	public List<HashMap<String, Object>> selectMemberConnectNomalStaticMonthList(HashMap<String, Object> params){
		List<HashMap<String, Object>> result = null;
		
		result = sDao.selectMemberConnectNomalStaticMonthList(params);
		
		return result;
				
	}

	/**
	 * @Method Name : 
	 * @Description : �������(����) ����/���� ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author ������
	 * @since 2012.09.24
	 */
	public List<HashMap<String, Object>> selectMemberConnectOSStaticMonthList(HashMap<String, Object> params){
		List<HashMap<String, Object>> result = null;
		
		result = sDao.selectMemberConnectOSStaticMonthList(params);
		
		return result;
				
	}

	/**
	 * @Method Name : 
	 * @Description : �������(�Ϻ�) ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author ������
	 * @since 2012.09.24
	 */
	public List<HashMap<String, Object>> selectMemberConnectAgeStaticMonthList(HashMap<String, Object> params){
		List<HashMap<String, Object>> result = null;
		
		result = sDao.selectMemberConnectAgeStaticMonthList(params);
		
		return result;
				
	}

}
