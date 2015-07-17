package com.wallet.membership.service.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.dao.custom.MemberClassDao;
import com.wallet.membership.model.custom.MemberClass;

public class MemberClassService {
	private final MemberClassDao sDao;

	/**
	 * @Method Name : MemberClassService
	 * @Description : 생성자
	 * @param : 
	 * @return : 
	 * @author 김태리
	 * @since 2012.09.16
	 */
	public MemberClassService() {
		sDao = new MemberClassDao();
	}
	
	/**
	 * @Method Name : commit
	 * @Description : 
	 * @param : 
	 * @return : void
	 * @author 김태리
	 * @since 2012.09.16
	 */
	public void commit(){
		sDao.commit();
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
		sDao.rollback();
	}
	
	/**
	 * @Method Name : selectMemberClassList
	 * @Description : 멤버십 등급 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberClass>
	 * @author 김태리
	 * @since 2012.09.16
	 */
	public List<MemberClass> selectMemberClassList(HashMap<String ,Object> params) {
		List<MemberClass> result = null;
		
		result = sDao.selectMemberClassList(params); //-- 이벤트/공지사항 목록을 조회함.

		return result;
	}
	
	/**
	 * @Method Name : selectMemberClassListCnt
	 * @Description : 멤버십 등급 목록 수 조회
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2012.09.16
	 */
	public int selectMemberClassListCnt(HashMap<String, Object> params) {
		int result = 0;
		
		result = Integer.parseInt(sDao.selectMemberClassListCnt(params).toString()); //-- 이벤트/공지사항 목록을 조회함.

		return result;
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
		MemberClass aMemberClass = sDao.selectAMemberClass(params);
		
		return aMemberClass;
	}
	
	/**
	 * @Method Name : insertMemberClass
	 * @Description : 멤버십 등급 등록
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2012.09.16
	 */
	public int insertMemberClass(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.insertMemberClass(params);
		
		return result;
	}
	
	/**
	 * @Method Name : updateMemberClass
	 * @Description : 멤버십 등급 수정
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2012.09.16
	 */
	public int updateMemberClass(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.updateMemberClass(params);
		
		return result;
	}
	
	/**
	 * @Method Name : deleteMemberClass
	 * @Description : 멤버십 등급 삭제
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2012.09.16
	 */
	public int deleteMemberClass(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.deleteMemberClass(params);
		
		return result;
	}
}
