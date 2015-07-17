package com.wallet.membership.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.MemberAgree;

public interface MemberAgreeMapper {


	/**
	 * @Method Name : selectMemberAgreeList
	 * @Description : �̿��� ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberAgree>
	 * @author ���¸�
	 * @since 2012.09.05
	 */
	List<MemberAgree> selectMemberAgreeList(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : insertMemberAgree
	 * @Description : �̿��� ���
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.17
	 */
	Integer insertMemberAgree(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : updateMemberAgree
	 * @Description : �̿��� ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.17
	 */
	Integer updateMemberAgree(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : deleteMemberAgree
	 * @Description : �̿��� ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.17
	 */
	Integer deleteMemberAgree(HashMap<String ,Object> params);
}
