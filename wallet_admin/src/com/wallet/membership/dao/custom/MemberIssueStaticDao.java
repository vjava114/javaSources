package com.wallet.membership.dao.custom;


import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.membership.mapper.custom.MemberIssueStaticMapper;
import com.wallet.membership.model.custom.MemberIssueStatic;

public class MemberIssueStaticDao extends MybatisCilent implements MemberIssueStaticMapper {
	
	private String preMapperName = "com.wallet.membership.mapper.custom.MemberIssueStaticMapper.";

	
	/**
	 * @Method Name : MemberIssueStaticDao
	 * @Description : ������
	 * @param : 
	 * @return : 
	 * @author ��ϼ�
	 * @since 2012.09.24
	 */
	public MemberIssueStaticDao() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @Method Name : selectMemberIssueStaticDayList
	 * @Description : �߱����(�Ϻ�) ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author ��ϼ�
	 * @since 2012.09.24
	 */
	@SuppressWarnings("unchecked")
	public List<MemberIssueStatic> selectMemberIssueStaticDayList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectMemberIssueStaticDayList", params);
	}
	
	
	/**
	 * @Method Name : selectMemberIssueStaticMonthList
	 * @Description : �߱����(����) ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author ��ϼ�
	 * @since 2012.09.24
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> selectMemberIssueStaticMonthList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectMemberIssueStaticMonthList", params);
	}
	
	
	/**
	 * @Method Name : selectMemberIssueCancelStaticDayList
	 * @Description : �߱�������(�Ϻ�) ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author ��ϼ�
	 * @since 2012.09.24
	 */
	@SuppressWarnings("unchecked")
	public List<MemberIssueStatic> selectMemberIssueCancelStaticDayList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectMemberIssueCancelStaticDayList", params);
	}
	
	/**
	 * @Method Name : selectMemberIssueCancelStaticMonthList
	 * @Description : �߱�������(����) ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<MemberIssueStatic>
	 * @author ��ϼ�
	 * @since 2012.09.26
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> selectMemberIssueCancelStaticMonthList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectMemberIssueCancelStaticMonthList", params);
	}
}

