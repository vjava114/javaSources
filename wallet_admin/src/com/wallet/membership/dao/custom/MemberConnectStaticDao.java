package com.wallet.membership.dao.custom;


import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.membership.mapper.custom.MemberConnectStaticMapper;
import com.wallet.membership.mapper.custom.MemberIssueStaticMapper;
import com.wallet.membership.mapper.custom.MemberJoinStaticMapper;
import com.wallet.membership.model.custom.MemberConnectStatic;
import com.wallet.membership.model.custom.MemberIssueStatic;
import com.wallet.membership.model.custom.MemberJoinStatic;

public class MemberConnectStaticDao extends MybatisCilent implements MemberConnectStaticMapper {
	
	private String preMapperName = "com.wallet.membership.mapper.custom.MemberConnectStaticMapper.";

	
	/**
	 * @Method Name : MemberIssueStaticDao
	 * @Description : 생성자
	 * @param : 
	 * @return : 
	 * @author 이정인
	 * @since 2012.09.24
	 */
	public MemberConnectStaticDao() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @Method Name : selectMemberIssueStaticDayList
	 * @Description : 가입통계(일별) 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author 이정인
	 * @since 2012.09.24
	 */
	public List<MemberConnectStatic> selectMemberConnectOSStaticDayList(HashMap<String, Object> params) {
		// TODO Auto-generated method stub
		return sqlMapper.selectList(preMapperName + "selectMemberConnectOSStaticDayList", params);
	}


	/**
	 * @Method Name : selectMemberIssueStaticDayList
	 * @Description : 가입통계(일별) 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author 이정인
	 * @since 2012.09.24
	 */
	public List<MemberConnectStatic> selectMemberConnectAgeCancelStaticDayList(HashMap<String, Object> params) {
		// TODO Auto-generated method stub
		return sqlMapper.selectList(preMapperName + "selectMemberConnectAgeCancelStaticDayList", params);
	}

	public List<HashMap<String, Object>> selectMemberConnectNomalStaticDayList(HashMap<String, Object> params) {
		// TODO Auto-generated method stub
		return sqlMapper.selectList(preMapperName + "selectMemberConnectNomalStaticDayList", params);
	}

	public List<HashMap<String, Object>> selectMemberConnectNomalStaticMonthList(HashMap<String, Object> params) {
		// TODO Auto-generated method stub
		return sqlMapper.selectList(preMapperName + "selectMemberConnectNomalStaticMonthList", params);
	}

	public List<HashMap<String, Object>> selectMemberConnectOSStaticMonthList(HashMap<String, Object> params) {
		// TODO Auto-generated method stub
		return sqlMapper.selectList(preMapperName + "selectMemberConnectOSStaticMonthList", params);
	}

	public List<HashMap<String, Object>> selectMemberConnectAgeStaticMonthList(HashMap<String, Object> params) {
		// TODO Auto-generated method stub
		return sqlMapper.selectList(preMapperName + "selectMemberConnectAgeStaticMonthList", params);
	}

}

