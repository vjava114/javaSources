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
import com.wallet.membership.mapper.MwCsCpnregHistMapper;
import com.wallet.membership.model.MwCsCpnregHist;
import com.wallet.membership.model.MwCsCpnregHistExample;
import com.wallet.membership.model.MwCsCpnregHistKey;

/**
 * 
 *
 */
public class MwCsCpnregHistDao extends MybatisCilent implements MwCsCpnregHistMapper {

	
	public void rollback(){
		sqlMapper.rollback();
	}
	
	public void commit() {
		sqlMapper.commit();
	}

	public int countByExample(MwCsCpnregHistExample example) {
		return (Integer) sqlMapper.selectOne("com.wallet.membership.mapper.MwCsCpnregHistMapper.countByExample", example);
	}

	public int deleteByExample(MwCsCpnregHistExample example) {
		return sqlMapper.delete("com.wallet.membership.mapper.MwCsCpnregHistMapper.deleteByExample", example);
	}

	@Delete({ "delete from MW_CS_CPNREG_HIST",
			"where CPN_ID = #{cpnId,jdbcType=VARCHAR}",
			"and SEQ_NO = #{seqNo,jdbcType=INTEGER}" })
	public int deleteByPrimaryKey(MwCsCpnregHistKey key) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Insert({
			"insert into MW_CS_CPNREG_HIST (CPN_ID, SEQ_NO, ",
			"CPN_STAT, REG_USER, REG_DTM, ",
			"STAT_COMMENT)",
			"values (#{cpnId,jdbcType=VARCHAR}, #{seqNo,jdbcType=INTEGER}, ",
			"#{cpnStat,jdbcType=CHAR}, #{regUser,jdbcType=VARCHAR}, #{regDtm,jdbcType=TIMESTAMP}, ",
			"#{statComment,jdbcType=LONGVARCHAR})" })
	public int insert(MwCsCpnregHist record) {
		return sqlMapper.insert("com.wallet.membership.mapper.MwCsCpnregHistMapper.insertSelective", record);
	}

	public int insertSelective(MwCsCpnregHist record) {
		return sqlMapper.insert("com.wallet.membership.mapper.MwCsCpnregHistMapper.insertSelective", record);
	}

	public List<MwCsCpnregHist> selectByExampleWithBLOBs(
			MwCsCpnregHistExample example) {
		return sqlMapper.selectList("com.wallet.membership.mapper.MwCsCpnregHistMapper.selectByExample", example);
	}

	public List<MwCsCpnregHist> selectByExample(MwCsCpnregHistExample example) {
		return sqlMapper.selectList("com.wallet.membership.mapper.MwCsCpnregHistMapper.selectByExample", example);
	}

	@Select({ "select",
			"CPN_ID, SEQ_NO, CPN_STAT, REG_USER, REG_DTM, STAT_COMMENT",
			"from MW_CS_CPNREG_HIST", "where CPN_ID = #{cpnId,jdbcType=VARCHAR}",
			"and SEQ_NO = #{seqNo,jdbcType=INTEGER}" })
	@ResultMap("ResultMapWithBLOBs")
	public MwCsCpnregHist selectByPrimaryKey(MwCsCpnregHistKey key) {
		// TODO Auto-generated method stub
		return null;
	}

	public int updateByExampleSelective(@Param("record") MwCsCpnregHist record,
			@Param("example") MwCsCpnregHistExample example) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("record", record);
		param.put("example", example);
		return sqlMapper.update("com.wallet.membership.mapper.MwCsCpnregHistMapper.updateByExampleSelective", param);
	}

	public int updateByExampleWithBLOBs(@Param("record") MwCsCpnregHist record,
			@Param("example") MwCsCpnregHistExample example) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("record", record);
		param.put("example", example);
		return sqlMapper.update("com.wallet.membership.mapper.MwCsCpnregHistMapper.updateByExampleWithBLOBs", param);
	}

	public int updateByExample(@Param("record") MwCsCpnregHist record,
			@Param("example") MwCsCpnregHistExample example) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("record", record);
		param.put("example", example);
		return sqlMapper.update("com.wallet.membership.mapper.MwCsCpnregHistMapper.updateByExampleWithBLOBs", param);
	}

	public int updateByPrimaryKeySelective(MwCsCpnregHist record) {
		return sqlMapper.update("com.wallet.membership.mapper.MwCsCpnregHistMapper.updateByPrimaryKeySelective", record);
	}
	
	@Update({ "update MW_CS_CPNREG_HIST",
			"set CPN_STAT = #{cpnStat,jdbcType=CHAR},",
			"REG_USER = #{regUser,jdbcType=VARCHAR},",
			"REG_DTM = #{regDtm,jdbcType=TIMESTAMP},",
			"STAT_COMMENT = #{statComment,jdbcType=LONGVARCHAR}",
			"where CPN_ID = #{cpnId,jdbcType=VARCHAR}",
			"and SEQ_NO = #{seqNo,jdbcType=INTEGER}" })
	public int updateByPrimaryKeyWithBLOBs(MwCsCpnregHist record) {
		return sqlMapper.update("com.wallet.membership.mapper.MwCsCpnregHistMapper.updateByPrimaryKeySelective", record);
	}

	@Update({ "update MW_CS_CPNREG_HIST",
			"set CPN_STAT = #{cpnStat,jdbcType=CHAR},",
			"REG_USER = #{regUser,jdbcType=VARCHAR},",
			"REG_DTM = #{regDtm,jdbcType=TIMESTAMP}",
			"where CPN_ID = #{cpnId,jdbcType=VARCHAR}",
			"and SEQ_NO = #{seqNo,jdbcType=INTEGER}" })
	public int updateByPrimaryKey(MwCsCpnregHist record) {
		return sqlMapper.update("com.wallet.membership.mapper.MwCsCpnregHistMapper.updateByPrimaryKeySelective", record);
	}


}
