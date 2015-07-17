package com.wallet.membership.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.StaticMember;

public interface StaticMemberMapper {
	/**
	 * @Method Name : selectSettlementList
	 * @Description : ������ ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<StaticMember>
	 * @author ���¸�
	 * @since 2012.09.27
	 */
	List<StaticMember> selectMemberList(HashMap<String ,Object> params);
	
}
