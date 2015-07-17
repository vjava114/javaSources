package com.wallet.membership.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.MemberManual;

public interface MemberManualMapper {

	/**
	 * @Method Name : selectMemberManualList
	 * @Description : 직접등록 멤버십 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberManual>
	 * @author 김태리
	 * @since 2012.09.19
	 */
	List<MemberManual> selectMemberManualList(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : selectMemberManualList
	 * @Description : 직접등록 멤버십 목록수 조회
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2012.09.19
	 */
	
	Integer selectMemberManualListCnt(HashMap<String ,Object> params);
	
	
	/**
	 * @Method Name : insertMemberManual
	 * @Description : 직접등록 멤버십 등록
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2012.09.19
	 */
	Integer insertMemberManual(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : updateMemberManual
	 * @Description : 직접등록 멤버십 수정
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2012.09.19
	 */
	Integer updateMemberManual(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : deleteMemberManual
	 * @Description : 직접등록 멤버십 수정
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2012.09.19
	 */
	Integer deleteMemberManual(HashMap<String ,Object> params);
}
