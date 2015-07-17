/*
 * 
 */
package com.wallet.membership.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.wallet.common.util.MybatisCilent;
import com.wallet.membership.mapper.CpnBankInfoMapper;
import com.wallet.membership.model.CpnBankInfo;
import com.wallet.membership.model.CpnBankInfoExample;
import com.wallet.membership.model.CpnBankInfoWithBLOBs;

/**
 * 
 *
 */
public class CpnBankInfoDao extends MybatisCilent implements CpnBankInfoMapper {


	public void commit() {
		sqlMapper.commit();
	}
	
	public void rollback(){
		sqlMapper.rollback();
	}

	
	public int countByExample(CpnBankInfoExample example) {
		return (Integer) sqlMapper.selectOne("com.wallet.membership.mapper.CpnBankInfoMapper.countByExample", example);
	}

	
	public int deleteByExample(CpnBankInfoExample example) {
		return sqlMapper.delete("com.wallet.membership.mapper.CpnBankInfoMapper.deleteByExample", example);
	}

	
	@Insert({
			"insert into cpn_bank_info (bank_id, name, ",
			"svc_1, svc_2, svc_3, ",
			"svc_4, BUSINESS_NO, ",
			"ZIPCODE, ADDR, ADDR_DETAIL, ",
			"MAIN_NUMBER, MANAGER_NAME, ",
			"PHONE_NO, MOBILE_PHONE, ",
			"EMAIL, ALLY_STAT, REG_USER, ",
			"REG_DTM, CHG_USER, ",
			"CHG_DTM, DC_METHOD_INFO, ",
			"MEMO)",
			"values (#{bankId,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
			"#{svc1,jdbcType=VARCHAR}, #{svc2,jdbcType=VARCHAR}, #{svc3,jdbcType=VARCHAR}, ",
			"#{svc4,jdbcType=VARCHAR}, #{businessNo,jdbcType=VARCHAR}, ",
			"#{zipcode,jdbcType=VARCHAR}, #{addr,jdbcType=VARCHAR}, #{addrDetail,jdbcType=VARCHAR}, ",
			"#{mainNumber,jdbcType=VARCHAR}, #{managerName,jdbcType=VARCHAR}, ",
			"#{phoneNo,jdbcType=VARCHAR}, #{mobilePhone,jdbcType=VARCHAR}, ",
			"#{email,jdbcType=VARCHAR}, #{allyStat,jdbcType=CHAR}, #{regUser,jdbcType=VARCHAR}, ",
			"#{regDtm,jdbcType=TIMESTAMP}, #{chgUser,jdbcType=VARCHAR}, ",
			"#{chgDtm,jdbcType=TIMESTAMP}, #{dcMethodInfo,jdbcType=LONGVARCHAR}, ",
			"#{memo,jdbcType=LONGVARCHAR})" })
	public int insert(CpnBankInfoWithBLOBs record) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int insertSelective(CpnBankInfoWithBLOBs record) {
		return sqlMapper.insert("com.wallet.membership.mapper.CpnBankInfoMapper.insertSelective", record);
	}

	
	public List<CpnBankInfoWithBLOBs> selectByExampleWithBLOBs(
			CpnBankInfoExample example) {
		return sqlMapper.selectList("com.wallet.membership.mapper.CpnBankInfoMapper.selectByExampleWithBLOBs", example);
	}

	
	public List<CpnBankInfo> selectByExample(CpnBankInfoExample example) {
		return sqlMapper.selectList("com.wallet.membership.mapper.CpnBankInfoMapper.selectByExample", example);
	}

	
	public int updateByExampleSelective(
			@Param("record") CpnBankInfoWithBLOBs record,
			@Param("example") CpnBankInfoExample example) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int updateByExampleWithBLOBs(
			@Param("record") CpnBankInfoWithBLOBs record,
			@Param("example") CpnBankInfoExample example) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int updateByExample(@Param("record") CpnBankInfo record,
			@Param("example") CpnBankInfoExample example) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("record", record);
		param.put("example", example);
		return sqlMapper.update("com.wallet.membership.mapper.CpnBankInfoMapper.updateByExample", param);
	}

}
