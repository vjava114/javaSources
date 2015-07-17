package com.wallet.membership.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.MemberClass;

public interface MemberClassMapper {
	/**
	 * @Method Name : selectMemberClassList
	 * @Description : ����� ��� ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberClass>
	 * @author ���¸�
	 * @since 2012.09.16
	 */
	List<MemberClass> selectMemberClassList(HashMap<String ,Object> params);
	
	
	/**
	 * @Method Name : selectMemberClassListCnt
	 * @Description : ����� ��� ��� �� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.16
	 */
	Integer selectMemberClassListCnt(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : insertMemberClass
	 * @Description : ����� ��� ���
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.16
	 */
	Integer insertMemberClass(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : updateMemberClass
	 * @Description : ����� ��� ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.16
	 */
	Integer updateMemberClass(HashMap<String ,Object> params);
}
