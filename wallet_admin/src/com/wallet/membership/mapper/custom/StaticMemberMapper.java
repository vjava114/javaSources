package com.wallet.membership.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.StaticMember;

public interface StaticMemberMapper {
	/**
	 * @Method Name : selectSettlementList
	 * @Description : 결제사 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<StaticMember>
	 * @author 김태리
	 * @since 2012.09.27
	 */
	List<StaticMember> selectMemberList(HashMap<String ,Object> params);
	
}
