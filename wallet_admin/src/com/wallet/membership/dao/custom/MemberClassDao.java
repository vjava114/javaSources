package com.wallet.membership.dao.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.membership.mapper.custom.MemberClassMapper;
import com.wallet.membership.model.custom.MemberClass;

public class MemberClassDao extends MybatisCilent implements MemberClassMapper {

	private String preMapperName = "com.wallet.membership.mapper.custom.MemberClassMapper.";

	
	/**
	 * @Method Name : commit
	 * @Description :
	 * @param : 
	 * @return : 
	 * @author 김태리
	 * @since 2012.09.16
	 */
	public void commit() {
		sqlMapper.commit();
	}
	
	/**
	 * @Method Name : rollback
	 * @Description :
	 * @param : 
	 * @return : 
	 * @author 김태리
	 * @since 2012.09.16
	 */
	public void rollback(){
		sqlMapper.rollback();
	}
	
	/**
	 * @Method Name : MemberClassDao
	 * @Description : 생성자
	 * @param : 
	 * @return : 
	 * @author 김태리
	 * @since 2012.09.16
	 */
	public MemberClassDao() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @Method Name : selectMemberClassList
	 * @Description : 멤버십 등급 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberClass>
	 * @author 김태리
	 * @since 2012.09.16
	 */
	@SuppressWarnings("unchecked")
	public List<MemberClass> selectMemberClassList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectMemberClassList", params);
	}
	
	
	/**
	 * @Method Name : selectMemberClassListCnt
	 * @Description : 멤버십 등급 목록 수 조회
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2012.09.16
	 */
	@SuppressWarnings("unchecked")
	public Integer selectMemberClassListCnt(HashMap<String,Object> params){
		Integer cnt = 0;
		
		cnt = (Integer) sqlMapper.selectOne(preMapperName + "selectMemberClassListCnt", params);
		return cnt;
	}

	
	/**
	 * @Method Name : selectAMemberClass
	 * @Description : 멤버십 등급 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberClass>
	 * @author 김태리
	 * @since 2012.09.16
	 */
	@SuppressWarnings("unchecked")
	public MemberClass selectAMemberClass(HashMap<String,Object> params){
		return (MemberClass) sqlMapper.selectOne(preMapperName + "selectMemberClassInfo", params);
	}
	
	
	/**
	 * @Method Name : insertMemberClass
	 * @Description : 멤버십 등급 등록
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2012.09.16
	 */
	@SuppressWarnings("unchecked")
	public Integer insertMemberClass(HashMap<String,Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.insert(preMapperName + "insertMemberClass", params));
		return result;
	}
	
	
	/**
	 * @Method Name : updateMemberClass
	 * @Description : 멤버십 등급 수정
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2012.09.16
	 */
	@SuppressWarnings("unchecked")
	public Integer updateMemberClass(HashMap<String,Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.update(preMapperName + "updateMemberClass", params));
		return result;
	}
	
	/**
	 * @Method Name : updateMemberClass
	 * @Description : 멤버십 등급 삭제
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2012.09.16
	 */
	@SuppressWarnings("unchecked")
	public Integer deleteMemberClass(HashMap<String, Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.delete(preMapperName + "deleteMemberClass", params));
		return result;
	}
}
