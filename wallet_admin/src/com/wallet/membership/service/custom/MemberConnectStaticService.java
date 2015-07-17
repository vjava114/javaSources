package com.wallet.membership.service.custom;


import java.util.HashMap;
import java.util.List;

import com.wallet.membership.dao.custom.MemberConnectStaticDao;
import com.wallet.membership.model.custom.MemberConnectStatic;

public class MemberConnectStaticService {
	private final MemberConnectStaticDao sDao;

	/**
	 * @Method Name : MemberEventService
	 * @Description : 생성자
	 * @param : 
	 * @return : 
	 * @author 이정인
	 * @since 2012.09.17
	 */
	public MemberConnectStaticService() {
		sDao = new MemberConnectStaticDao();
	}
	

	/**
	 * @Method Name : 
	 * @Description : 접속통계(일별) 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author 이정인
	 * @since 2012.09.24
	 */
	public List<MemberConnectStatic> selectMemberConnectOSStaticDayList(HashMap<String ,Object> params) {
		List<MemberConnectStatic> result = null;
		
		result = sDao.selectMemberConnectOSStaticDayList(params); //-- 이벤트/공지사항 목록을 조회함.

		return result;
	}

	/**
	 * @Method Name : 
	 * @Description : 접속통계 연령/성별(일별) 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author 이정인
	 * @since 2012.09.24
	 */
	public List<MemberConnectStatic> selectMemberConnectAgeCancelStaticDayList(HashMap<String ,Object> params) {
		List<MemberConnectStatic> result = null;
		
		result = sDao.selectMemberConnectAgeCancelStaticDayList(params); //-- 이벤트/공지사항 목록을 조회함.

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
	public List<HashMap<String, Object>> selectMemberConnectNomalStaticDayList(HashMap<String, Object> params){
		List<HashMap<String, Object>> result = null;
		
		result = sDao.selectMemberConnectNomalStaticDayList(params);
		
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
	public List<HashMap<String, Object>> selectMemberConnectNomalStaticMonthList(HashMap<String, Object> params){
		List<HashMap<String, Object>> result = null;
		
		result = sDao.selectMemberConnectNomalStaticMonthList(params);
		
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
	public List<HashMap<String, Object>> selectMemberConnectOSStaticMonthList(HashMap<String, Object> params){
		List<HashMap<String, Object>> result = null;
		
		result = sDao.selectMemberConnectOSStaticMonthList(params);
		
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
	public List<HashMap<String, Object>> selectMemberConnectAgeStaticMonthList(HashMap<String, Object> params){
		List<HashMap<String, Object>> result = null;
		
		result = sDao.selectMemberConnectAgeStaticMonthList(params);
		
		return result;
				
	}

}
