package com.wallet.membership.dao.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.membership.mapper.custom.FinanceMapper;
import com.wallet.membership.model.custom.Finance;

public class FinanceDao extends MybatisCilent implements FinanceMapper {

	private String preMapperName = "com.wallet.membership.mapper.custom.FinanceMapper.";

	
	/**
	 * @Method Name : commit
	 * @Description :
	 * @param : 
	 * @return : 
	 * @author ���¸�
	 * @since 2012.09.07
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
	 * @Method Name : FinanceDao
	 * @Description : ������
	 * @param : 
	 * @return : 
	 * @author ���¸�
	 * @since 2012.09.07
	 */
	public FinanceDao() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @Method Name : selectFinanceList
	 * @Description : ������ ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<Finance>
	 * @author ���¸�
	 * @since 2012.09.07
	 */
	@SuppressWarnings("unchecked")
	public List<Finance> selectFinanceList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectFinanceList", params);
	}
	
	
	/**
	 * @Method Name : selectFinanceListCnt
	 * @Description : ������ ��� �� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.07
	 */
	@SuppressWarnings("unchecked")
	public Integer selectFinanceListCnt(HashMap<String,Object> params){
		Integer cnt = 0;
		
		cnt = (Integer) sqlMapper.selectOne(preMapperName + "selectFinanceListCnt", params);
		return cnt;
	}

	
	/**
	 * @Method Name : selectAFinance
	 * @Description : ������ ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<Finance>
	 * @author ���¸�
	 * @since 2012.09.07
	 */
	@SuppressWarnings("unchecked")
	public Finance selectAFinance(HashMap<String,Object> params){
		return (Finance) sqlMapper.selectOne(preMapperName + "selectFinanceInfo", params);
	}
	
	
	/**
	 * @Method Name : insertFinance
	 * @Description : ������ ���
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.07
	 */
	@SuppressWarnings("unchecked")
	public Integer insertFinance(HashMap<String,Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.insert(preMapperName + "insertFinance", params));
		return result;
	}
	
	
	/**
	 * @Method Name : updateFinance
	 * @Description : ������ ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.07
	 */
	@SuppressWarnings("unchecked")
	public Integer updateFinance(HashMap<String,Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.update(preMapperName + "updateFinance", params));
		return result;
	}
	
	/**
	 * @Method Name : updateFinance
	 * @Description : ������ ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.07
	 */
	@SuppressWarnings("unchecked")
	public Integer deleteFinance(HashMap<String, Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.delete(preMapperName + "deleteFinance", params));
		return result;
	}
}
