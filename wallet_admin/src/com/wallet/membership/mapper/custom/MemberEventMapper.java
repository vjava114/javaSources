package com.wallet.membership.mapper.custom;


import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.MemberEvent;

public interface MemberEventMapper {
	/**
	 * @Method Name : selectMemberEvent
	 * @Description : 공지사항/이벤트 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberEvent>
	 * @author 김완섭
	 * @since 2012.09.17
	 */
	List<MemberEvent> selectMemberEventList(HashMap<String ,Object> params);
	
	
	/**
	 * @Method Name : selectMemberEventListCnt
	 * @Description : 공지사항/이벤트 목록 수 조회
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김완섭
	 * @since 2012.09.17
	 */
	Integer selectMemberEventListCnt(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : insertMemberEvent
	 * @Description : 공지사항/이벤트 등록
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김완섭
	 * @since 2012.09.17
	 */
	Integer insertMemberEvent(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : updateMemberEvent
	 * @Description : 공지사항/이벤트 수정
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김완섭
	 * @since 2012.09.17
	 */
	Integer updateMemberEvent(HashMap<String ,Object> params);
}

