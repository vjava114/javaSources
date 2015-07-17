package com.wallet.membership.mapper.custom;


import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.MemberIssueStatic;

public interface MemberIssueStaticMapper {
	/**
	 * @Method Name : selectMemberIssueStaticDayList
	 * @Description : �߱����(�Ϻ�) ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author ��ϼ�
	 * @since 2012.09.24
	 */
	List<MemberIssueStatic> selectMemberIssueStaticDayList(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : selectMemberIssueStaticMonthList
	 * @Description : �߱����(����) ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author ��ϼ�
	 * @since 2012.09.24
	 */
	List<HashMap<String, Object>> selectMemberIssueStaticMonthList(HashMap<String ,Object> params);
	
	
	
	/**
	 * @Method Name : selectMemberIssueCancelStaticDayList
	 * @Description : �߱�������(�Ϻ�) ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author ��ϼ�
	 * @since 2012.09.25
	 */
	List<MemberIssueStatic> selectMemberIssueCancelStaticDayList(HashMap<String ,Object> params);
	
	
	/**
	 * @Method Name : selectMemberIssueCancelStaticMonthList
	 * @Description : �߱�������(����) ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author ��ϼ�
	 * @since 2012.09.26
	 */
	List<HashMap<String, Object>> selectMemberIssueCancelStaticMonthList(HashMap<String ,Object> params);
}

