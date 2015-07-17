package com.wallet.membership.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.MemberClass;

public interface MemberClassMapper {
	/**
	 * @Method Name : selectMemberClassList
	 * @Description : 멤버십 등급 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberClass>
	 * @author 김태리
	 * @since 2012.09.16
	 */
	List<MemberClass> selectMemberClassList(HashMap<String ,Object> params);
	
	
	/**
	 * @Method Name : selectMemberClassListCnt
	 * @Description : 멤버십 등급 목록 수 조회
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2012.09.16
	 */
	Integer selectMemberClassListCnt(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : insertMemberClass
	 * @Description : 멤버십 등급 등록
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2012.09.16
	 */
	Integer insertMemberClass(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : updateMemberClass
	 * @Description : 멤버십 등급 수정
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2012.09.16
	 */
	Integer updateMemberClass(HashMap<String ,Object> params);
}
