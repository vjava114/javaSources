package com.wallet.membership.mapper.custom;


import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.MemberConnectStatic;

public interface MemberConnectStaticMapper {
	/**
	 * @Method Name : selectMemberJoinStaticDay
	 * @Description : �������(�Ϻ�) ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author ������
	 * @since 2012.09.24
	 */
	List<MemberConnectStatic> selectMemberConnectOSStaticDayList(HashMap<String ,Object> params);
	
	
	/**
	 * @Method Name : selectMemberJoinStaticDay
	 * @Description : ������� ����/����(�Ϻ�) ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author ������
	 * @since 2012.09.24
	 */
	List<MemberConnectStatic> selectMemberConnectAgeCancelStaticDayList(HashMap<String ,Object> params);
	
	List<HashMap<String, Object>> selectMemberConnectNomalStaticDayList(HashMap<String, Object> params);
	
	List<HashMap<String, Object>> selectMemberConnectNomalStaticMonthList(HashMap<String, Object> params);

	List<HashMap<String, Object>> selectMemberConnectOSStaticMonthList(HashMap<String, Object> params);

	List<HashMap<String, Object>> selectMemberConnectAgeStaticMonthList(HashMap<String, Object> params);
	
}

