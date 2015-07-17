package com.wallet.membership.dao.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.membership.mapper.custom.MemberMapper;
import com.wallet.membership.model.custom.Member;

public class MemberDao extends MybatisCilent implements MemberMapper {

	private String preMapperName = "com.wallet.membership.mapper.custom.MemberMapper.";

	
	/**
	 * @Method Name : commit
	 * @Description :
	 * @param : 
	 * @return : 
	 * @author ���¸�
	 * @since 2012.09.10
	 */
	public void commit() {
		sqlMapper.commit();
	}
	
	/**
	 * @Method Name : rollback
	 * @Description :
	 * @param : 
	 * @return : 
	 * @author ���¸�
	 * @since 2012.09.14
	 */
	public void rollback(){
		sqlMapper.rollback();
	}
	
	
	/**
	 * @Method Name : MemberDao
	 * @Description : ������
	 * @param : 
	 * @return : 
	 * @author ���¸�
	 * @since 2012.09.10
	 */
	public MemberDao() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @Method Name : selectMemberList
	 * @Description : �����(ī������) ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<Member>
	 * @author ���¸�
	 * @since 2012.09.10
	 */
	@SuppressWarnings("unchecked")
	public List<Member> selectMemberList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectMemberList", params);
	}
	
	
	/**
	 * @Method Name : selectMemberListCnt
	 * @Description : �����(ī������) ��� �� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.10
	 */
	@SuppressWarnings("unchecked")
	public Integer selectMemberListCnt(HashMap<String,Object> params){
		Integer cnt = 0;
		
		cnt = (Integer) sqlMapper.selectOne(preMapperName + "selectMemberListCnt", params);
		return cnt;
	}

	
	/**
	 * @Method Name : selectAMember
	 * @Description : �����(ī������) ��ȸ
	 * @return : List<Member>
	 * @author ���¸�
	 * @since 2012.09.10
	 */
	@SuppressWarnings("unchecked")
	public Member selectAMember(HashMap<String,Object> params){
		return (Member) sqlMapper.selectOne(preMapperName + "selectMemberInfo", params);
	}
	
	
	/**
	 * @Method Name : insertMember
	 * @Description : �����(ī������) ���
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.10
	 */
	@SuppressWarnings("unchecked")
	public Integer insertMember(HashMap<String,Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.insert(preMapperName + "insertMember", params));
		return result;
	}
	
	
	/**
	 * @Method Name : updateMember
	 * @Description : �����(ī������) ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.10
	 */
	@SuppressWarnings("unchecked")
	public Integer updateMember(HashMap<String,Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.update(preMapperName + "updateMember", params));
		return result;
	}
	
	/**
	 * @Method Name : updateMember
	 * @Description : �����(ī������) ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.10
	 */
	@SuppressWarnings("unchecked")
	public Integer deleteMember(HashMap<String, Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.delete(preMapperName + "deleteMember", params));
		return result;
	}
	
	/**
	 * @Method Name : memberDupCheck
	 * @Description : ����� ID�� ��� ��, �ߺ����θ� Ȯ���Ѵ�.
	 * @param : String
	 * @return : Integer 0/1 (0:�ߺ��ƴ�, 1:�ߺ�)
	 * @author trkim
	 * @since 2012.09.11
	 */
	public Integer memberDupCheck(String membId){
		Integer result = 0;
		
		result = (Integer) sqlMapper.selectOne(preMapperName + "memberDupCheck", membId);
		
		return result;
	}

	/**
	 * ����� ���� ���� 
	 * 
	 * @param params
	 * @return
	 */
	public List<Member> selectMemberOrderList(HashMap<String, Object> params) {
		return sqlMapper.selectList(preMapperName + "selectMemberOrderList", params);
	}

	/**
	 * ����� ���ü��� ���� (��������<�����İ�)
	 * 
	 * @param params
	 * @return
	 */
	public Integer updateMemberOrderListGt(HashMap<String, Object> params) {
		return new Integer(sqlMapper.update(preMapperName + "updateMemberOrderListGt", params));
	}

	/**
	 * ����� ���ü��� ���� (��������>�����İ�)
	 * 
	 * @param params
	 * @return
	 */
	public Integer updateMemberOrderListLt(HashMap<String, Object> params) {
		return new Integer(sqlMapper.update(preMapperName + "updateMemberOrderListLt", params));
	}

	/**
	 * ����� ���ü��� ����
	 * 
	 * @param params
	 * @return
	 */
	public Integer updateMemberOrder(HashMap<String, Object> params) {
		return new Integer(sqlMapper.update(preMapperName + "updateMemberOrder", params));
	}

	/**
	 * ���ÿ��� ����
	 * 
	 * @param params
	 * @return
	 */
	public Integer updateMemberShowYn(HashMap<String, Object> params) {
		return new Integer(sqlMapper.update(preMapperName + "updateMemberShowYn", params));
	}

	/**
	 * ��õ���� ����� ��õ���ÿ��� �ʱ�ȭ 
	 * 
	 * @param params
	 * @return
	 */
	public Integer updateMembRecommInitAll(HashMap<String, Object> params) {
		return new Integer(sqlMapper.update(preMapperName + "updateMembRecommInitAll", params));
	}

	public Integer updateMemberOrderListIncrease(HashMap<String, Object> params) {
		return new Integer(sqlMapper.update(preMapperName + "updateMemberOrderListIncrease", params));
	}
}
