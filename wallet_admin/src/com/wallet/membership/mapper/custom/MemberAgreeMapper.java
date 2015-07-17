package com.wallet.membership.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.MemberAgree;

public interface MemberAgreeMapper {


	/**
	 * @Method Name : selectMemberAgreeList
	 * @Description : 이용약관 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberAgree>
	 * @author 김태리
	 * @since 2012.09.05
	 */
	List<MemberAgree> selectMemberAgreeList(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : insertMemberAgree
	 * @Description : 이용약관 등록
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2012.09.17
	 */
	Integer insertMemberAgree(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : updateMemberAgree
	 * @Description : 이용약관 수정
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2012.09.17
	 */
	Integer updateMemberAgree(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : deleteMemberAgree
	 * @Description : 이용약관 삭제
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2012.09.17
	 */
	Integer deleteMemberAgree(HashMap<String ,Object> params);
}
