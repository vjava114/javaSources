package com.wallet.membership.mapper.custom;


import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.MemberEvent;

public interface MemberEventMapper {
	/**
	 * @Method Name : selectMemberEvent
	 * @Description : ��������/�̺�Ʈ ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberEvent>
	 * @author ��ϼ�
	 * @since 2012.09.17
	 */
	List<MemberEvent> selectMemberEventList(HashMap<String ,Object> params);
	
	
	/**
	 * @Method Name : selectMemberEventListCnt
	 * @Description : ��������/�̺�Ʈ ��� �� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ��ϼ�
	 * @since 2012.09.17
	 */
	Integer selectMemberEventListCnt(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : insertMemberEvent
	 * @Description : ��������/�̺�Ʈ ���
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ��ϼ�
	 * @since 2012.09.17
	 */
	Integer insertMemberEvent(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : updateMemberEvent
	 * @Description : ��������/�̺�Ʈ ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ��ϼ�
	 * @since 2012.09.17
	 */
	Integer updateMemberEvent(HashMap<String ,Object> params);
}

