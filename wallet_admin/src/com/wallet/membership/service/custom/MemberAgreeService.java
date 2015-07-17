package com.wallet.membership.service.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.dao.custom.MemberAgreeDao;
import com.wallet.membership.model.custom.MemberAgree;

public class MemberAgreeService {
	private final MemberAgreeDao sDao;

	/**
	 * @Method Name : MemberAgreeService
	 * @Description : 생성자
	 * @param : 
	 * @return : 
	 * @author 김태리
	 * @since 2012.09.17
	 */
	public MemberAgreeService() {
		sDao = new MemberAgreeDao();
	}
	
	/**
	 * @Method Name : commit
	 * @Description : 
	 * @param : 
	 * @return : void
	 * @author 김태리
	 * @since 2012.09.17
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
	 * @since 2012.09.14
	 */
	public void rollback(){
		sDao.rollback();
	}
	
	/**
	 * @Method Name : selectMemberAgreeList
	 * @Description : 이용약관 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberAgree>
	 * @author 김태리
	 * @since 2012.09.17
	 */
	public List<MemberAgree> selectMemberAgreeList(HashMap<String ,Object> params) {
		return sDao.selectMemberAgreeList(params); //-- 이벤트/공지사항 목록을 조회함.
	}
	
	/**
	 * @Method Name : insertMemberAgree
	 * @Description : 이용약관 등록
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2012.09.17
	 */
	public int insertMemberAgree(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.insertMemberAgree(params);
		
		return result;
	}
	
	/**
	 * @Method Name : updateMemberAgree
	 * @Description : 이용약관 수정
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2012.09.17
	 */
	public int updateMemberAgree(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.updateMemberAgree(params);
		
		return result;
	}
	
	/**
	 * @Method Name : deleteMemberAgree
	 * @Description : 이용약관 삭제
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2012.09.17
	 */
	public int deleteMemberAgree(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.deleteMemberAgree(params);
		
		return result;
	}

}
