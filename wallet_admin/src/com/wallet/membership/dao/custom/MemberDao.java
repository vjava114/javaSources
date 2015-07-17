package com.wallet.membership.dao.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.membership.mapper.custom.MemberMapper;
import com.wallet.membership.model.custom.Member;

public class MemberDao extends MybatisCilent implements MemberMapper {

	private String preMapperName = "com.wallet.membership.mapper.custom.MemberMapper.";

	
	/**
	 * @Method Name : commit
	 * @Description :
	 * @param : 
	 * @return : 
	 * @author 김태리
	 * @since 2012.09.10
	 */
	public void commit() {
		sqlMapper.commit();
	}
	
	/**
	 * @Method Name : rollback
	 * @Description :
	 * @param : 
	 * @return : 
	 * @author 김태리
	 * @since 2012.09.14
	 */
	public void rollback(){
		sqlMapper.rollback();
	}
	
	
	/**
	 * @Method Name : MemberDao
	 * @Description : 생성자
	 * @param : 
	 * @return : 
	 * @author 김태리
	 * @since 2012.09.10
	 */
	public MemberDao() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @Method Name : selectMemberList
	 * @Description : 멤버십(카드정보) 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<Member>
	 * @author 김태리
	 * @since 2012.09.10
	 */
	@SuppressWarnings("unchecked")
	public List<Member> selectMemberList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectMemberList", params);
	}
	
	
	/**
	 * @Method Name : selectMemberListCnt
	 * @Description : 멤버십(카드정보) 목록 수 조회
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2012.09.10
	 */
	@SuppressWarnings("unchecked")
	public Integer selectMemberListCnt(HashMap<String,Object> params){
		Integer cnt = 0;
		
		cnt = (Integer) sqlMapper.selectOne(preMapperName + "selectMemberListCnt", params);
		return cnt;
	}

	
	/**
	 * @Method Name : selectAMember
	 * @Description : 멤버십(카드정보) 조회
	 * @return : List<Member>
	 * @author 김태리
	 * @since 2012.09.10
	 */
	@SuppressWarnings("unchecked")
	public Member selectAMember(HashMap<String,Object> params){
		return (Member) sqlMapper.selectOne(preMapperName + "selectMemberInfo", params);
	}
	
	
	/**
	 * @Method Name : insertMember
	 * @Description : 멤버십(카드정보) 등록
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2012.09.10
	 */
	@SuppressWarnings("unchecked")
	public Integer insertMember(HashMap<String,Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.insert(preMapperName + "insertMember", params));
		return result;
	}
	
	
	/**
	 * @Method Name : updateMember
	 * @Description : 멤버십(카드정보) 수정
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2012.09.10
	 */
	@SuppressWarnings("unchecked")
	public Integer updateMember(HashMap<String,Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.update(preMapperName + "updateMember", params));
		return result;
	}
	
	/**
	 * @Method Name : updateMember
	 * @Description : 멤버십(카드정보) 삭제
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2012.09.10
	 */
	@SuppressWarnings("unchecked")
	public Integer deleteMember(HashMap<String, Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.delete(preMapperName + "deleteMember", params));
		return result;
	}
	
	/**
	 * @Method Name : memberDupCheck
	 * @Description : 멤버십 ID의 등록 전, 중복여부를 확인한다.
	 * @param : String
	 * @return : Integer 0/1 (0:중복아님, 1:중복)
	 * @author trkim
	 * @since 2012.09.11
	 */
	public Integer memberDupCheck(String membId){
		Integer result = 0;
		
		result = (Integer) sqlMapper.selectOne(preMapperName + "memberDupCheck", membId);
		
		return result;
	}

	/**
	 * 멤버십 전시 순서 
	 * 
	 * @param params
	 * @return
	 */
	public List<Member> selectMemberOrderList(HashMap<String, Object> params) {
		return sqlMapper.selectList(preMapperName + "selectMemberOrderList", params);
	}

	/**
	 * 멤버십 전시순서 변경 (변경전값<변경후값)
	 * 
	 * @param params
	 * @return
	 */
	public Integer updateMemberOrderListGt(HashMap<String, Object> params) {
		return new Integer(sqlMapper.update(preMapperName + "updateMemberOrderListGt", params));
	}

	/**
	 * 멤버십 전시순서 변경 (변경전값>변경후값)
	 * 
	 * @param params
	 * @return
	 */
	public Integer updateMemberOrderListLt(HashMap<String, Object> params) {
		return new Integer(sqlMapper.update(preMapperName + "updateMemberOrderListLt", params));
	}

	/**
	 * 멤버십 전시순서 변경
	 * 
	 * @param params
	 * @return
	 */
	public Integer updateMemberOrder(HashMap<String, Object> params) {
		return new Integer(sqlMapper.update(preMapperName + "updateMemberOrder", params));
	}

	/**
	 * 전시여부 변경
	 * 
	 * @param params
	 * @return
	 */
	public Integer updateMemberShowYn(HashMap<String, Object> params) {
		return new Integer(sqlMapper.update(preMapperName + "updateMemberShowYn", params));
	}

	/**
	 * 추천순서 변경시 추천전시여부 초기화 
	 * 
	 * @param params
	 * @return
	 */
	public Integer updateMembRecommInitAll(HashMap<String, Object> params) {
		return new Integer(sqlMapper.update(preMapperName + "updateMembRecommInitAll", params));
	}

	public Integer updateMemberOrderListIncrease(HashMap<String, Object> params) {
		return new Integer(sqlMapper.update(preMapperName + "updateMemberOrderListIncrease", params));
	}
}
