package com.wallet.membership.dao.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.membership.mapper.custom.FinanceStaticMapper;
import com.wallet.membership.model.custom.FinanceStatic;

public class FinanceStaticDao  extends MybatisCilent implements FinanceStaticMapper {

	private String preMapperName = "com.wallet.membership.mapper.custom.FinanceStaticMapper.";
	
	/**
	 * @Method Name : commit
	 * @Description :
	 * @param : 
	 * @return : 
	 * @author ���¸�
	 * @since 2012.09.25
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
	 * @Method Name : FinanceStaticDao
	 * @Description : ������
	 * @param : 
	 * @return : 
	 * @author ���¸�
	 * @since 2012.09.25
	 */
	public FinanceStaticDao() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @Method Name : selectfinanceStaticDayList
	 * @Description : �����纰 ������� �Ϻ����
	 * @param : HashMap<String ,Object>
	 * @return : List<FinanceStatic>
	 * @author ���¸�
	 * @since 2012.09.25
	 */
	@SuppressWarnings("unchecked")
	public List<FinanceStatic> selectFinanceStaticDayList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectFinanceStaticDayList", params);
	}
	
	
	/**
	 * @Method Name : selectfinanceStaticDayList
	 * @Description : �����纰 ������� �Ϻ����
	 * @param : HashMap<String ,Object>
	 * @return : List<FinanceStatic>
	 * @author ���¸�
	 * @since 2012.09.25
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> selectFinanceStaticMonthList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectFinanceStaticMonthList", params);
	}
	
	
}
