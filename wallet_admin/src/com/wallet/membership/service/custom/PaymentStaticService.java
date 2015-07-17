package com.wallet.membership.service.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.dao.custom.FinanceStaticDao;
import com.wallet.membership.dao.custom.PaymentStaticDao;
import com.wallet.membership.model.custom.FinanceStatic;
import com.wallet.membership.model.custom.PaymentStatic;

public class PaymentStaticService {
	private final PaymentStaticDao sDao;

	/**
	 * @Method Name : FinanceStaticService
	 * @Description : ������
	 * @param : 
	 * @return : 
	 * @author ������
	 * @since 2012.09.25
	 */
	public PaymentStaticService() {
		sDao = new PaymentStaticDao();
	}
	
	/**
	 * @Method Name : commit
	 * @Description : 
	 * @param : 
	 * @return : void
	 * @author ������
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
	 * @author ������
	 * @since 2012.09.14
	 */
	public void rollback(){
		sDao.rollback();
	}
	
	/**
	 * @Method Name : selectFinanceStaticDayList
	 * @Description : �����纰_������� �Ϻ� ���
	 * @param : HashMap<String ,Object>
	 * @return : List<PaymentStatic>
	 * @author ���¸�
	 * @since 2012.09.25
	 */
	public List<PaymentStatic> selectPaymentStaticDayList(HashMap<String ,Object> params) {
		List<PaymentStatic> result = null;
		
		result = sDao.selectPaymentStaticDayList(params); //-- �̺�Ʈ/�������� ����� ��ȸ��.

		return result;
	}

	
	public List<HashMap<String, Object>> selectPaymentStaticMonthList(HashMap<String, Object> params){
		List<HashMap<String, Object>> result = null;
		
		result = sDao.selectPaymentStaticMonthList(params);
		
		return result;
				
	}
}
