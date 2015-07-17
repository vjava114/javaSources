package com.wallet.membership.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.Member;

public interface MemberMapper {
	/**
	 * @Method Name : selectMemberList
	 * @Description : 멤버십(카드정보) 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<Member>
	 * @author 김태리
	 * @since 2012.09.10
	 */
	List<Member> selectMemberList(HashMap<String ,Object> params);
	
	
	/**
	 * @Method Name : selectMemberListCnt
	 * @Description : 멤버십(카드정보) 목록 수 조회
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2012.09.10
	 */
	Integer selectMemberListCnt(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : insertMember
	 * @Description : 멤버십(카드정보) 등록
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2012.09.10
	 */
	Integer insertMember(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : updateMember
	 * @Description : 멤버십(카드정보) 수정
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2012.09.10
	 */
	Integer updateMember(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : memberDupCheck
	 * @Description : 멤버십 ID의 등록 전, 중복여부를 확인한다.
	 * @param : String
	 * @return : Integer 0/1 (0:중복아님, 1:중복)
	 * @author trkim
	 * @since 2012.09.11
	 */
	Integer memberDupCheck(String membId);
	
	/**
	 * 멤버십 전시 순서
	 * 
	 * @param params
	 * @return
	 */
	List<Member> selectMemberOrderList(HashMap<String, Object> params);
	
	/**
	 * 멤버십 전시 순서 변경 (변경전값<변경후값)
	 * 
	 * @param params
	 * @return
	 */
	Integer updateMemberOrderListGt(HashMap<String, Object> params);
	
	/**
	 * 멤버십 전시 순서 변경 (변경전값>변경후값)
	 * 
	 * @param params
	 * @return
	 */
	Integer updateMemberOrderListLt(HashMap<String, Object> params);
	
	/**
	 * 멤버십 전시 순서 변경 
	 * 
	 * @param params
	 * @return
	 */
	Integer updateMemberOrder(HashMap<String, Object> params);
	
	/**
	 * 전시여부 변경
	 * 
	 * @param params
	 * @return
	 */
	Integer updateMemberShowYn(HashMap<String, Object> params);
	
	/**
	 * 추천순서 변경시 전시여부 초기화 
	 * @param params
	 * @return
	 */
	Integer updateMembRecommInitAll(HashMap<String, Object> params);
	
	Integer updateMemberOrderListIncrease(HashMap<String, Object> params);
}
