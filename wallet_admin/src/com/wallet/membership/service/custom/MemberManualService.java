package com.wallet.membership.service.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.dao.custom.MemberManualDao;
import com.wallet.membership.model.custom.MemberManual;

public class MemberManualService {
	private final MemberManualDao sDao;

	/**
	 * @Method Name : MemberManualService
	 * @Description : 생성자
	 * @param : 
	 * @return : 
	 * @author 김태리
	 * @since 2012.09.19
	 */
	public MemberManualService() {
		sDao = new MemberManualDao();
	}
	
	/**
	 * @Method Name : commit
	 * @Description : 
	 * @param : 
	 * @return : void
	 * @author 김태리
	 * @since 2012.09.19
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
	 * @Method Name : selectMemberManualList
	 * @Description : 직접등록 멤버십 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberManual>
	 * @author 김태리
	 * @since 2012.09.19
	 */
	public List<MemberManual> selectMemberManualList(HashMap<String ,Object> params) {
		List<MemberManual> result = null;
		
		result = sDao.selectMemberManualList(params); //-- 이벤트/공지사항 목록을 조회함.

		return result;
	}
	
	/**
	 * @Method Name : selectMemberManualListCnt
	 * @Description : 직접등록 멤버십 목록 수 조회
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2012.09.19
	 */
	public int selectMemberManualListCnt(HashMap<String, Object> params) {
		int result = 0;
		
		result = Integer.parseInt(sDao.selectMemberManualListCnt(params).toString()); //-- 이벤트/공지사항 목록을 조회함.

		return result;
	}

	
	/**
	 * @Method Name : selectAMemberManual
	 * @Description : 직접등록 멤버십 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberManual>
	 * @author 김태리
	 * @since 2012.09.19
	 */
	@SuppressWarnings("unchecked")
	public MemberManual selectAMemberManual(HashMap<String,Object> params){
		MemberManual aMemberManual = sDao.selectAMemberManual(params);
		
		return aMemberManual;
	}
	
	/**
	 * @Method Name : insertMemberManual
	 * @Description : 직접등록 멤버십 등록
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2012.09.19
	 */
	public int insertMemberManual(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.insertMemberManual(params);
		
		return result;
	}
	
	/**
	 * @Method Name : updateMemberManual
	 * @Description : 직접등록 멤버십 수정
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2012.09.19
	 */
	public int updateMemberManual(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.updateMemberManual(params);
		
		return result;
	}
	
	/**
	 * @Method Name : deleteMemberManual
	 * @Description : 직접등록 멤버십 삭제
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2012.09.19
	 */
	public int deleteMemberManual(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.deleteMemberManual(params);
		
		return result;
	}
	
	/**
	 * @Method Name : memberManualDupCheck
	 * @Description : 멤버십 ID의 등록 전, 중복여부를 확인한다.
	 * @param : String
	 * @return : int 0/1 (0:중복아님, 1:중복)
	 * @author trkim
	 * @since 2012.09.19
	 */
	public int memberManualDupCheck(String selfMembId){
		int result = 0;
		
		result = Integer.parseInt(sDao.memberManualDupCheck(selfMembId).toString());
		
		return result;
	}
}
