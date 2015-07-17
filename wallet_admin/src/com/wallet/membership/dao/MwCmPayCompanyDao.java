/*
 * 
 */
package com.wallet.membership.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wallet.common.util.MybatisCilent;
import com.wallet.membership.mapper.MwCmPayCompanyMapper;
import com.wallet.membership.model.MwCmPayCompany;
import com.wallet.membership.model.MwCmPayCompanyExample;
import com.wallet.membership.model.MwCmPayCompanyWithBLOBs;

/**
 * 
 *
 */
public class MwCmPayCompanyDao extends MybatisCilent implements MwCmPayCompanyMapper {

	public void rollback(){
		sqlMapper.rollback();
	}
	
	
	public int countByExample(MwCmPayCompanyExample example) {
		return (Integer) sqlMapper.selectOne("com.wallet.membership.mapper.MwCmPayCompanyMapper.countByExample", example);
	}

	
	public int deleteByExample(MwCmPayCompanyExample example) {
		return sqlMapper.delete("com.wallet.membership.mapper.MwCmPayCompanyMapper.deleteByExample", example);
	}

	
	@Delete({ "delete from MW_CM_PAYCOMPANY",
			"where PAYCOMP_ID = #{paycompId,jdbcType=VARCHAR}" })
	public int deleteByPrimaryKey(String paycompId) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	@Insert({
			"insert into MW_CM_PAYCOMPANY (PAYCOMP_ID, PAYCOMP_NAME, ",
			"ALLY_STAT, BUSINESS_NO, ",
			"ZIPCODE, ADDR, ADDR_DETAIL, ",
			"MAIN_NUMBER, PHONE_NO, ",
			"MOBILE_PHONE, EMAIL, ",
			"REG_USER, REG_DTM, ",
			"CHG_USER, CHG_DTM, ",
			"MANAGER_NAME, DC_METHOD_INFO, ",
			"MEMO)",
			"values (#{paycompId,jdbcType=VARCHAR}, #{paycompName,jdbcType=VARCHAR}, ",
			"#{allyStat,jdbcType=CHAR}, #{businessNo,jdbcType=VARCHAR}, ",
			"#{zipcode,jdbcType=VARCHAR}, #{addr,jdbcType=VARCHAR}, #{addrDetail,jdbcType=VARCHAR}, ",
			"#{mainNumber,jdbcType=VARCHAR}, #{phoneNo,jdbcType=VARCHAR}, ",
			"#{mobilePhone,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR}, ",
			"#{regUser,jdbcType=VARCHAR}, #{regDtm,jdbcType=TIMESTAMP}, ",
			"#{chgUser,jdbcType=VARCHAR}, #{chgDtm,jdbcType=TIMESTAMP}, ",
			"#{managerName,jdbcType=VARCHAR}, #{dcMethodInfo,jdbcType=LONGVARCHAR}, ",
			"#{memo,jdbcType=LONGVARCHAR})" })
	public int insert(MwCmPayCompanyWithBLOBs record) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int insertSelective(MwCmPayCompanyWithBLOBs record) {
		return sqlMapper.insert("com.wallet.membership.mapper.MwCmPayCompanyMapper.insertSelective", record);
	}

	
	public List<MwCmPayCompanyWithBLOBs> selectByExampleWithBLOBs(
			MwCmPayCompanyExample example) {
		return sqlMapper.selectList("com.wallet.membership.mapper.MwCmPayCompanyMapper.selectByExampleWithBLOBs", example);
	}

	
	public List<MwCmPayCompany> selectByExample(MwCmPayCompanyExample example) {
		return sqlMapper.selectList("com.wallet.membership.mapper.MwCmPayCompanyMapper.selectByExample", example);
	}

	
	@Select({
			"select",
			"PAYCOMP_ID, PAYCOMP_NAME, ALLY_STAT, BUSINESS_NO, ZIPCODE, ADDR, ADDR_DETAIL, ",
			"MAIN_NUMBER, PHONE_NO, MOBILE_PHONE, EMAIL, REG_USER, REG_DTM, CHG_USER, CHG_DTM, ",
			"MANAGER_NAME, DC_METHOD_INFO, MEMO", "from MW_CM_PAYCOMPANY",
			"where PAYCOMP_ID = #{paycompId,jdbcType=VARCHAR}" })
	@ResultMap("ResultMapWithBLOBs")
	public MwCmPayCompanyWithBLOBs selectByPrimaryKey(String paycompId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public int updateByExampleSelective(
			@Param("record") MwCmPayCompanyWithBLOBs record,
			@Param("example") MwCmPayCompanyExample example) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int updateByExampleWithBLOBs(
			@Param("record") MwCmPayCompanyWithBLOBs record,
			@Param("example") MwCmPayCompanyExample example) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int updateByExample(@Param("record") MwCmPayCompany record,
			@Param("example") MwCmPayCompanyExample example) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("record", record);
		param.put("example", example);
		return sqlMapper.update("com.wallet.membership.mapper.MwCmPayCompanyMapper.updateByExample", param);
	}

	
	public int updateByPrimaryKeySelective(MwCmPayCompanyWithBLOBs record) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	@Update({ "update MW_CM_PAYCOMPANY",
			"set PAYCOMP_NAME = #{paycompName,jdbcType=VARCHAR},",
			"ALLY_STAT = #{allyStat,jdbcType=CHAR},",
			"BUSINESS_NO = #{businessNo,jdbcType=VARCHAR},",
			"ZIPCODE = #{zipcode,jdbcType=VARCHAR},",
			"ADDR = #{addr,jdbcType=VARCHAR},",
			"ADDR_DETAIL = #{addrDetail,jdbcType=VARCHAR},",
			"MAIN_NUMBER = #{mainNumber,jdbcType=VARCHAR},",
			"PHONE_NO = #{phoneNo,jdbcType=VARCHAR},",
			"MOBILE_PHONE = #{mobilePhone,jdbcType=VARCHAR},",
			"EMAIL = #{email,jdbcType=VARCHAR},",
			"REG_USER = #{regUser,jdbcType=VARCHAR},",
			"REG_DTM = #{regDtm,jdbcType=TIMESTAMP},",
			"CHG_USER = #{chgUser,jdbcType=VARCHAR},",
			"CHG_DTM = #{chgDtm,jdbcType=TIMESTAMP},",
			"MANAGER_NAME = #{managerName,jdbcType=VARCHAR},",
			"DC_METHOD_INFO = #{dcMethodInfo,jdbcType=LONGVARCHAR},",
			"MEMO = #{memo,jdbcType=LONGVARCHAR}",
			"where PAYCOMP_ID = #{paycompId,jdbcType=VARCHAR}" })
	public int updateByPrimaryKeyWithBLOBs(MwCmPayCompanyWithBLOBs record) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	@Update({ "update MW_CM_PAYCOMPANY",
			"set PAYCOMP_NAME = #{paycompName,jdbcType=VARCHAR},",
			"ALLY_STAT = #{allyStat,jdbcType=CHAR},",
			"BUSINESS_NO = #{businessNo,jdbcType=VARCHAR},",
			"ZIPCODE = #{zipcode,jdbcType=VARCHAR},",
			"ADDR = #{addr,jdbcType=VARCHAR},",
			"ADDR_DETAIL = #{addrDetail,jdbcType=VARCHAR},",
			"MAIN_NUMBER = #{mainNumber,jdbcType=VARCHAR},",
			"PHONE_NO = #{phoneNo,jdbcType=VARCHAR},",
			"MOBILE_PHONE = #{mobilePhone,jdbcType=VARCHAR},",
			"EMAIL = #{email,jdbcType=VARCHAR},",
			"REG_USER = #{regUser,jdbcType=VARCHAR},",
			"REG_DTM = #{regDtm,jdbcType=TIMESTAMP},",
			"CHG_USER = #{chgUser,jdbcType=VARCHAR},",
			"CHG_DTM = #{chgDtm,jdbcType=TIMESTAMP},",
			"MANAGER_NAME = #{managerName,jdbcType=VARCHAR}",
			"where PAYCOMP_ID = #{paycompId,jdbcType=VARCHAR}" })
	public int updateByPrimaryKey(MwCmPayCompany record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void commit() {
		sqlMapper.commit();
	}

}
