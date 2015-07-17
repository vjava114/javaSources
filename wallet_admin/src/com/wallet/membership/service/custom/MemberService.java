package com.wallet.membership.service.custom;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.wallet.common.util.Log;
import com.wallet.membership.dao.custom.MemberDao;
import com.wallet.membership.model.MwCmCompany;
import com.wallet.membership.model.custom.Member;

public class MemberService {
	private final MemberDao sDao;
	private Logger log = Log.getLogger("logs");

	/**
	 * @Method Name : MemberService
	 * @Description : 생성자
	 * @param : 
	 * @return : 
	 * @author 김태리
	 * @since 2012.09.10
	 */
	public MemberService() {
		sDao = new MemberDao();
	}
	
	/**
	 * @Method Name : commit
	 * @Description : 
	 * @param : 
	 * @return : void
	 * @author 김태리
	 * @since 2012.09.10
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
	 * @Method Name : selectMemberList
	 * @Description : 멤버십(카드정보) 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<Member>
	 * @author 김태리
	 * @since 2012.09.10
	 */
	public List<Member> selectMemberList(HashMap<String ,Object> params) {
		List<Member> result = null;
		
		result = sDao.selectMemberList(params); //-- 멤버십 목록을 조회함.

		return result;
	}
	
	/**
	 * @Method Name : selectMemberListCnt
	 * @Description : 멤버십(카드정보) 목록 수 조회
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2012.09.10
	 */
	public int selectMemberListCnt(HashMap<String, Object> params) {
		int result = 0;
		
		result = Integer.parseInt(sDao.selectMemberListCnt(params).toString()); //-- 멤버십 목록을 조회함.

		return result;
	}

	
	/**
	 * @Method Name : selectAMember
	 * @Description : 멤버십(카드정보) 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<Member>
	 * @author 김태리
	 * @since 2012.09.10
	 */
	@SuppressWarnings("unchecked")
	public Member selectAMember(HashMap<String,Object> params){
		Member aMember = sDao.selectAMember(params);
		
		return aMember;
	}
	
	/**
	 * @Method Name : insertMember
	 * @Description : 멤버십(카드정보) 등록
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2012.09.10
	 */
	public int insertMember(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.insertMember(params);
		
		return result;
	}
	
	/**
	 * @Method Name : updateMember
	 * @Description : 멤버십(카드정보) 수정
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2012.09.10
	 */
	public int updateMember(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.updateMember(params);
		
		return result;
	}
	
	/**
	 * @Method Name : deleteMember
	 * @Description : 멤버십(카드정보) 삭제
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author 김태리
	 * @since 2012.09.10
	 */
	public int deleteMember(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.deleteMember(params);
		
		return result;
	}
	
	/**
	 * @Method Name : memberDupCheck
	 * @Description : 멤버십 ID의 등록 전, 중복여부를 확인한다.
	 * @param : String
	 * @return : int 0/1 (0:중복아님, 1:중복)
	 * @author trkim
	 * @since 2012.09.11
	 */
	public int memberDupCheck(String membId){
		int result = 0;
		
		result = Integer.parseInt(sDao.memberDupCheck(membId).toString());
		
		return result;
	}
	
	/**
	 * @Method Name : selectMultiMemberList
	 * @Description : 멀티멤버십(카드정보) 목록 조회
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.17
	 */
	public List<Member> selectMultiMemberList(HashMap<String, Object> params){
		List<Member> result = null;
		
		result = sDao.selectMemberList(params); //-- 멤버십 목록을 조회함.

		return result;
		
	}
	
	/**
	 * @Method Name : selectMultiMemberListCnt
	 * @Description : 멀티멤버십(카드정보) 목록 수 조회
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author 김태리
	 * @since 2012.09.17
	 */
	public int selectMultiMemberListCnt(HashMap<String, Object> params){
		int result = 0;
		
		result = Integer.parseInt(sDao.selectMemberListCnt(params).toString()); //-- 멤버십 목록을 조회함.

		return result;
	}

	/**
	 * 멤버십 전시순서
	 * 
	 * @param params
	 * @return
	 */
	public List<Member> selectMemberOrderList(HashMap<String, Object> params) {
		return sDao.selectMemberOrderList(params); //-- 멤버십 목록을 조회함.
	}


	/**
	 * 멤버십 전시순서 변경 (변경전값<변경후값)
	 * 
	 * @param params
	 * @return
	 */
	public int updateMemberOrderListGt(HashMap<String, Object> params) {
		log.debug("########## MemberService.updateMemberOrderListGt ##########");
		return sDao.updateMemberOrderListGt(params);
	}


	/**
	 * 멤버십 전시순서 변경 (변경전값>변경후값)
	 * 
	 * @param params
	 * @return
	 */
	public int updateMemberOrderListLt(HashMap<String, Object> params) {
		log.debug("########## MemberService.updateMemberOrderListLt ##########");
		return sDao.updateMemberOrderListLt(params);
	}
	
	/**
	 * 멤버십 전시순서 변경
	 * 
	 * @param params
	 * @return
	 */
	public int updateMemberOrder(HashMap<String, Object> params) {
		log.debug("########## MemberService.updateMemberOrder ##########");
		return sDao.updateMemberOrder(params);
	}

	/**
	 * 전시여부 변경
	 * 
	 * @param params
	 * @return
	 */
	public int updateMemberShowYn(HashMap<String, Object> params) {
		log.debug("########## MemberService.updateMemberShowYn ##########");
		return sDao.updateMemberShowYn(params);
	}

	public int updateMembRecommInitAll(HashMap<String, Object> params) {
		log.debug("########## MemberService.updateMembRecommInitAll ##########");
		return sDao.updateMembRecommInitAll(params);
	}

	public int updateMemberOrderListIncrease(HashMap<String, Object> params) {
		log.debug("########## MemberService.updateMemberOrderListIncrease ##########");
		return sDao.updateMemberOrderListIncrease(params);
	}
}
