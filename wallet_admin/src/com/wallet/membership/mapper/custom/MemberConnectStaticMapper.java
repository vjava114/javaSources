package com.wallet.membership.mapper.custom;


import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.MemberConnectStatic;

public interface MemberConnectStaticMapper {
	/**
	 * @Method Name : selectMemberJoinStaticDay
	 * @Description : 가입통계(일별) 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author 이정인
	 * @since 2012.09.24
	 */
	List<MemberConnectStatic> selectMemberConnectOSStaticDayList(HashMap<String ,Object> params);
	
	
	/**
	 * @Method Name : selectMemberJoinStaticDay
	 * @Description : 가입통계 연령/성별(일별) 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author 이정인
	 * @since 2012.09.24
	 */
	List<MemberConnectStatic> selectMemberConnectAgeCancelStaticDayList(HashMap<String ,Object> params);
	
	List<HashMap<String, Object>> selectMemberConnectNomalStaticDayList(HashMap<String, Object> params);
	
	List<HashMap<String, Object>> selectMemberConnectNomalStaticMonthList(HashMap<String, Object> params);

	List<HashMap<String, Object>> selectMemberConnectOSStaticMonthList(HashMap<String, Object> params);

	List<HashMap<String, Object>> selectMemberConnectAgeStaticMonthList(HashMap<String, Object> params);
	
}

