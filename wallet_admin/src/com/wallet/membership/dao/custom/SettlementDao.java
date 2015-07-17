package com.wallet.membership.dao.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.common.util.MybatisCilent;
import com.wallet.membership.mapper.custom.SettlementMapper;
import com.wallet.membership.model.custom.Settlement;

public class SettlementDao extends MybatisCilent implements SettlementMapper {

	private String preMapperName = "com.wallet.membership.mapper.custom.SettlementMapper.";

	
	/**
	 * @Method Name : commit
	 * @Description :
	 * @param : 
	 * @return : 
	 * @author 김태리
	 * @since 2012.09.05
	 */
	public void commit() {
		sqlMapper.commit();
	}
	
	/**
	 * @Method Name : rollback
	 * @Description :
	 * @param : 
	 * @return : 
	 * @author 김태리
	 * @since 2012.09.14
	 */
	public void rollback(){
		sqlMapper.rollback();
	}
	
	/**
	 * @Method Name : SettlementDao
	 * @Description : 생성자
	 * @param : 
	 * @return : 
	 * @author 김태리
	 * @since 2012.09.05
	 */
	public SettlementDao() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @Method Name : selectSettlementList
	 * @Description : 결제사 목록 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<Settlement>
	 * @author 김태리
	 * @since 2012.09.05
	 */
	@SuppressWarnings("unchecked")
	public List<Settlement> selectSettlementList(HashMap<String,Object> params){
		return sqlMapper.selectList(preMapperName + "selectSettlementList", params);
	}
	
	
	/**
	 * @Method Name : selectSettlementListCnt
	 * @Description : 결제사 목록 수 조회
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2012.09.05
	 */
	@SuppressWarnings("unchecked")
	public Integer selectSettlementListCnt(HashMap<String,Object> params){
		Integer cnt = 0;
		
		cnt = (Integer) sqlMapper.selectOne(preMapperName + "selectSettlementListCnt", params);
		return cnt;
	}

	
	/**
	 * @Method Name : selectASettlement
	 * @Description : 결제사 조회
	 * @param : HashMap<String ,Object>
	 * @return : List<Settlement>
	 * @author 김태리
	 * @since 2012.09.06
	 */
	@SuppressWarnings("unchecked")
	public Settlement selectASettlement(HashMap<String,Object> params){
		return (Settlement) sqlMapper.selectOne(preMapperName + "selectSettlementInfo", params);
	}
	
	
	/**
	 * @Method Name : insertSettlement
	 * @Description : 결제사 등록
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2012.09.06
	 */
	@SuppressWarnings("unchecked")
	public Integer insertSettlement(HashMap<String,Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.insert(preMapperName + "insertSettlement", params));
		return result;
	}
	
	
	/**
	 * @Method Name : updateSettlement
	 * @Description : 결제사 수정
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2012.09.06
	 */
	@SuppressWarnings("unchecked")
	public Integer updateSettlement(HashMap<String,Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.update(preMapperName + "updateSettlement", params));
		return result;
	}
	
	/**
	 * @Method Name : updateSettlement
	 * @Description : 결제사 삭제
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2012.09.07
	 */
	@SuppressWarnings("unchecked")
	public Integer deleteSettlement(HashMap<String, Object> params){
		Integer result = 0;
		result = new Integer(sqlMapper.delete(preMapperName + "deleteSettlement", params));
		return result;
	}
}
