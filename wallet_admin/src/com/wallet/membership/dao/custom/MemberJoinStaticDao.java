package com.wallet.membership.dao.custom;


import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.membership.mapper.custom.MemberJoinStaticMapper;
import com.wallet.membership.model.custom.MemberJoinStatic;

public class MemberJoinStaticDao extends MybatisCilent implements MemberJoinStaticMapper {
	
	private String preMapperName = "com.wallet.membership.mapper.custom.MemberJoinStaticMapper.";

	
	/**
	 * @Method Name : MemberIssueStaticDao
	 * @Description : ������
	 * @param : 
	 * @return : 
	 * @author ������
	 * @since 2012.09.24
	 */
	public MemberJoinStaticDao() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @Method Name : selectMemberIssueStaticDayList
	 * @Description : �������(�Ϻ�) ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author ������
	 * @since 2012.09.24
	 */
	public List<MemberJoinStatic> selectMemberJoinStaticDayList(HashMap<String, Object> params) {
		// TODO Auto-generated method stub
		return sqlMapper.selectList(preMapperName + "selectMemberJoinStaticDayList", params);
	}


	/**
	 * @Method Name : selectMemberIssueStaticDayList
	 * @Description : �������(�Ϻ�) ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author ������
	 * @since 2012.09.24
	 */
	public List<MemberJoinStatic> selectMemberJoinAgeStaticDayList(HashMap<String, Object> params) {
		// TODO Auto-generated method stub
		return sqlMapper.selectList(preMapperName + "selectMemberJoinAgeStaticDayList", params);
	}

	/**
	 * @Method Name : selectMemberIssueStaticDayList
	 * @Description : �������(����) ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author ������
	 * @since 2012.09.24
	 */
	public List<HashMap<String, Object>> selectMemberJoinNomalStaticMonthList(HashMap<String,Object> params){
		// TODO Auto-generated method stub
		return sqlMapper.selectList(preMapperName + "selectMemberJoinNomalStaticMonthList", params);
	}

	public List<HashMap<String, Object>> selectMemberJoinOSStaticMonthList(HashMap<String, Object> params) {
		// TODO Auto-generated method stub
		return sqlMapper.selectList(preMapperName + "selectMemberJoinOSStaticMonthList", params);
	}

	public List<HashMap<String, Object>> selectMemberJoinAgeStaticMonthList(HashMap<String, Object> params) {
		// TODO Auto-generated method stub
		return sqlMapper.selectList(preMapperName + "selectMemberJoinAgeStaticMonthList", params);
	}

	public List<HashMap<String, Object>> selectMemberJoinNomalStaticDayList(HashMap<String, Object> params) {
		// TODO Auto-generated method stub
		return sqlMapper.selectList(preMapperName + "selectMemberJoinNomalStaticDayList", params);
	}

		public List<HashMap<String, Object>> getMemberJoinMemberShips(HashMap<String, Object> params) {
		// TODO Auto-generated method stub
		return sqlMapper.selectList(preMapperName + "getMemberJoinMemberShips", params);
	}

}

