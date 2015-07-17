package com.wallet.membership.dao.custom;


import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.membership.mapper.custom.MemberIssueStaticMapper;
import com.wallet.membership.model.custom.MemberIssueStatic;

public class MemberIssueStaticDao extends MybatisCilent implements MemberIssueStaticMapper {
	
	private String preMapperName = "com.wallet.membership.mapper.custom.MemberIssueStaticMapper.";

	
	/**
	 * @Method Name : MemberIssueStaticDao
	 * @Description : 생성자
	 * @param : 
	 * @return : 
	 * @author 김완섭
	 * @since 2012.09.24
	 */
	public MemberIssueStaticDao() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @Method Name : selectMemberIssueStaticDayList
	 * @Description : 발급통계(일별) 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author 김완섭
	 * @since 2012.09.24
	 */
	@SuppressWarnings("unchecked")
	public List<MemberIssueStatic> selectMemberIssueStaticDayList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectMemberIssueStaticDayList", params);
	}
	
	
	/**
	 * @Method Name : selectMemberIssueStaticMonthList
	 * @Description : 발급통계(월별) 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author 김완섭
	 * @since 2012.09.24
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> selectMemberIssueStaticMonthList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectMemberIssueStaticMonthList", params);
	}
	
	
	/**
	 * @Method Name : selectMemberIssueCancelStaticDayList
	 * @Description : 발급취소통계(일별) 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author 김완섭
	 * @since 2012.09.24
	 */
	@SuppressWarnings("unchecked")
	public List<MemberIssueStatic> selectMemberIssueCancelStaticDayList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectMemberIssueCancelStaticDayList", params);
	}
	
	/**
	 * @Method Name : selectMemberIssueCancelStaticMonthList
	 * @Description : 발급취소통계(월별) 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author 김완섭
	 * @since 2012.09.26
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> selectMemberIssueCancelStaticMonthList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectMemberIssueCancelStaticMonthList", params);
	}
}

