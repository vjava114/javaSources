package com.wallet.membership.mapper.custom;


import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.MemberJoinStatic;

public interface MemberJoinStaticMapper {
	/**
	 * @Method Name : selectMemberJoinStaticDay
	 * @Description : �������(�Ϻ�) ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author ������
	 * @since 2012.09.24
	 */
	List<MemberJoinStatic> selectMemberJoinStaticDayList(HashMap<String ,Object> params);
	
	
	/**
	 * @Method Name : selectMemberJoinStaticDay
	 * @Description : ������� ����/����(�Ϻ�) ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author ������
	 * @since 2012.09.24
	 */
	List<MemberJoinStatic> selectMemberJoinAgeStaticDayList(HashMap<String ,Object> params);
	
	
	List<HashMap<String, Object>> selectMemberJoinNomalStaticMonthList(HashMap<String, Object> params);
	
	List<HashMap<String, Object>> getMemberJoinMemberShips(HashMap<String, Object> params);
	
	List<HashMap<String, Object>> selectMemberJoinOSStaticMonthList(HashMap<String, Object> params);

	List<HashMap<String, Object>> selectMemberJoinAgeStaticMonthList(HashMap<String, Object> params);

	List<HashMap<String, Object>> selectMemberJoinNomalStaticDayList(HashMap<String, Object> params);
}

