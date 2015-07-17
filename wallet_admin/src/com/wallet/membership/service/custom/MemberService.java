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
	 * @Description : ������
	 * @param : 
	 * @return : 
	 * @author ���¸�
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
	 * @author ���¸�
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
	 * @author ���¸�
	 * @since 2012.09.14
	 */
	public void rollback(){
		sDao.rollback();
	}
	
	/**
	 * @Method Name : selectMemberList
	 * @Description : �����(ī������) ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<Member>
	 * @author ���¸�
	 * @since 2012.09.10
	 */
	public List<Member> selectMemberList(HashMap<String ,Object> params) {
		List<Member> result = null;
		
		result = sDao.selectMemberList(params); //-- ����� ����� ��ȸ��.

		return result;
	}
	
	/**
	 * @Method Name : selectMemberListCnt
	 * @Description : �����(ī������) ��� �� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ���¸�
	 * @since 2012.09.10
	 */
	public int selectMemberListCnt(HashMap<String, Object> params) {
		int result = 0;
		
		result = Integer.parseInt(sDao.selectMemberListCnt(params).toString()); //-- ����� ����� ��ȸ��.

		return result;
	}

	
	/**
	 * @Method Name : selectAMember
	 * @Description : �����(ī������) ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<Member>
	 * @author ���¸�
	 * @since 2012.09.10
	 */
	@SuppressWarnings("unchecked")
	public Member selectAMember(HashMap<String,Object> params){
		Member aMember = sDao.selectAMember(params);
		
		return aMember;
	}
	
	/**
	 * @Method Name : insertMember
	 * @Description : �����(ī������) ���
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ���¸�
	 * @since 2012.09.10
	 */
	public int insertMember(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.insertMember(params);
		
		return result;
	}
	
	/**
	 * @Method Name : updateMember
	 * @Description : �����(ī������) ����
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ���¸�
	 * @since 2012.09.10
	 */
	public int updateMember(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.updateMember(params);
		
		return result;
	}
	
	/**
	 * @Method Name : deleteMember
	 * @Description : �����(ī������) ����
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ���¸�
	 * @since 2012.09.10
	 */
	public int deleteMember(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.deleteMember(params);
		
		return result;
	}
	
	/**
	 * @Method Name : memberDupCheck
	 * @Description : ����� ID�� ��� ��, �ߺ����θ� Ȯ���Ѵ�.
	 * @param : String
	 * @return : int 0/1 (0:�ߺ��ƴ�, 1:�ߺ�)
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
	 * @Description : ��Ƽ�����(ī������) ��� ��ȸ
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2012.09.17
	 */
	public List<Member> selectMultiMemberList(HashMap<String, Object> params){
		List<Member> result = null;
		
		result = sDao.selectMemberList(params); //-- ����� ����� ��ȸ��.

		return result;
		
	}
	
	/**
	 * @Method Name : selectMultiMemberListCnt
	 * @Description : ��Ƽ�����(ī������) ��� �� ��ȸ
	 * @param : Map, Locale, HttpServletRequest, HttpServletResponse 
	 * @return : String 
	 * @author ���¸�
	 * @since 2012.09.17
	 */
	public int selectMultiMemberListCnt(HashMap<String, Object> params){
		int result = 0;
		
		result = Integer.parseInt(sDao.selectMemberListCnt(params).toString()); //-- ����� ����� ��ȸ��.

		return result;
	}

	/**
	 * ����� ���ü���
	 * 
	 * @param params
	 * @return
	 */
	public List<Member> selectMemberOrderList(HashMap<String, Object> params) {
		return sDao.selectMemberOrderList(params); //-- ����� ����� ��ȸ��.
	}


	/**
	 * ����� ���ü��� ���� (��������<�����İ�)
	 * 
	 * @param params
	 * @return
	 */
	public int updateMemberOrderListGt(HashMap<String, Object> params) {
		log.debug("########## MemberService.updateMemberOrderListGt ##########");
		return sDao.updateMemberOrderListGt(params);
	}


	/**
	 * ����� ���ü��� ���� (��������>�����İ�)
	 * 
	 * @param params
	 * @return
	 */
	public int updateMemberOrderListLt(HashMap<String, Object> params) {
		log.debug("########## MemberService.updateMemberOrderListLt ##########");
		return sDao.updateMemberOrderListLt(params);
	}
	
	/**
	 * ����� ���ü��� ����
	 * 
	 * @param params
	 * @return
	 */
	public int updateMemberOrder(HashMap<String, Object> params) {
		log.debug("########## MemberService.updateMemberOrder ##########");
		return sDao.updateMemberOrder(params);
	}

	/**
	 * ���ÿ��� ����
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
