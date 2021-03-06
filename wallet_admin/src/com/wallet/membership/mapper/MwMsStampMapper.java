package com.wallet.membership.mapper;

import com.wallet.membership.model.MwCmCompany;
import com.wallet.membership.model.MwCmCompanyExample;
import com.wallet.membership.model.MwMsStamp;
import com.wallet.membership.model.MwMsStampExample;
import com.wallet.membership.model.MwMsStampKey;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface MwMsStampMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_MS_STAMP
     *
     * @mbggenerated Tue Sep 18 15:05:33 KST 2012
     */
    int countByExample(MwMsStampExample example);
    int StampSelectByCount(MwMsStampExample example);
    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_MS_STAMP
     *
     * @mbggenerated Tue Sep 18 15:05:33 KST 2012
     */
    int deleteByExample(MwMsStampExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_MS_STAMP
     *
     * @mbggenerated Tue Sep 18 15:05:33 KST 2012
     */
    @Delete({
        "delete from MW_MS_STAMP",
        "where MEMB_ID = #{membId,jdbcType=VARCHAR}",
          "and STAMP_SEQ = #{stampSeq,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(MwMsStampKey key);
    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_MS_STAMP
     *
     * @mbggenerated Tue Sep 18 15:05:33 KST 2012
     */
    @Insert({
        "insert into MW_MS_STAMP (MEMB_ID, STAMP_SEQ, ",
        "STAMP_SVC_TYPE, STAMP_UNIT, ",
        "STAMP_IMAGE, STAMP_MAX_NO, ",
        "EXPIRE_SDAY, EXPIRE_EDAY, ",
        "REG_USER, REG_DTM, ",
        "CHG_USER, CHG_DTM, ",
        "BENEFIT_NOTICE)",
        "values (#{membId,jdbcType=VARCHAR}, #{stampSeq,jdbcType=INTEGER}, ",
        "#{stampSvcType,jdbcType=CHAR}, #{stampUnit,jdbcType=INTEGER}, ",
        "#{stampImage,jdbcType=VARCHAR}, #{stampMaxNo,jdbcType=INTEGER}, ",
        "#{expireSday,jdbcType=VARCHAR}, #{expireEday,jdbcType=VARCHAR}, ",
        "#{regUser,jdbcType=VARCHAR}, #{regDtm,jdbcType=TIMESTAMP}, ",
        "#{chgUser,jdbcType=VARCHAR}, #{chgDtm,jdbcType=TIMESTAMP}, ",
        "#{benefitNotice,jdbcType=LONGVARCHAR})"
    })
    int insert(MwMsStamp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_MS_STAMP
     *
     * @mbggenerated Tue Sep 18 15:05:33 KST 2012
     */
    int insertSelective(MwMsStamp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_MS_STAMP
     *
     * @mbggenerated Tue Sep 18 15:05:33 KST 2012
     */
    List<MwMsStamp> selectByExampleWithBLOBs(MwMsStampExample example);
    List<MwMsStamp> StampGetBySeq(MwMsStampExample example);
    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_MS_STAMP
     *
     * @mbggenerated Tue Sep 18 15:05:33 KST 2012
     */
    List<MwMsStamp> selectByExample(MwMsStampExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_MS_STAMP
     *
     * @mbggenerated Tue Sep 18 15:05:33 KST 2012
     */
	List<MwMsStamp> StampSelectByExample(MwMsStampExample example);

    @Select({
        "select",
        "MEMB_ID, STAMP_SEQ, STAMP_SVC_TYPE, STAMP_UNIT, STAMP_IMAGE, STAMP_MAX_NO, EXPIRE_SDAY, ",
        "EXPIRE_EDAY, REG_USER, REG_DTM, CHG_USER, CHG_DTM, BENEFIT_NOTICE",
        "from MW_MS_STAMP",
        "where MEMB_ID = #{membId,jdbcType=VARCHAR}",
          "and STAMP_SEQ = #{stampSeq,jdbcType=INTEGER}"
    })
    @ResultMap("ResultMapWithBLOBs")
    MwMsStamp selectByPrimaryKey(MwMsStampKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_MS_STAMP
     *
     * @mbggenerated Tue Sep 18 15:05:33 KST 2012
     */
    int updateByExampleSelective(@Param("record") MwMsStamp record, @Param("example") MwMsStampExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_MS_STAMP
     *
     * @mbggenerated Tue Sep 18 15:05:33 KST 2012
     */
    int updateByExampleWithBLOBs(@Param("record") MwMsStamp record, @Param("example") MwMsStampExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_MS_STAMP
     *
     * @mbggenerated Tue Sep 18 15:05:33 KST 2012
     */
    int updateByExample(@Param("record") MwMsStamp record, @Param("example") MwMsStampExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_MS_STAMP
     *
     * @mbggenerated Tue Sep 18 15:05:33 KST 2012
     */
    int updateByPrimaryKeySelective(MwMsStamp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_MS_STAMP
     *
     * @mbggenerated Tue Sep 18 15:05:33 KST 2012
     */
    @Update({
        "update MW_MS_STAMP",
        "set STAMP_SVC_TYPE = #{stampSvcType,jdbcType=CHAR},",
          "STAMP_UNIT = #{stampUnit,jdbcType=INTEGER},",
          "STAMP_IMAGE = #{stampImage,jdbcType=VARCHAR},",
          "STAMP_MAX_NO = #{stampMaxNo,jdbcType=INTEGER},",
          "EXPIRE_SDAY = #{expireSday,jdbcType=VARCHAR},",
          "EXPIRE_EDAY = #{expireEday,jdbcType=VARCHAR},",
          "REG_USER = #{regUser,jdbcType=VARCHAR},",
          "REG_DTM = #{regDtm,jdbcType=TIMESTAMP},",
          "CHG_USER = #{chgUser,jdbcType=VARCHAR},",
          "CHG_DTM = #{chgDtm,jdbcType=TIMESTAMP},",
          "BENEFIT_NOTICE = #{benefitNotice,jdbcType=LONGVARCHAR}",
        "where MEMB_ID = #{membId,jdbcType=VARCHAR}",
          "and STAMP_SEQ = #{stampSeq,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(MwMsStamp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_MS_STAMP
     *
     * @mbggenerated Tue Sep 18 15:05:33 KST 2012
     */
    @Update({
        "update MW_MS_STAMP",
        "set STAMP_SVC_TYPE = #{stampSvcType,jdbcType=CHAR},",
          "STAMP_UNIT = #{stampUnit,jdbcType=INTEGER},",
          "STAMP_IMAGE = #{stampImage,jdbcType=VARCHAR},",
          "STAMP_MAX_NO = #{stampMaxNo,jdbcType=INTEGER},",
          "EXPIRE_SDAY = #{expireSday,jdbcType=VARCHAR},",
          "EXPIRE_EDAY = #{expireEday,jdbcType=VARCHAR},",
          "REG_USER = #{regUser,jdbcType=VARCHAR},",
          "REG_DTM = #{regDtm,jdbcType=TIMESTAMP},",
          "CHG_USER = #{chgUser,jdbcType=VARCHAR},",
          "CHG_DTM = #{chgDtm,jdbcType=TIMESTAMP}",
        "where MEMB_ID = #{membId,jdbcType=VARCHAR}",
          "and STAMP_SEQ = #{stampSeq,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(MwMsStamp record);
}