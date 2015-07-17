package com.wallet.membership.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.FinanceStatic;

public interface FinanceStaticMapper {
	/**
	 * @Method Name : selectfinanceStaticDayList
	 * @Description : �����纰 ������� �Ϻ����
	 * @param : HashMap<String ,Object>
	 * @return : List<FinanceStatic>
	 * @author ���¸�
	 * @since 2012.09.05
	 */
	List<FinanceStatic> selectFinanceStaticDayList(HashMap<String ,Object> params);

	/**
	 * @Method Name : selectFinanceStaticMonthList
	 * @Description : �����纰 ������� �������
	 * @param : HashMap<String ,Object>
	 * @return : List<FinanceStatic>
	 * @author ���¸�
	 * @since 2012.09.05
	 */
	List<HashMap<String, Object>> selectFinanceStaticMonthList(HashMap<String ,Object> params);
}
