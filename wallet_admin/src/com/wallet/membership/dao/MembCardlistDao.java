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
import com.wallet.membership.mapper.MembCardlistMapper;
import com.wallet.membership.model.MembCardlist;
import com.wallet.membership.model.MembCardlistExample;

/**
 * 
 *
 */
public class MembCardlistDao extends MybatisCilent implements MembCardlistMapper {

	
	public void rollback(){
		sqlMapper.rollback();
	}
	
	
	public int countByExample(MembCardlistExample example) {
		return (Integer) sqlMapper.selectOne("com.wallet.membership.mapper.MembCardlistMapper.countByExample", example);
	}

	
	public int deleteByExample(MembCardlistExample example) {
		return sqlMapper.delete("com.wallet.membership.mapper.MembCardlistMapper.deleteByExample", example);
	}

	
	@Delete({ "delete from memb_cardlist",
			"where memb_id = #{membId,jdbcType=VARCHAR}" })
	public int deleteByPrimaryKey(String membId) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	@Insert({
			"insert into memb_cardlist (memb_id, memb_code, ",
			"sub_yn, dis_sday, ",
			"stat, cate_id, name, ",
			"info, shop_desc, ",
			"dis_eday, memo, reg_user, ",
			"pnt_type, reg_day, ",
			"chg_user, chg_day, ",
			"maindis_yn, main_idx, ",
			"mydis_yn, shop_yn, ",
			"cpn_yn, evt_yn, new_yn, ",
			"display_yn, link_yn, ",
			"cdma_yn, mtype, info_url, ",
			"sms_yn, email_yn, pass_yn, ",
			"usim_mode, app_url, ",
			"usim_id, multi_yn, memb_type, ",
			"ENG_NAME_YN, JOIN_URL, ",
			"NOTI_URL, COMP_ID, ",
			"RECOMM_YN, RECOMM_SEQ, ",
			"chg_tm, popu_seq, ",
			"POINT_TYPE, BAR_TYPE, CLASS_YN, ",
			"UPPER_MEMB_ID, ",
			"SHOPINFO_YN, SHOPINFO_URL)",
			"values (#{membId,jdbcType=VARCHAR}, #{membCode,jdbcType=VARCHAR}, ",
			"#{subYn,jdbcType=VARCHAR}, #{disSday,jdbcType=VARCHAR}, ",
			"#{stat,jdbcType=VARCHAR}, #{cateId,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
			"#{info,jdbcType=VARCHAR}, #{shopDesc,jdbcType=VARCHAR}, ",
			"#{disEday,jdbcType=VARCHAR}, #{memo,jdbcType=VARCHAR}, #{regUser,jdbcType=VARCHAR}, ",
			"#{pntType,jdbcType=VARCHAR}, #{regDay,jdbcType=VARCHAR}, ",
			"#{chgUser,jdbcType=VARCHAR}, #{chgDay,jdbcType=VARCHAR}, ",
			"#{maindisYn,jdbcType=VARCHAR}, #{mainIdx,jdbcType=INTEGER}, ",
			"#{mydisYn,jdbcType=VARCHAR}, #{shopYn,jdbcType=VARCHAR}, ",
			"#{cpnYn,jdbcType=VARCHAR}, #{evtYn,jdbcType=VARCHAR}, #{newYn,jdbcType=VARCHAR}, ",
			"#{displayYn,jdbcType=VARCHAR}, #{linkYn,jdbcType=VARCHAR}, ",
			"#{cdmaYn,jdbcType=VARCHAR}, #{mtype,jdbcType=CHAR}, #{infoUrl,jdbcType=VARCHAR}, ",
			"#{smsYn,jdbcType=CHAR}, #{emailYn,jdbcType=CHAR}, #{passYn,jdbcType=VARCHAR}, ",
			"#{usimMode,jdbcType=VARCHAR}, #{appUrl,jdbcType=VARCHAR}, ",
			"#{usimId,jdbcType=VARCHAR}, #{multiYn,jdbcType=CHAR}, #{membType,jdbcType=CHAR}, ",
			"#{engNameYn,jdbcType=CHAR}, #{joinUrl,jdbcType=VARCHAR}, ",
			"#{notiUrl,jdbcType=VARCHAR}, #{compId,jdbcType=VARCHAR}, ",
			"#{recommYn,jdbcType=CHAR}, #{recommSeq,jdbcType=INTEGER}, ",
			"#{chgTm,jdbcType=VARCHAR}, #{popuSeq,jdbcType=INTEGER}, ",
			"#{pointType,jdbcType=CHAR}, #{barType,jdbcType=CHAR}, #{classYn,jdbcType=CHAR}, ",
			"#{compPayYn,jdbcType=CHAR}, #{upperMembId,jdbcType=VARCHAR}, ",
			"#{shopinfoYn,jdbcType=CHAR}, #{shopinfoUrl,jdbcType=VARCHAR})" })
	public int insert(MembCardlist record) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int insertSelective(MembCardlist record) {
		return sqlMapper.insert("com.wallet.membership.mapper.MembCardlistMapper.insertSelective", record);
	}

	
	public List<MembCardlist> selectByExample(MembCardlistExample example) {
		return sqlMapper.selectList("com.wallet.membership.mapper.MembCardlistMapper.selectByExample", example);
	}

	
	@Select({
			"select",
			"memb_id, memb_code, sub_yn, dis_sday, stat, cate_id, name, info, shop_desc, ",
			"dis_eday, memo, reg_user, pnt_type, reg_day, chg_user, chg_day, maindis_yn, ",
			"main_idx, mydis_yn, shop_yn, cpn_yn, evt_yn, new_yn, display_yn, link_yn, cdma_yn, ",
			"mtype, info_url, sms_yn, email_yn, pass_yn, usim_mode, app_url, usim_id, multi_yn, ",
			"memb_type, ENG_NAME_YN, JOIN_URL, NOTI_URL, COMP_ID, RECOMM_YN, RECOMM_SEQ, ",
			"chg_tm, popu_seq, POINT_TYPE, BAR_TYPE, CLASS_YN, UPPER_MEMB_ID, ",
			"SHOPINFO_YN, SHOPINFO_URL", "from memb_cardlist",
			"where memb_id = #{membId,jdbcType=VARCHAR}" })
	@ResultMap("BaseResultMap")
	public MembCardlist selectByPrimaryKey(String membId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public int updateByExampleSelective(@Param("record") MembCardlist record,
			@Param("example") MembCardlistExample example) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("record", record);
		param.put("example", example);
		return sqlMapper.update("com.wallet.membership.mapper.MembCardlistMapper.updateByExampleSelective", param);
	}

	
	public int updateByExample(@Param("record") MembCardlist record,
			@Param("example") MembCardlistExample example) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("record", record);
		param.put("example", example);
		return sqlMapper.update("com.wallet.membership.mapper.MembCardlistMapper.updateByExample", param);
	}

	
	public int updateByPrimaryKeySelective(MembCardlist record) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	@Update({ "update memb_cardlist",
			"set memb_code = #{membCode,jdbcType=VARCHAR},",
			"sub_yn = #{subYn,jdbcType=VARCHAR},",
			"dis_sday = #{disSday,jdbcType=VARCHAR},",
			"stat = #{stat,jdbcType=VARCHAR},",
			"cate_id = #{cateId,jdbcType=VARCHAR},",
			"name = #{name,jdbcType=VARCHAR},", "info = #{info,jdbcType=VARCHAR},",
			"shop_desc = #{shopDesc,jdbcType=VARCHAR},",
			"dis_eday = #{disEday,jdbcType=VARCHAR},",
			"memo = #{memo,jdbcType=VARCHAR},",
			"reg_user = #{regUser,jdbcType=VARCHAR},",
			"pnt_type = #{pntType,jdbcType=VARCHAR},",
			"reg_day = #{regDay,jdbcType=VARCHAR},",
			"chg_user = #{chgUser,jdbcType=VARCHAR},",
			"chg_day = #{chgDay,jdbcType=VARCHAR},",
			"maindis_yn = #{maindisYn,jdbcType=VARCHAR},",
			"main_idx = #{mainIdx,jdbcType=INTEGER},",
			"mydis_yn = #{mydisYn,jdbcType=VARCHAR},",
			"shop_yn = #{shopYn,jdbcType=VARCHAR},",
			"cpn_yn = #{cpnYn,jdbcType=VARCHAR},",
			"evt_yn = #{evtYn,jdbcType=VARCHAR},",
			"new_yn = #{newYn,jdbcType=VARCHAR},",
			"display_yn = #{displayYn,jdbcType=VARCHAR},",
			"link_yn = #{linkYn,jdbcType=VARCHAR},",
			"cdma_yn = #{cdmaYn,jdbcType=VARCHAR},",
			"mtype = #{mtype,jdbcType=CHAR},",
			"info_url = #{infoUrl,jdbcType=VARCHAR},",
			"sms_yn = #{smsYn,jdbcType=CHAR},",
			"email_yn = #{emailYn,jdbcType=CHAR},",
			"pass_yn = #{passYn,jdbcType=VARCHAR},",
			"usim_mode = #{usimMode,jdbcType=VARCHAR},",
			"app_url = #{appUrl,jdbcType=VARCHAR},",
			"usim_id = #{usimId,jdbcType=VARCHAR},",
			"multi_yn = #{multiYn,jdbcType=CHAR},",
			"memb_type = #{membType,jdbcType=CHAR},",
			"ENG_NAME_YN = #{engNameYn,jdbcType=CHAR},",
			"JOIN_URL = #{joinUrl,jdbcType=VARCHAR},",
			"NOTI_URL = #{notiUrl,jdbcType=VARCHAR},",
			"COMP_ID = #{compId,jdbcType=VARCHAR},",
			"RECOMM_YN = #{recommYn,jdbcType=CHAR},",
			"RECOMM_SEQ = #{recommSeq,jdbcType=INTEGER},",
			"chg_tm = #{chgTm,jdbcType=VARCHAR},",
			"popu_seq = #{popuSeq,jdbcType=INTEGER},",
			"POINT_TYPE = #{pointType,jdbcType=CHAR},",
			"BAR_TYPE = #{barType,jdbcType=CHAR},",
			"CLASS_YN = #{classYn,jdbcType=CHAR},",
			"UPPER_MEMB_ID = #{upperMembId,jdbcType=VARCHAR},",
			"SHOPINFO_YN = #{shopinfoYn,jdbcType=CHAR},",
			"SHOPINFO_URL = #{shopinfoUrl,jdbcType=VARCHAR}",
			"where memb_id = #{membId,jdbcType=VARCHAR}" })
	public int updateByPrimaryKey(MembCardlist record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void commit() {
		sqlMapper.commit();
	}
}
