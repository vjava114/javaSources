package com.wallet.membership.service.custom;


import java.util.HashMap;
import java.util.List;

import com.wallet.membership.dao.custom.PosDao;
import com.wallet.membership.model.custom.Pos;

public class PosService {
	private final PosDao sDao;
	
	/**
	 * @Method Name : PosService
	 * @Description : ������
	 * @param : 
	 * @return : 
	 * @author ��ϼ�
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
	 * @author ��ϼ�
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
	 * @author ��ϼ�
	 * @since 2012.09.14
	 */
	public void rollback(){
		sDao.rollback();
	}
	
	/**
	 * @Method Name : selectPosList
	 * @Description : POS ��� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<Pos>
	 * @author ��ϼ�
	 * @since 2012.09.13
	 */
	public List<Pos> selectPosList(HashMap<String ,Object> params) {
		List<Pos> result = null;
		
		result = sDao.selectPosList(params); //-- �̺�Ʈ/�������� ����� ��ȸ��.

		return result;
	}
	
	/**
	 * @Method Name : selectPosListCnt
	 * @Description : POS ��� �� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ��ϼ�
	 * @since 2012.09.13
	 */
	public int selectPosListCnt(HashMap<String, Object> params) {
		int result = 0;
		
		result = Integer.parseInt(sDao.selectPosListCnt(params).toString()); //-- �̺�Ʈ/�������� ����� ��ȸ��.

		return result;
	}

	
	/**
	 * @Method Name : selectAPos
	 * @Description : POS ���޻� ��ȸ
	 * @param : HashMap<String ,Object>
	 * @return : List<Pos>
	 * @author ��ϼ�
	 * @since 2012.09.13
	 */
	@SuppressWarnings("unchecked")
	public Pos selectAPos(HashMap<String,Object> params){
		Pos aPos = sDao.selectAPos(params);
		
		return aPos;
	}
	
	/**
	 * @Method Name : insertPos
	 * @Description : POS ���
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ��ϼ�
	 * @since 2012.09.13
	 */
	public int insertPos(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.insertPos(params);
		
		return result;
	}
	
	/**
	 * @Method Name : updatePos
	 * @Description : POS ����
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ��ϼ�
	 * @since 2012.09.13
	 */
	public int updatePos(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.updatePos(params);
		
		return result;
	}
	
	/**
	 * @Method Name : deletePos
	 * @Description : POS ����
	 * @param : HashMap<String ,Object>
	 * @return : int
	 * @author ��ϼ�
	 * @since 2012.09.13
	 */
	public int deletePos(HashMap<String, Object> params){
		int result = 0;
		
		result = sDao.deletePos(params);
		
		return result;
	}
}

