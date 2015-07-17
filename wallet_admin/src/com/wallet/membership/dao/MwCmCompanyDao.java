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
import com.wallet.membership.mapper.MwCmCompanyMapper;
import com.wallet.membership.model.MwCmCode;
import com.wallet.membership.model.MwCmCodeExample;
import com.wallet.membership.model.MwCmCodeKey;
import com.wallet.membership.model.MwCmCodeWithBLOBs;
import com.wallet.membership.model.MwCmCompany;
import com.wallet.membership.model.MwCmCompanyExample;

/**
 * 
 *
 */
public class MwCmCompanyDao extends MybatisCilent implements MwCmCompanyMapper {
	
	public void rollback(){
		sqlMapper.rollback();
	}
	
	public int countByExample(MwCmCompanyExample example) {
		return (Integer) sqlMapper.selectOne("com.wallet.membership.mapper.MwCmCompanyMapper.countByExample", example);
	}

	
	public int deleteByExample(MwCmCompanyExample example) {
		return sqlMapper.delete("com.wallet.membership.mapper.MwCmCompanyMapper.deleteByExample", example);
	}

	
	@Delete({ "delete from MW_CM_COMPANY",
			"where COMP_ID = #{compId,jdbcType=VARCHAR}" })
	public int deleteByPrimaryKey(String compId) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	@Insert({
			"insert into MW_CM_COMPANY (COMP_ID, COMP_NAME, ",
			"ALLY_STAT, UPPER_COMP_ID, ",
			"COMP_LEVEL_TYPE, COMP_TYPE, ",
			"CPN_RTIME_ISSUE_YN, COMPLEX_PAYMENT_YN, ",
			"ZIPCODE, ADDR, ADDR_DETAIL, ",
			"MAIN_NUMBER, PHONE_NO, ",
			"MOBILE_PHONE, EMAIL, ",
			"LIMIT_AMOUNT_DAY, LIMIT_AMOUNT_WEEK, ",
			"LIMIT_AMOUNT_MONTH, LIMIT_AMOUNT_YEAR, ",
			"BUSINESS_NO, GPS_X, ",
			"GPS_Y, SHOP_INFO_URL, ",
			"COMP_SHOP_ID, MEMB_ID, ",
			"REG_USER, REG_DTM, ",
			"CHG_USER, CHG_DTM, ",
			"REGION_TYPE, MEMO)",
			"values (#{compId,jdbcType=VARCHAR}, #{compName,jdbcType=VARCHAR}, ",
			"#{allyStat,jdbcType=CHAR}, #{upperCompId,jdbcType=VARCHAR}, ",
			"#{compLevelType,jdbcType=CHAR}, #{compType,jdbcType=CHAR}, ",
			"#{cpnRtimeIssueYn,jdbcType=CHAR}, #{complexPaymentYn,jdbcType=CHAR}, ",
			"#{compPayMembYn,jdbcType=CHAR}, #{compPayMembSave,jdbcType=CHAR}, ",
			"#{compPayMembUse,jdbcType=CHAR}, #{compPayMembDc,jdbcType=CHAR}, ",
			"#{compPayCpnYn,jdbcType=CHAR}, #{compPayStampYn,jdbcType=CHAR}, ",
			"#{zipcode,jdbcType=VARCHAR}, #{addr,jdbcType=VARCHAR}, #{addrDetail,jdbcType=VARCHAR}, ",
			"#{mainNumber,jdbcType=VARCHAR}, #{phoneNo,jdbcType=VARCHAR}, ",
			"#{mobilePhone,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR}, ",
			"#{limitAmountDay,jdbcType=INTEGER}, #{limitAmountWeek,jdbcType=INTEGER}, ",
			"#{limitAmountMonth,jdbcType=INTEGER}, #{limitAmountYear,jdbcType=INTEGER}, ",
			"#{businessNo,jdbcType=VARCHAR}, #{gpsX,jdbcType=VARCHAR}, ",
			"#{gpsY,jdbcType=VARCHAR}, #{shopInfoUrl,jdbcType=VARCHAR}, ",
			"#{compShopId,jdbcType=VARCHAR}, #{membId,jdbcType=VARCHAR}, ",
			"#{regUser,jdbcType=VARCHAR}, #{regDtm,jdbcType=TIMESTAMP}, ",
			"#{chgUser,jdbcType=VARCHAR}, #{chgDtm,jdbcType=TIMESTAMP}, ",
			"#{regionType,jdbcType=CHAR}, #{memo,jdbcType=LONGVARCHAR})" })
	public int insert(MwCmCompany record) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int insertSelective(MwCmCompany record) {
		return sqlMapper.insert("com.wallet.membership.mapper.MwCmCompanyMapper.insertSelective", record);
	}
	
	
	public int partnerInsertSelective(MwCmCompany record) {
		return sqlMapper.insert("com.wallet.membership.mapper.MwCmCompanyMapper.partnerInsertSelective", record);
	}

	
	public List<MwCmCompany> selectByExampleWithBLOBs(MwCmCompanyExample example) {
		return sqlMapper.selectList("com.wallet.membership.mapper.MwCmCompanyMapper.selectByExampleWithBLOBs", example);
	}

	
	public List<MwCmCompany> selectByExample(MwCmCompanyExample example) {
		return sqlMapper.selectList("com.wallet.membership.mapper.MwCmCompanyMapper.selectByExample", example);
	}

	
	@Select({
			"select",
			"COMP_ID, COMP_NAME, ALLY_STAT, UPPER_COMP_ID, COMP_LEVEL_TYPE, COMP_TYPE, CPN_RTIME_ISSUE_YN, ",
			"COMPLEX_PAYMENT_YN, ZIPCODE, ADDR, ADDR_DETAIL, ",
			"MAIN_NUMBER, PHONE_NO, MOBILE_PHONE, EMAIL, LIMIT_AMOUNT_DAY, LIMIT_AMOUNT_WEEK, ",
			"LIMIT_AMOUNT_MONTH, LIMIT_AMOUNT_YEAR, BUSINESS_NO, GPS_X, GPS_Y, SHOP_INFO_URL, ",
			"COMP_SHOP_ID, MEMB_ID, REG_USER, REG_DTM, CHG_USER, CHG_DTM, REGION_TYPE, MEMO",
			"from MW_CM_COMPANY", "where COMP_ID = #{compId,jdbcType=VARCHAR}" })
	@ResultMap("ResultMapWithBLOBs")
	public MwCmCompany selectByPrimaryKey(String compId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public int updateByExampleSelective(@Param("record") MwCmCompany record,
			@Param("example") MwCmCompanyExample example) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("record", record);
		param.put("example", example);
		return sqlMapper.update("com.wallet.membership.mapper.MwCmCompanyMapper.updateByExampleSelective", param);
	}

	
	public int updateByExampleWithBLOBs(@Param("record") MwCmCompany record,
			@Param("example") MwCmCompanyExample example) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int updateByExample(@Param("record") MwCmCompany record,
			@Param("example") MwCmCompanyExample example) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("record", record);
		param.put("example", example);
		return sqlMapper.update("com.wallet.membership.mapper.MwCmCompanyMapper.updateByExample", param);

	}

	
	public int updateByPrimaryKeySelective(MwCmCompany record) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	@Update({ "update MW_CM_COMPANY",
			"set COMP_NAME = #{compName,jdbcType=VARCHAR},",
			"ALLY_STAT = #{allyStat,jdbcType=CHAR},",
			"UPPER_COMP_ID = #{upperCompId,jdbcType=VARCHAR},",
			"COMP_LEVEL_TYPE = #{compLevelType,jdbcType=CHAR},",
			"COMP_TYPE = #{compType,jdbcType=CHAR},",
			"CPN_RTIME_ISSUE_YN = #{cpnRtimeIssueYn,jdbcType=CHAR},",
			"COMPLEX_PAYMENT_YN = #{complexPaymentYn,jdbcType=CHAR},",
			"ZIPCODE = #{zipcode,jdbcType=VARCHAR},",
			"ADDR = #{addr,jdbcType=VARCHAR},",
			"ADDR_DETAIL = #{addrDetail,jdbcType=VARCHAR},",
			"MAIN_NUMBER = #{mainNumber,jdbcType=VARCHAR},",
			"PHONE_NO = #{phoneNo,jdbcType=VARCHAR},",
			"MOBILE_PHONE = #{mobilePhone,jdbcType=VARCHAR},",
			"EMAIL = #{email,jdbcType=VARCHAR},",
			"LIMIT_AMOUNT_DAY = #{limitAmountDay,jdbcType=INTEGER},",
			"LIMIT_AMOUNT_WEEK = #{limitAmountWeek,jdbcType=INTEGER},",
			"LIMIT_AMOUNT_MONTH = #{limitAmountMonth,jdbcType=INTEGER},",
			"LIMIT_AMOUNT_YEAR = #{limitAmountYear,jdbcType=INTEGER},",
			"BUSINESS_NO = #{businessNo,jdbcType=VARCHAR},",
			"GPS_X = #{gpsX,jdbcType=VARCHAR},", "GPS_Y = #{gpsY,jdbcType=VARCHAR},",
			"SHOP_INFO_URL = #{shopInfoUrl,jdbcType=VARCHAR},",
			"COMP_SHOP_ID = #{compShopId,jdbcType=VARCHAR},",
			"MEMB_ID = #{membId,jdbcType=VARCHAR},",
			"REG_USER = #{regUser,jdbcType=VARCHAR},",
			"REG_DTM = #{regDtm,jdbcType=TIMESTAMP},",
			"CHG_USER = #{chgUser,jdbcType=VARCHAR},",
			"CHG_DTM = #{chgDtm,jdbcType=TIMESTAMP},",
			"REGION_TYPE = #{regionType,jdbcType=CHAR},",
			"MEMO = #{memo,jdbcType=LONGVARCHAR}",
			"where COMP_ID = #{compId,jdbcType=VARCHAR}" })
	public int updateByPrimaryKeyWithBLOBs(MwCmCompany record) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	@Update({ "update MW_CM_COMPANY",
			"set COMP_NAME = #{compName,jdbcType=VARCHAR},",
			"ALLY_STAT = #{allyStat,jdbcType=CHAR},",
			"UPPER_COMP_ID = #{upperCompId,jdbcType=VARCHAR},",
			"COMP_LEVEL_TYPE = #{compLevelType,jdbcType=CHAR},",
			"COMP_TYPE = #{compType,jdbcType=CHAR},",
			"CPN_RTIME_ISSUE_YN = #{cpnRtimeIssueYn,jdbcType=CHAR},",
			"COMPLEX_PAYMENT_YN = #{complexPaymentYn,jdbcType=CHAR},",
			"ZIPCODE = #{zipcode,jdbcType=VARCHAR},",
			"ADDR = #{addr,jdbcType=VARCHAR},",
			"ADDR_DETAIL = #{addrDetail,jdbcType=VARCHAR},",
			"MAIN_NUMBER = #{mainNumber,jdbcType=VARCHAR},",
			"PHONE_NO = #{phoneNo,jdbcType=VARCHAR},",
			"MOBILE_PHONE = #{mobilePhone,jdbcType=VARCHAR},",
			"EMAIL = #{email,jdbcType=VARCHAR},",
			"LIMIT_AMOUNT_DAY = #{limitAmountDay,jdbcType=INTEGER},",
			"LIMIT_AMOUNT_WEEK = #{limitAmountWeek,jdbcType=INTEGER},",
			"LIMIT_AMOUNT_MONTH = #{limitAmountMonth,jdbcType=INTEGER},",
			"LIMIT_AMOUNT_YEAR = #{limitAmountYear,jdbcType=INTEGER},",
			"BUSINESS_NO = #{businessNo,jdbcType=VARCHAR},",
			"GPS_X = #{gpsX,jdbcType=VARCHAR},", "GPS_Y = #{gpsY,jdbcType=VARCHAR},",
			"SHOP_INFO_URL = #{shopInfoUrl,jdbcType=VARCHAR},",
			"COMP_SHOP_ID = #{compShopId,jdbcType=VARCHAR},",
			"MEMB_ID = #{membId,jdbcType=VARCHAR},",
			"REG_USER = #{regUser,jdbcType=VARCHAR},",
			"REG_DTM = #{regDtm,jdbcType=TIMESTAMP},",
			"CHG_USER = #{chgUser,jdbcType=VARCHAR},",
			"CHG_DTM = #{chgDtm,jdbcType=TIMESTAMP},",
			"REGION_TYPE = #{regionType,jdbcType=CHAR}",
			"where COMP_ID = #{compId,jdbcType=VARCHAR}" })
	public int updateByPrimaryKey(MwCmCompany record) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public void commit() {
		sqlMapper.commit();
	}

	
	public List<MwCmCompany> MemberPatnerSelectByExample(
			MwCmCompanyExample example) {
		// TODO Auto-generated method stub
		return sqlMapper.selectList("com.wallet.membership.mapper.MwCmCompanyMapper.MemberPatnerSelectByExample", example);
	}

	
	public int storeInsertSelective(MwCmCompany record) {
		// TODO Auto-generated method stub
		return sqlMapper.insert("com.wallet.membership.mapper.MwCmCompanyMapper.storeInsertSelective", record);
	}

	
	public List<MwCmCompany> MemberStoreSelectByExample(MwCmCompanyExample example) {
		// TODO Auto-generated method stub
		return sqlMapper.selectList("com.wallet.membership.mapper.MwCmCompanyMapper.MemberStoreSelectByExample", example);
	}

	
	public int FranchiseInsertSelective(MwCmCompany record) {
		// TODO Auto-generated method stub
		return sqlMapper.insert("com.wallet.membership.mapper.MwCmCompanyMapper.FranchiseInsertSelective", record);
	}

	
	public List<MwCmCompany> FranchiseSelectByExample(MwCmCompanyExample example) {
		// TODO Auto-generated method stub
		return sqlMapper.selectList("com.wallet.membership.mapper.MwCmCompanyMapper.FranchiseSelectByExample", example);
	}
	
	public List<MwCmCompany> nextShopId(MwCmCompanyExample example) {
		// TODO Auto-generated method stub
		return sqlMapper.selectList("com.wallet.membership.mapper.MwCmCompanyMapper.nextShopId", example);
	}
	
	public List<MwCmCompany> nextBrandId(MwCmCompanyExample example) {
		// TODO Auto-generated method stub
		return sqlMapper.selectList("com.wallet.membership.mapper.MwCmCompanyMapper.nextBrandId", example);
	}
	
	public int FranchiseSelectByCount(MwCmCompanyExample example) {
		// TODO Auto-generated method stub
		return (Integer) sqlMapper.selectOne("com.wallet.membership.mapper.MwCmCompanyMapper.FranchiseSelectByCount", example);
	}
	
	public int MemberStoreSelectByCount(MwCmCompanyExample example) {
		// TODO Auto-generated method stub
		return (Integer) sqlMapper.selectOne("com.wallet.membership.mapper.MwCmCompanyMapper.MemberStoreSelectByCount", example);
	}
	
	public int MemberPatnerSelectByCount(MwCmCompanyExample example) {
		// TODO Auto-generated method stub
		return (Integer) sqlMapper.selectOne("com.wallet.membership.mapper.MwCmCompanyMapper.MemberPatnerSelectByCount", example);
	}
}
