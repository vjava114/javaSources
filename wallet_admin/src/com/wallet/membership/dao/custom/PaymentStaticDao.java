package com.wallet.membership.dao.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.membership.mapper.custom.FinanceStaticMapper;
import com.wallet.membership.mapper.custom.PaymentStaticMapper;
import com.wallet.membership.model.custom.FinanceStatic;
import com.wallet.membership.model.custom.PaymentStatic;

public class PaymentStaticDao  extends MybatisCilent implements PaymentStaticMapper {

	private String preMapperName = "com.wallet.membership.mapper.custom.PaymentStaticMapper.";
	
	/**
	 * @Method Name : commit
	 * @Description :
	 * @param : 
	 * @return : 
	 * @author 이정인
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
	 * @author 이정인
	 * @since 2012.09.14
	 */
	public void rollback(){
		sqlMapper.rollback();
	}
	
	/**
	 * @Method Name : 
	 * @Description : 생성자
	 * @param : 
	 * @return : 
	 * @author 이정인
	 * @since 2012.09.25
	 */
	public PaymentStaticDao() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @Method Name : 
	 * @Description : 결제사별 쿠폰사용 일별통계
	 * @param : HashMap<String ,Object>
	 * @return : List<FinanceStatic>
	 * @author 이정인
	 * @since 2012.09.25
	 */
	
	public List<PaymentStatic> selectPaymentStaticDayList(HashMap<String, Object> params) {
		// TODO Auto-generated method stub
		return sqlMapper.selectList(preMapperName + "selectPaymentStaticDayList", params);
	}

	public List<HashMap<String, Object>> selectPaymentStaticMonthList(HashMap<String, Object> params) {
		// TODO Auto-generated method stub
		return sqlMapper.selectList(preMapperName + "selectPaymentStaticMonthList", params);
	}
	
	
}
