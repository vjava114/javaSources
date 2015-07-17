package com.wallet.membership.dao.custom;


import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.membership.mapper.custom.MemberEventMapper;
import com.wallet.membership.model.custom.MemberEvent;

public class MemberEventDao extends MybatisCilent implements MemberEventMapper {
	
	private String preMapperName = "com.wallet.membership.mapper.custom.MemberEventMapper.";

	
	/**
	 * @Method Name : commit
	 * @Description :
	 * @param : 
	 * @return : 
	 * @author 김완섭
	 * @since 2012.09.17
	 */
	public void commit() {
		sqlMapper.commit();
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
		sqlMapper.rollback();
	}
	
	/**
	 * @Method Name : MemberEventDao
	 * @Description : 생성자
	 * @param : 
	 * @return : 
	 * @author 김완섭
	 * @since 2012.09.17
	 */
	public MemberEventDao() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @Method Name : selectMemberEventList
	 * @Description : 공지사항/이벤트 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberEvent>
	 * @author 김완섭
	 * @since 2012.09.17
	 */
	@SuppressWarnings("unchecked")
	public List<MemberEvent> selectMemberEventList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectMemberEventList", params);
	}
	
	
	/**
	 * @Method Name : selectMemberEventListCnt
	 * @Description : 공지사항/이벤트 목록 수 조회
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김완섭
	 * @since 2012.09.17
	 */
	@SuppressWarnings("unchecked")
	public Integer selectMemberEventListCnt(HashMap<String,Object> params){
		Integer cnt = 0;
		
		cnt = (Integer) sqlMapper.selectOne(preMapperName + "selectMemberEventListCnt", params);
		return cnt;
	}

	
	/**
	 * @Method Name : selectAMemberEvent
	 * @Description : 공지사항/이벤트 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberEvent>
	 * @author 김완섭
	 * @since 2012.09.17
	 */
	@SuppressWarnings("unchecked")
	public MemberEvent selectAMemberEvent(HashMap<String,Object> params){
		return (MemberEvent) sqlMapper.selectOne(preMapperName + "selectMemberEventInfo", params);
	}
	
	
	/**
	 * @Method Name : insertMemberEventt
	 * @Description : 공지사항/이벤트 등록
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김완섭
	 * @since 2012.09.17
	 */
	@SuppressWarnings("unchecked")
	public Integer insertMemberEvent(HashMap<String,Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.insert(preMapperName + "insertMemberEvent", params));
		return result;
	}
	
	
	/**
	 * @Method Name : updateMemberEvent
	 * @Description : 공지사항/이벤트 수정
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김완섭
	 * @since 2012.09.17
	 */
	@SuppressWarnings("unchecked")
	public Integer updateMemberEvent(HashMap<String,Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.update(preMapperName + "updateMemberEvent", params));
		return result;
	}
	
	/**
	 * @Method Name : updateMemberEvent
	 * @Description : 공지사항/이벤트 삭제
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김완섭
	 * @since 2012.09.17
	 */
	@SuppressWarnings("unchecked")
	public Integer deleteMemberEvent(HashMap<String, Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.delete(preMapperName + "deleteMemberEvent", params));
		return result;
	}

}

