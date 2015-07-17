package com.wallet.membership.service.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.dao.custom.FinanceStaticDao;
import com.wallet.membership.model.custom.FinanceStatic;

public class FinanceStaticService {
	private final FinanceStaticDao sDao;

	/**
	 * @Method Name : FinanceStaticService
	 * @Description : ������
	 * @param : 
	 * @return : 
	 * @author ���¸�
	 * @since 2012.09.25
	 */
	public FinanceStaticService() {
		sDao = new FinanceStaticDao();
	}
	
	/**
	 * @Method Name : commit
	 * @Description : 
	 * @param : 
	 * @return : void
	 * @author ���¸�
	 * @since 2012.09.25
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
	 * @Method Name : selectFinanceStaticDayList
	 * @Description : �����纰_������� �Ϻ� ���
	 * @param : HashMap<String ,Object>
	 * @return : List<FinanceStatic>
	 * @author ���¸�
	 * @since 2012.09.25
	 */
	public List<FinanceStatic> selectFinanceStaticDayList(HashMap<String ,Object> params) {
		List<FinanceStatic> result = null;
		
		result = sDao.selectFinanceStaticDayList(params); //-- �̺�Ʈ/�������� ����� ��ȸ��.

		return result;
	}

	
	public List<HashMap<String, Object>> selectFinanceStaticMonthList(HashMap<String, Object> params){
		List<HashMap<String, Object>> result = null;
		
		result = sDao.selectFinanceStaticMonthList(params);
		
		return result;
				
	}
}
