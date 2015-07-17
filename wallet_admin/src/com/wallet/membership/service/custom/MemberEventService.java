package com.wallet.membership.service.custom;


import java.util.HashMap;
import java.util.List;

import com.wallet.membership.dao.custom.MemberEventDao;
import com.wallet.membership.model.custom.MemberEvent;

public class MemberEventService {
	private final MemberEventDao sDao;

	/**
	 * @Method Name : MemberEventService
	 * @Description : 생성자
	 * @param : 
	 * @return : 
	 * @author 김완섭
	 * @since 2012.09.17
	 */
	public MemberEventService() {
		sDao = new MemberEventDao();
	}
	
	/**
	 * @Method Name : commit
	 * @Description : 
	 * @param : 
	 * @return : void
	 * @author 김완섭
	 * @since 2012.09.17
	 */
	public void commit(){
		sDao.commit();
	}
	
	/**
	 * @Method Name : rollback
	 * @Description :
	 * @param : 
	 * @return : 
	 * @author 김완섭
	 * @since 2012.09.17
	 */
	public void rollback(){
		sDao.rollback();
	}
	
	/**
	 * @Method Name : selectMemberEventList
	 * @Description : 공지사항/이벤트 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberEvent>
	 * @author 김완섭
	 * @since 2012.09.17
	 */
	public List<MemberEvent> selectMemberEventList(HashMap<String ,Object> params) {
		List<MemberEvent> result = null;
		
		result = sDao.selectMemberEventList(params); //-- 이벤트/공지사항 목록을 조회함.

		return result;
	}
	
	/**
	 * @Method Name : selectMemberEventListCnt
	 * @Description : 공지사항/이벤트 목록 수 조회
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김완섭
	 * @since 2012.09.17
	 */
	public int selectMemberEventListCnt(HashMap<String, Object> params) {
		int result = 0;
		
		result = Integer.parseInt(sDao.selectMemberEventListCnt(params).toString()); //-- 이벤트/공지사항 목록을 조회함.

		return result;
	}

	
	/**
	 * @Method Name : selectAMemberEvent
	 * @Description : 공지사항/이벤트 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberEvent>
	 * @author 김완섭
	 * @since 2012.09.17
	 */

	public MemberEvent selectAMemberEvent(HashMap<String,Object> params){
		MemberEvent aMemberEvent = sDao.selectAMemberEvent(params);
		
		return aMemberEvent;
	}
	
	/**
	 * @Method Name : insertMemberEvent
	 * @Description : 공지사항/이벤트 등록
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김완섭
	 * @since 2012.09.17
	 */
	public int insertMemberEvent(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.insertMemberEvent(params);
		
		return result;
	}
	
	/**
	 * @Method Name : updateMemberEvent
	 * @Description : 공지사항/이벤트 수정
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김완섭
	 * @since 2012.09.17
	 */
	public int updateMemberEvent(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.updateMemberEvent(params);
		
		return result;
	}
	
	/**
	 * @Method Name : deleteSettlement
	 * @Description : 결제사 삭제
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2012.09.07
	 */
	public int deleteMemberEvent(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.deleteMemberEvent(params);
		
		return result;
	}
}
