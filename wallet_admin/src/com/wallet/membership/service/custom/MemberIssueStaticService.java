package com.wallet.membership.service.custom;


import java.util.HashMap;
import java.util.List;

import com.wallet.membership.dao.custom.MemberIssueStaticDao;
import com.wallet.membership.model.custom.MemberIssueStatic;

public class MemberIssueStaticService {
	private final MemberIssueStaticDao sDao;

	/**
	 * @Method Name : MemberIssueStaticService
	 * @Description : 생성자
	 * @param : 
	 * @return : 
	 * @author 김완섭
	 * @since 2012.09.24
	 */
	public MemberIssueStaticService() {
		sDao = new MemberIssueStaticDao();
	}
	

	/**
	 * @Method Name : selectMemberIssueStaticDayList
	 * @Description : 발급통계(일별) 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author 김완섭
	 * @since 2012.09.24
	 */
	public List<MemberIssueStatic> selectMemberIssueStaticDayList(HashMap<String ,Object> params) {
		List<MemberIssueStatic> result = null;
		
		result = sDao.selectMemberIssueStaticDayList(params);

		return result;
	}
	
	/**
	 * @Method Name : selectMemberIssueStaticMonthList
	 * @Description : 발급통계(월별) 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author 김완섭
	 * @since 2012.09.24
	 */
	public List<HashMap<String, Object>> selectMemberIssueStaticMonthList(HashMap<String ,Object> params) {
		List<HashMap<String, Object>> result = null;
		
		result = sDao.selectMemberIssueStaticMonthList(params); 

		return result;
	}
	
	/**
	 * @Method Name : selectMemberIssueCancelStaticDayList
	 * @Description : 발급취소통계(일별) 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author 김완섭
	 * @since 2012.09.24
	 */
	public List<MemberIssueStatic> selectMemberIssueCancelStaticDayList(HashMap<String ,Object> params) {
		List<MemberIssueStatic> result = null;
		
		result = sDao.selectMemberIssueCancelStaticDayList(params);

		return result;
	}
	
	/**
	 * @Method Name : selectMemberIssueCancelStaticMonthList
	 * @Description : 발급취소통계(월별) 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author 김완섭
	 * @since 2012.09.26
	 */
	public List<HashMap<String, Object>> selectMemberIssueCancelStaticMonthList(HashMap<String ,Object> params) {
		List<HashMap<String, Object>> result = null;
		
		result = sDao.selectMemberIssueCancelStaticMonthList(params);

		return result;
	}
}
