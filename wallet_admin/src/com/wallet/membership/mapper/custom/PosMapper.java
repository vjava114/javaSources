package com.wallet.membership.mapper.custom;


import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.Pos;

public interface PosMapper {
	/**
	 * @Method Name : selectPosList
	 * @Description : POS ¸ñ·Ï Á¶È¸
	 * @param : HashMap<String ,Object>
	 * @return : List<Pos>
	 * @author ±è¿Ï¼·
	 * @since 2012.09.13
	 */
	List<Pos> selectPosList(HashMap<String ,Object> params);
	
	
	/**
	 * @Method Name : selectPosListCnt
	 * @Description : POS ¸ñ·Ï ¼ö Á¶È¸
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ±è¿Ï¼·
	 * @since 2012.09.13
	 */
	Integer selectPosListCnt(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : insertPos
	 * @Description : POS µî·Ï
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ±è¿Ï¼·
	 * @since 2012.09.13
	 */
	Integer insertPos(HashMap<String ,Object> params);
	
	/**
	 * @Method Name : updatePos
	 * @Description : POS ¼öÁ¤
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ±è¿Ï¼·
	 * @since 2012.09.13
	 */
	Integer updatePos(HashMap<String ,Object> params);
}
