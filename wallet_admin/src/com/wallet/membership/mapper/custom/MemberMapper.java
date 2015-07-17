package com.wallet.membership.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.Member;

public interface MemberMapper {
	/**
	 * @Method Name : selectMemberList
	 * @Description : �����(ī������) ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<Member>
	 * @author ���¸�
	 * @since 2012.09.10
	 */
	List<Member> selectMemberList(HashMap<String ,Object> params);
	
	
	/**
	 * @Method Name : selectMemberListCnt
	 * @Description : �����(ī������) ��� �� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.10
	 */
	Integer selectMemberListCnt(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : insertMember
	 * @Description : �����(ī������) ���
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.10
	 */
	Integer insertMember(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : updateMember
	 * @Description : �����(ī������) ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.10
	 */
	Integer updateMember(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : memberDupCheck
	 * @Description : ����� ID�� ��� ��, �ߺ����θ� Ȯ���Ѵ�.
	 * @param : String
	 * @return : Integer 0/1 (0:�ߺ��ƴ�, 1:�ߺ�)
	 * @author trkim
	 * @since 2012.09.11
	 */
	Integer memberDupCheck(String membId);
	
	/**
	 * ����� ���� ����
	 * 
	 * @param params
	 * @return
	 */
	List<Member> selectMemberOrderList(HashMap<String, Object> params);
	
	/**
	 * ����� ���� ���� ���� (��������<�����İ�)
	 * 
	 * @param params
	 * @return
	 */
	Integer updateMemberOrderListGt(HashMap<String, Object> params);
	
	/**
	 * ����� ���� ���� ���� (��������>�����İ�)
	 * 
	 * @param params
	 * @return
	 */
	Integer updateMemberOrderListLt(HashMap<String, Object> params);
	
	/**
	 * ����� ���� ���� ���� 
	 * 
	 * @param params
	 * @return
	 */
	Integer updateMemberOrder(HashMap<String, Object> params);
	
	/**
	 * ���ÿ��� ����
	 * 
	 * @param params
	 * @return
	 */
	Integer updateMemberShowYn(HashMap<String, Object> params);
	
	/**
	 * ��õ���� ����� ���ÿ��� �ʱ�ȭ 
	 * @param params
	 * @return
	 */
	Integer updateMembRecommInitAll(HashMap<String, Object> params);
	
	Integer updateMemberOrderListIncrease(HashMap<String, Object> params);
}
