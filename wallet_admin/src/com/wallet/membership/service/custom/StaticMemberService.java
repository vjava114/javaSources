package com.wallet.membership.service.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.dao.custom.StaticMemberDao;
import com.wallet.membership.model.custom.StaticMember;

public class StaticMemberService {

	private final StaticMemberDao sDao;

	/**
	 * @Method Name : StaticMemberService
	 * @Description : 생성자
	 * @param : 
	 * @return : 
	 * @author 김태리
	 * @since 2012.09.27
	 */
	public StaticMemberService() {
		sDao = new StaticMemberDao();
	}
	
	/**
	 * @Method Name : commit
	 * @Description : 
	 * @param : 
	 * @return : void
	 * @author 김태리
	 * @since 2012.09.27
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
	 * @Method Name : selectStaticMemberList
	 * @Description : 결제사 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<StaticMember>
	 * @author 김태리
	 * @since 2012.09.27
	 */
	public List<StaticMember> selectMemberList(HashMap<String ,Object> params) {
		List<StaticMember> result = null;
		
		result = sDao.selectMemberList(params); //-- 이벤트/공지사항 목록을 조회함.

		return result;
	}

}
