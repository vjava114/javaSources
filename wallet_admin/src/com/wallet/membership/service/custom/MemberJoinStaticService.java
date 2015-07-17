package com.wallet.membership.service.custom;


import java.util.HashMap;
import java.util.List;

import com.wallet.membership.dao.custom.MemberJoinStaticDao;
import com.wallet.membership.model.custom.MemberJoinStatic;

public class MemberJoinStaticService {
	private final MemberJoinStaticDao sDao;

	/**
	 * @Method Name : MemberEventService
	 * @Description : ������
	 * @param : 
	 * @return : 
	 * @author ������
	 * @since 2012.09.17
	 */
	public MemberJoinStaticService() {
		sDao = new MemberJoinStaticDao();
	}
	

	/**
	 * @Method Name : 
	 * @Description : �������(�Ϻ�) ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author ������
	 * @since 2012.09.24
	 */
	public List<MemberJoinStatic> selectMemberJoinStaticDayList(HashMap<String ,Object> params) {
		List<MemberJoinStatic> result = null;
		
		result = sDao.selectMemberJoinStaticDayList(params); //-- �̺�Ʈ/�������� ����� ��ȸ��.

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
	public List<MemberJoinStatic> selectMemberJoinAgeStaticDayList(HashMap<String ,Object> params) {
		List<MemberJoinStatic> result = null;
		
		result = sDao.selectMemberJoinAgeStaticDayList(params); //-- �̺�Ʈ/�������� ����� ��ȸ��.

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
	public List<HashMap<String, Object>> selectMemberJoinNomalStaticMonthList(HashMap<String, Object> params){
		List<HashMap<String, Object>> result = null;
		
		result = sDao.selectMemberJoinNomalStaticMonthList(params);
		
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
	public List<HashMap<String, Object>> selectMemberJoinOSStaticMonthList(HashMap<String, Object> params){
		List<HashMap<String, Object>> result = null;
		
		result = sDao.selectMemberJoinOSStaticMonthList(params);
		
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
	public List<HashMap<String, Object>> selectMemberJoinAgeStaticMonthList(HashMap<String, Object> params){
		List<HashMap<String, Object>> result = null;
		
		result = sDao.selectMemberJoinAgeStaticMonthList(params);
		
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
	public List<HashMap<String, Object>> selectMemberJoinNomalStaticDayList(HashMap<String, Object> params){
		List<HashMap<String, Object>> result = null;
		
		result = sDao.selectMemberJoinNomalStaticDayList(params);
		
		return result;
				
	}
	
	public List<HashMap<String, Object>> getMemberJoinMemberShips(HashMap<String, Object> params){
		List<HashMap<String, Object>> result = null;
		
		result = sDao.getMemberJoinMemberShips(params);
		
		return result;
	}

}
