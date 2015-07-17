package com.wallet.membership.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.MemberManual;

public interface MemberManualMapper {

	/**
	 * @Method Name : selectMemberManualList
	 * @Description : ������� ����� ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberManual>
	 * @author ���¸�
	 * @since 2012.09.19
	 */
	List<MemberManual> selectMemberManualList(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : selectMemberManualList
	 * @Description : ������� ����� ��ϼ� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.19
	 */
	
	Integer selectMemberManualListCnt(HashMap<String ,Object> params);
	
	
	/**
	 * @Method Name : insertMemberManual
	 * @Description : ������� ����� ���
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.19
	 */
	Integer insertMemberManual(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : updateMemberManual
	 * @Description : ������� ����� ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.19
	 */
	Integer updateMemberManual(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : deleteMemberManual
	 * @Description : ������� ����� ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.19
	 */
	Integer deleteMemberManual(HashMap<String ,Object> params);
}
