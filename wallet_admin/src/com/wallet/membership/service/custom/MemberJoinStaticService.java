package com.wallet.membership.service.custom;


import java.util.HashMap;
import java.util.List;

import com.wallet.membership.dao.custom.MemberJoinStaticDao;
import com.wallet.membership.model.custom.MemberJoinStatic;

public class MemberJoinStaticService {
	private final MemberJoinStaticDao sDao;

	/**
	 * @Method Name : MemberEventService
	 * @Description : 생성자
	 * @param : 
	 * @return : 
	 * @author 이정인
	 * @since 2012.09.17
	 */
	public MemberJoinStaticService() {
		sDao = new MemberJoinStaticDao();
	}
	

	/**
	 * @Method Name : 
	 * @Description : 가입통계(일별) 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author 이정인
	 * @since 2012.09.24
	 */
	public List<MemberJoinStatic> selectMemberJoinStaticDayList(HashMap<String ,Object> params) {
		List<MemberJoinStatic> result = null;
		
		result = sDao.selectMemberJoinStaticDayList(params); //-- 이벤트/공지사항 목록을 조회함.

		return result;
	}

	/**
	 * @Method Name : 
	 * @Description : 가입통계 연령/성별(일별) 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author 이정인
	 * @since 2012.09.24
	 */
	public List<MemberJoinStatic> selectMemberJoinAgeStaticDayList(HashMap<String ,Object> params) {
		List<MemberJoinStatic> result = null;
		
		result = sDao.selectMemberJoinAgeStaticDayList(params); //-- 이벤트/공지사항 목록을 조회함.

		return result;
	}

	/**
	 * @Method Name : 
	 * @Description : 가입통계(월별) 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author 이정인
	 * @since 2012.09.24
	 */
	public List<HashMap<String, Object>> selectMemberJoinNomalStaticMonthList(HashMap<String, Object> params){
		List<HashMap<String, Object>> result = null;
		
		result = sDao.selectMemberJoinNomalStaticMonthList(params);
		
		return result;
				
	}

	/**
	 * @Method Name : 
	 * @Description : 가입통계(월별) OS 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author 이정인
	 * @since 2012.09.24
	 */
	public List<HashMap<String, Object>> selectMemberJoinOSStaticMonthList(HashMap<String, Object> params){
		List<HashMap<String, Object>> result = null;
		
		result = sDao.selectMemberJoinOSStaticMonthList(params);
		
		return result;
				
	}

	/**
	 * @Method Name : 
	 * @Description : 가입통계(월별) 연령/성별 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author 이정인
	 * @since 2012.09.24
	 */
	public List<HashMap<String, Object>> selectMemberJoinAgeStaticMonthList(HashMap<String, Object> params){
		List<HashMap<String, Object>> result = null;
		
		result = sDao.selectMemberJoinAgeStaticMonthList(params);
		
		return result;
				
	}

	/**
	 * @Method Name : 
	 * @Description : 가입통계(일별) 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author 이정인
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
