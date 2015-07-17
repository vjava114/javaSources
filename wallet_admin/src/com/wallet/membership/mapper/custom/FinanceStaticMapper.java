package com.wallet.membership.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.FinanceStatic;

public interface FinanceStaticMapper {
	/**
	 * @Method Name : selectfinanceStaticDayList
	 * @Description : 금융사별 쿠폰사용 일별통계
	 * @param : HashMap<String ,Object>
	 * @return : List<FinanceStatic>
	 * @author 김태리
	 * @since 2012.09.05
	 */
	List<FinanceStatic> selectFinanceStaticDayList(HashMap<String ,Object> params);

	/**
	 * @Method Name : selectFinanceStaticMonthList
	 * @Description : 금융사별 쿠폰사용 월별통계
	 * @param : HashMap<String ,Object>
	 * @return : List<FinanceStatic>
	 * @author 김태리
	 * @since 2012.09.05
	 */
	List<HashMap<String, Object>> selectFinanceStaticMonthList(HashMap<String ,Object> params);
}
