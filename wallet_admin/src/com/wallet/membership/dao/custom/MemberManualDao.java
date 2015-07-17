package com.wallet.membership.dao.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.membership.mapper.custom.MemberManualMapper;
import com.wallet.membership.model.custom.MemberManual;

public class MemberManualDao extends MybatisCilent implements MemberManualMapper {

	private String preMapperName = "com.wallet.membership.mapper.custom.MemberManualMapper.";

	
	/**
	 * @Method Name : commit
	 * @Description :
	 * @param : 
	 * @return : 
	 * @author ���¸�
	 * @since 2012.09.19
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
	 * @Method Name : MemberManualDao
	 * @Description : ������
	 * @param : 
	 * @return : 
	 * @author ���¸�
	 * @since 2012.09.19
	 */
	public MemberManualDao() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @Method Name : selectMemberManualList
	 * @Description : ������� ����� ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberManual>
	 * @author ���¸�
	 * @since 2012.09.19
	 */
	@SuppressWarnings("unchecked")
	public List<MemberManual> selectMemberManualList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectMemberManualList", params);
	}
	
	
	/**
	 * @Method Name : selectMemberManualListCnt
	 * @Description : ������� ����� ��� �� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.19
	 */
	@SuppressWarnings("unchecked")
	public Integer selectMemberManualListCnt(HashMap<String,Object> params){
		Integer cnt = 0;
		
		cnt = (Integer) sqlMapper.selectOne(preMapperName + "selectMemberManualListCnt", params);
		return cnt;
	}

	
	/**
	 * @Method Name : selectAMemberManual
	 * @Description : ������� ����� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberManual>
	 * @author ���¸�
	 * @since 2012.09.19
	 */
	@SuppressWarnings("unchecked")
	public MemberManual selectAMemberManual(HashMap<String,Object> params){
		return (MemberManual) sqlMapper.selectOne(preMapperName + "selectMemberManualInfo", params);
	}
	
	
	/**
	 * @Method Name : insertMemberManual
	 * @Description : ������� ����� ���
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.19
	 */
	@SuppressWarnings("unchecked")
	public Integer insertMemberManual(HashMap<String,Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.insert(preMapperName + "insertMemberManual", params));
		return result;
	}
	
	
	/**
	 * @Method Name : updateMemberManual
	 * @Description : ������� ����� ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.19
	 */
	@SuppressWarnings("unchecked")
	public Integer updateMemberManual(HashMap<String,Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.update(preMapperName + "updateMemberManual", params));
		return result;
	}
	
	/**
	 * @Method Name : updateMemberManual
	 * @Description : ������� ����� ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.19
	 */
	@SuppressWarnings("unchecked")
	public Integer deleteMemberManual(HashMap<String, Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.delete(preMapperName + "deleteMemberManual", params));
		return result;
	}
	
	/**
	 * @Method Name : memberManualDupCheck
	 * @Description : ����� ID�� ��� ��, �ߺ����θ� Ȯ���Ѵ�.
	 * @param : String
	 * @return : Integer 0/1 (0:�ߺ��ƴ�, 1:�ߺ�)
	 * @author trkim
	 * @since 2012.09.19
	 */
	public Integer memberManualDupCheck(String membId){
		Integer result = 0;
		
		result = (Integer) sqlMapper.selectOne(preMapperName + "memberManualDupCheck", membId);
		
		return result;
	}
}
