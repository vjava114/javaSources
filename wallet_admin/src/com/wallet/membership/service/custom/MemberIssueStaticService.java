package com.wallet.membership.service.custom;


import java.util.HashMap;
import java.util.List;

import com.wallet.membership.dao.custom.MemberIssueStaticDao;
import com.wallet.membership.model.custom.MemberIssueStatic;

public class MemberIssueStaticService {
	private final MemberIssueStaticDao sDao;

	/**
	 * @Method Name : MemberIssueStaticService
	 * @Description : ������
	 * @param : 
	 * @return : 
	 * @author ��ϼ�
	 * @since 2012.09.24
	 */
	public MemberIssueStaticService() {
		sDao = new MemberIssueStaticDao();
	}
	

	/**
	 * @Method Name : selectMemberIssueStaticDayList
	 * @Description : �߱����(�Ϻ�) ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author ��ϼ�
	 * @since 2012.09.24
	 */
	public List<MemberIssueStatic> selectMemberIssueStaticDayList(HashMap<String ,Object> params) {
		List<MemberIssueStatic> result = null;
		
		result = sDao.selectMemberIssueStaticDayList(params);

		return result;
	}
	
	/**
	 * @Method Name : selectMemberIssueStaticMonthList
	 * @Description : �߱����(����) ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author ��ϼ�
	 * @since 2012.09.24
	 */
	public List<HashMap<String, Object>> selectMemberIssueStaticMonthList(HashMap<String ,Object> params) {
		List<HashMap<String, Object>> result = null;
		
		result = sDao.selectMemberIssueStaticMonthList(params); 

		return result;
	}
	
	/**
	 * @Method Name : selectMemberIssueCancelStaticDayList
	 * @Description : �߱�������(�Ϻ�) ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author ��ϼ�
	 * @since 2012.09.24
	 */
	public List<MemberIssueStatic> selectMemberIssueCancelStaticDayList(HashMap<String ,Object> params) {
		List<MemberIssueStatic> result = null;
		
		result = sDao.selectMemberIssueCancelStaticDayList(params);

		return result;
	}
	
	/**
	 * @Method Name : selectMemberIssueCancelStaticMonthList
	 * @Description : �߱�������(����) ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author ��ϼ�
	 * @since 2012.09.26
	 */
	public List<HashMap<String, Object>> selectMemberIssueCancelStaticMonthList(HashMap<String ,Object> params) {
		List<HashMap<String, Object>> result = null;
		
		result = sDao.selectMemberIssueCancelStaticMonthList(params);

		return result;
	}
}
