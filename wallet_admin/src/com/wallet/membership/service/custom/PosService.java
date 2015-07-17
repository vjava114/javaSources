package com.wallet.membership.service.custom;


import java.util.HashMap;
import java.util.List;

import com.wallet.membership.dao.custom.PosDao;
import com.wallet.membership.model.custom.Pos;

public class PosService {
	private final PosDao sDao;
	
	/**
	 * @Method Name : PosService
	 * @Description : »ý¼ºÀÚ
	 * @param : 
	 * @return : 
	 * @author ±è¿Ï¼·
	 * @since 2012.09.13
	 */
	public PosService() {
		sDao = new PosDao();
	}
	
	/**
	 * @Method Name : commit
	 * @Description : 
	 * @param : 
	 * @return : void
	 * @author ±è¿Ï¼·
	 * @since 2012.09.13
	 */
	public void commit(){
		sDao.commit();
	}
	
	/**
	 * @Method Name : rollback
	 * @Description : 
	 * @param : 
	 * @return : void
	 * @author ±è¿Ï¼·
	 * @since 2012.09.14
	 */
	public void rollback(){
		sDao.rollback();
	}
	
	/**
	 * @Method Name : selectPosList
	 * @Description : POS ¸ñ·Ï Á¶È¸
	 * @param : HashMap<String ,Object>
	 * @return : List<Pos>
	 * @author ±è¿Ï¼·
	 * @since 2012.09.13
	 */
	public List<Pos> selectPosList(HashMap<String ,Object> params) {
		List<Pos> result = null;
		
		result = sDao.selectPosList(params); //-- ÀÌº¥Æ®/°øÁö»çÇ× ¸ñ·ÏÀ» Á¶È¸ÇÔ.

		return result;
	}
	
	/**
	 * @Method Name : selectPosListCnt
	 * @Description : POS ¸ñ·Ï ¼ö Á¶È¸
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ±è¿Ï¼·
	 * @since 2012.09.13
	 */
	public int selectPosListCnt(HashMap<String, Object> params) {
		int result = 0;
		
		result = Integer.parseInt(sDao.selectPosListCnt(params).toString()); //-- ÀÌº¥Æ®/°øÁö»çÇ× ¸ñ·ÏÀ» Á¶È¸ÇÔ.

		return result;
	}

	
	/**
	 * @Method Name : selectAPos
	 * @Description : POS Á¦ÈÞ»ç Á¶È¸
	 * @param : HashMap<String ,Object>
	 * @return : List<Pos>
	 * @author ±è¿Ï¼·
	 * @since 2012.09.13
	 */
	@SuppressWarnings("unchecked")
	public Pos selectAPos(HashMap<String,Object> params){
		Pos aPos = sDao.selectAPos(params);
		
		return aPos;
	}
	
	/**
	 * @Method Name : insertPos
	 * @Description : POS µî·Ï
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ±è¿Ï¼·
	 * @since 2012.09.13
	 */
	public int insertPos(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.insertPos(params);
		
		return result;
	}
	
	/**
	 * @Method Name : updatePos
	 * @Description : POS ¼öÁ¤
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ±è¿Ï¼·
	 * @since 2012.09.13
	 */
	public int updatePos(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.updatePos(params);
		
		return result;
	}
	
	/**
	 * @Method Name : deletePos
	 * @Description : POS »èÁ¦
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ±è¿Ï¼·
	 * @since 2012.09.13
	 */
	public int deletePos(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.deletePos(params);
		
		return result;
	}
}

