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
import com.wallet.membership.mapper.MwCmImageMapper;
import com.wallet.membership.model.MwCmCode;
import com.wallet.membership.model.MwCmCodeExample;
import com.wallet.membership.model.MwCmCodeKey;
import com.wallet.membership.model.MwCmCodeWithBLOBs;
import com.wallet.membership.model.MwCmImage;
import com.wallet.membership.model.MwCmImageExample;
import com.wallet.membership.model.MwCmImageKey;

/**
 * 
 *
 */
public class MwCmImageDao extends MybatisCilent implements MwCmImageMapper {

	
	public void rollback(){
		sqlMapper.rollback();
	}

	
	public void commit() {
		sqlMapper.commit();
	}


	public int countByExample(MwCmImageExample example) {
		return (Integer) sqlMapper.selectOne("com.wallet.membership.mapper.MwCmImageMapper.countByExample", example);
	}


	public int deleteByExample(MwCmImageExample example) {
		return sqlMapper.delete("com.wallet.membership.mapper.MwCmImageMapper.deleteByExample", example);
	}


	@Delete({ "delete from MW_CM_IMAGE", "where ID = #{id,jdbcType=VARCHAR}",
			"and OS_TYPE = #{osType,jdbcType=VARCHAR}",
			"and USE_TYPE = #{useType,jdbcType=VARCHAR}",
			"and LEVEL = #{level,jdbcType=VARCHAR}" })
	public int deleteByPrimaryKey(MwCmImageKey key) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Insert({ "insert into MW_CM_IMAGE (ID, OS_TYPE, ", "USE_TYPE, LEVEL, ",
			"IMAGE_HOST, IMAGE_URL, ", "REG_USER, REG_DTM, ", "CHG_USER, CHG_DTM)",
			"values (#{id,jdbcType=VARCHAR}, #{osType,jdbcType=VARCHAR}, ",
			"#{useType,jdbcType=VARCHAR}, #{level,jdbcType=VARCHAR}, ",
			"#{imageHost,jdbcType=VARCHAR}, #{imageUrl,jdbcType=VARCHAR}, ",
			"#{regUser,jdbcType=VARCHAR}, #{regDtm,jdbcType=TIMESTAMP}, ",
			"#{chgUser,jdbcType=VARCHAR}, #{chgDtm,jdbcType=TIMESTAMP})" })
	public int insert(MwCmImage record) {
		return sqlMapper.insert("com.wallet.membership.mapper.MwCmImageMapper.insertSelective", record);
	}


	public int insertSelective(MwCmImage record) {
		return sqlMapper.insert("com.wallet.membership.mapper.MwCmImageMapper.insertSelective", record);
	}


	public List<MwCmImage> selectByExample(MwCmImageExample example) {
		return sqlMapper.selectList("com.wallet.membership.mapper.MwCmImageMapper.selectByExample", example);
	}


	@Select({
			"select",
			"ID, OS_TYPE, USE_TYPE, LEVEL, IMAGE_HOST, IMAGE_URL, REG_USER, REG_DTM, CHG_USER, ",
			"CHG_DTM", "from MW_CM_IMAGE", "where ID = #{id,jdbcType=VARCHAR}",
			"and OS_TYPE = #{osType,jdbcType=VARCHAR}",
			"and USE_TYPE = #{useType,jdbcType=VARCHAR}",
			"and LEVEL = #{level,jdbcType=VARCHAR}" })
	@ResultMap("BaseResultMap")
	public MwCmImage selectByPrimaryKey(MwCmImageKey key) {
		// TODO Auto-generated method stub
		return null;
	}


	public int updateByExampleSelective(@Param("record") MwCmImage record,
			@Param("example") MwCmImageExample example) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("record", record);
		param.put("example", example);
		return sqlMapper.update("com.wallet.membership.mapper.MwCmImageMapper.updateByExampleSelective", param);
	}


	public int updateByExample(@Param("record") MwCmImage record,
			@Param("example") MwCmImageExample example) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("record", record);
		param.put("example", example);
		return sqlMapper.update("com.wallet.membership.mapper.MwCmImageMapper.updateByExample", param);
	}


	public int updateByPrimaryKeySelective(MwCmImage record) {
		return sqlMapper.update("com.wallet.membership.mapper.MwCmImageMapper.updateByPrimaryKeySelective", record);
	}


	@Update({ "update MW_CM_IMAGE",
			"set IMAGE_HOST = #{imageHost,jdbcType=VARCHAR},",
			"IMAGE_URL = #{imageUrl,jdbcType=VARCHAR},",
			"REG_USER = #{regUser,jdbcType=VARCHAR},",
			"REG_DTM = #{regDtm,jdbcType=TIMESTAMP},",
			"CHG_USER = #{chgUser,jdbcType=VARCHAR},",
			"CHG_DTM = #{chgDtm,jdbcType=TIMESTAMP}",
			"where ID = #{id,jdbcType=VARCHAR}",
			"and OS_TYPE = #{osType,jdbcType=VARCHAR}",
			"and USE_TYPE = #{useType,jdbcType=VARCHAR}",
			"and LEVEL = #{level,jdbcType=VARCHAR}" })
	public int updateByPrimaryKey(MwCmImage record) {
		return sqlMapper.update("com.wallet.membership.mapper.MwCmImageMapper.updateByPrimaryKeySelective", record);
	}

}
