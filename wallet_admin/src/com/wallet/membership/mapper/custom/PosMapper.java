package com.wallet.membership.mapper.custom;


import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.Pos;

public interface PosMapper {
	/**
	 * @Method Name : selectPosList
	 * @Description : POS ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<Pos>
	 * @author ��ϼ�
	 * @since 2012.09.13
	 */
	List<Pos> selectPosList(HashMap<String ,Object> params);
	
	
	/**
	 * @Method Name : selectPosListCnt
	 * @Description : POS ��� �� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ��ϼ�
	 * @since 2012.09.13
	 */
	Integer selectPosListCnt(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : insertPos
	 * @Description : POS ���
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ��ϼ�
	 * @since 2012.09.13
	 */
	Integer insertPos(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : updatePos
	 * @Description : POS ����
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ��ϼ�
	 * @since 2012.09.13
	 */
	Integer updatePos(HashMap<String ,Object> params);
}
