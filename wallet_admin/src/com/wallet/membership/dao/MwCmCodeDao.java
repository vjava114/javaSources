/*
 * 
 */
package com.wallet.membership.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wallet.common.util.MybatisCilent;
import com.wallet.membership.mapper.MwCmCodeMapper;
import com.wallet.membership.model.MwCmCode;
import com.wallet.membership.model.MwCmCodeExample;
import com.wallet.membership.model.MwCmCodeKey;
import com.wallet.membership.model.MwCmCodeWithBLOBs;

/**
 * 
 *
 */
public class MwCmCodeDao extends MybatisCilent implements MwCmCodeMapper {

	
	public void rollback(){
		sqlMapper.rollback();
	}
	
	
	public int countByExample(MwCmCodeExample example) {
		return (Integer) sqlMapper.selectOne("com.wallet.membership.mapper.MwCmCodeMapper.countByExample", example);
	}

	
	public int deleteByExample(MwCmCodeExample example) {
		return sqlMapper.delete("com.wallet.membership.mapper.MwCmCodeMapper.deleteByExample", example);
	}

	
	public int insert(MwCmCodeWithBLOBs record) {
		return sqlMapper.insert("com.wallet.membership.mapper.MwCmCodeMapper.insertSelective", record);
	}

	
	public int insertSelective(MwCmCodeWithBLOBs record) {
		return sqlMapper.insert("com.wallet.membership.mapper.MwCmCodeMapper.insertSelective", record);
	}

	
	public List<MwCmCode> selectByExample(MwCmCodeExample example) {
		return sqlMapper.selectList("com.wallet.membership.mapper.MwCmCodeMapper.selectByExample", example);
	}

	
	public int updateByExampleSelective(MwCmCodeWithBLOBs record,	MwCmCodeExample example) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("record", record);
		param.put("example", example);
		return sqlMapper.update("com.wallet.membership.mapper.MwCmCodeMapper.updateByExampleSelective", param);
	}

	
	public int updateByExample(MwCmCode record, MwCmCodeExample example) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("record", record);
		param.put("example", example);
		return sqlMapper.update("com.wallet.membership.mapper.MwCmCodeMapper.updateByExample", param);
	}
	
	public void commit() {
		sqlMapper.commit();
	}

	
	
	
	
	@Delete({ "delete from MW_CM_CODE",
			"where GRP_CODE = #{grpCode,jdbcType=VARCHAR}",
			"and COM_CD = #{comCd,jdbcType=VARCHAR}" })
	public int deleteByPrimaryKey(MwCmCodeKey key) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public List<MwCmCodeWithBLOBs> selectByExampleWithBLOBs(MwCmCodeExample example) {
		return sqlMapper.selectList("com.wallet.membership.mapper.MwCmCodeMapper.selectByExampleWithBLOBs", example);
	}

	
	@Select({
			"select",
			"GRP_CODE, COM_CD, COM_CD_VAL, SEQ_NO, USE_YN, REMARK1, REMARK2, REMARK3, REMARK4, ",
			"REG_USER, REG_DTM, CHG_USER, CHG_DTM, COM_CD_DESC, REMARK5",
			"from MW_CM_CODE", "where GRP_CODE = #{grpCode,jdbcType=VARCHAR}",
			"and COM_CD = #{comCd,jdbcType=VARCHAR}" })
	@ResultMap("ResultMapWithBLOBs")
	public	MwCmCodeWithBLOBs selectByPrimaryKey(MwCmCodeKey key) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public int updateByExampleWithBLOBs(
			@Param("record") MwCmCodeWithBLOBs record,
			@Param("example") MwCmCodeExample example) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("record", record);
		param.put("example", example);
		return sqlMapper.update("com.wallet.membership.mapper.MwCmCodeMapper.updateByExampleWithBLOBs", param);
	}

	
	public int updateByPrimaryKeySelective(MwCmCodeWithBLOBs record) {
		return sqlMapper.update("com.wallet.membership.mapper.MwCmCodeMapper.updateByPrimaryKeySelective", record);
	}

	
	@Update({ "update MW_CM_CODE",
			"set COM_CD_VAL = #{comCdVal,jdbcType=VARCHAR},",
			"SEQ_NO = #{seqNo,jdbcType=INTEGER},",
			"USE_YN = #{useYn,jdbcType=CHAR},",
			"REMARK1 = #{remark1,jdbcType=VARCHAR},",
			"REMARK2 = #{remark2,jdbcType=VARCHAR},",
			"REMARK3 = #{remark3,jdbcType=VARCHAR},",
			"REMARK4 = #{remark4,jdbcType=VARCHAR},",
			"REG_USER = #{regUser,jdbcType=VARCHAR},",
			"REG_DTM = #{regDtm,jdbcType=TIMESTAMP},",
			"CHG_USER = #{chgUser,jdbcType=VARCHAR},",
			"CHG_DTM = #{chgDtm,jdbcType=TIMESTAMP},",
			"COM_CD_DESC = #{comCdDesc,jdbcType=LONGVARCHAR},",
			"REMARK5 = #{remark5,jdbcType=LONGVARCHAR}",
			"where GRP_CODE = #{grpCode,jdbcType=VARCHAR}",
			"and COM_CD = #{comCd,jdbcType=VARCHAR}" })
	public int updateByPrimaryKeyWithBLOBs(MwCmCodeWithBLOBs record) {
		return sqlMapper.update("com.wallet.membership.mapper.MwCmCodeMapper.updateByPrimaryKeySelective", record);
	}

	
	@Update({ "update MW_CM_CODE",
			"set COM_CD_VAL = #{comCdVal,jdbcType=VARCHAR},",
			"SEQ_NO = #{seqNo,jdbcType=INTEGER},",
			"USE_YN = #{useYn,jdbcType=CHAR},",
			"REMARK1 = #{remark1,jdbcType=VARCHAR},",
			"REMARK2 = #{remark2,jdbcType=VARCHAR},",
			"REMARK3 = #{remark3,jdbcType=VARCHAR},",
			"REMARK4 = #{remark4,jdbcType=VARCHAR},",
			"REG_USER = #{regUser,jdbcType=VARCHAR},",
			"REG_DTM = #{regDtm,jdbcType=TIMESTAMP},",
			"CHG_USER = #{chgUser,jdbcType=VARCHAR},",
			"CHG_DTM = #{chgDtm,jdbcType=TIMESTAMP}",
			"where GRP_CODE = #{grpCode,jdbcType=VARCHAR}",
			"and COM_CD = #{comCd,jdbcType=VARCHAR}" })
	public int updateByPrimaryKey(MwCmCode record) {
		return sqlMapper.update("com.wallet.membership.mapper.MwCmCodeMapper.updateByPrimaryKeySelective", record);
	}

}
