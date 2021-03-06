package com.wallet.membership.mapper;

import com.wallet.membership.model.MwCmImage;
import com.wallet.membership.model.MwCmImageExample;
import com.wallet.membership.model.MwCmImageKey;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface MwCmImageMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_CM_IMAGE
     *
     * @mbggenerated Sun Sep 16 22:55:42 KST 2012
     */
    int countByExample(MwCmImageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_CM_IMAGE
     *
     * @mbggenerated Sun Sep 16 22:55:42 KST 2012
     */
    int deleteByExample(MwCmImageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_CM_IMAGE
     *
     * @mbggenerated Sun Sep 16 22:55:42 KST 2012
     */
    @Delete({
        "delete from MW_CM_IMAGE",
        "where ID = #{id,jdbcType=VARCHAR}",
          "and OS_TYPE = #{osType,jdbcType=VARCHAR}",
          "and USE_TYPE = #{useType,jdbcType=VARCHAR}",
          "and LEVEL = #{level,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(MwCmImageKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_CM_IMAGE
     *
     * @mbggenerated Sun Sep 16 22:55:42 KST 2012
     */
    @Insert({
        "insert into MW_CM_IMAGE (ID, OS_TYPE, ",
        "USE_TYPE, LEVEL, ",
        "IMAGE_HOST, IMAGE_URL, USE_YN, ",
        "REG_USER, REG_DTM,",
        "CHG_USER, CHG_DTM)",
        "values (#{id,jdbcType=VARCHAR}, #{osType,jdbcType=VARCHAR}, ",
        "#{useType,jdbcType=VARCHAR}, #{level,jdbcType=VARCHAR}, ",
        "#{imageHost,jdbcType=VARCHAR}, #{imageUrl,jdbcType=VARCHAR}, #{useYn,jdbcType=VARCHAR}",
        "#{regUser,jdbcType=VARCHAR}, #{regDtm,jdbcType=TIMESTAMP}, ",
        "#{chgUser,jdbcType=VARCHAR}, #{chgDtm,jdbcType=TIMESTAMP})"
    })
    int insert(MwCmImage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_CM_IMAGE
     *
     * @mbggenerated Sun Sep 16 22:55:42 KST 2012
     */
    int insertSelective(MwCmImage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_CM_IMAGE
     *
     * @mbggenerated Sun Sep 16 22:55:42 KST 2012
     */
    List<MwCmImage> selectByExample(MwCmImageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_CM_IMAGE
     *
     * @mbggenerated Sun Sep 16 22:55:42 KST 2012
     */
    @Select({
        "select",
        "ID, OS_TYPE, USE_TYPE, LEVEL, IMAGE_HOST, IMAGE_URL, USE_YN, REG_USER, REG_DTM, CHG_USER, ",
        "CHG_DTM",
        "from MW_CM_IMAGE",
        "where ID = #{id,jdbcType=VARCHAR}",
          "and OS_TYPE = #{osType,jdbcType=VARCHAR}",
          "and USE_TYPE = #{useType,jdbcType=VARCHAR}",
          "and LEVEL = #{level,jdbcType=VARCHAR}"
    })
    @ResultMap("BaseResultMap")
    MwCmImage selectByPrimaryKey(MwCmImageKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_CM_IMAGE
     *
     * @mbggenerated Sun Sep 16 22:55:42 KST 2012
     */
    int updateByExampleSelective(@Param("record") MwCmImage record, @Param("example") MwCmImageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_CM_IMAGE
     *
     * @mbggenerated Sun Sep 16 22:55:42 KST 2012
     */
    int updateByExample(@Param("record") MwCmImage record, @Param("example") MwCmImageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_CM_IMAGE
     *
     * @mbggenerated Sun Sep 16 22:55:42 KST 2012
     */
    int updateByPrimaryKeySelective(MwCmImage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MW_CM_IMAGE
     *
     * @mbggenerated Sun Sep 16 22:55:42 KST 2012
     */
    @Update({
        "update MW_CM_IMAGE",
        "set IMAGE_HOST = #{imageHost,jdbcType=VARCHAR},",
        	"IMAGE_URL = #{imageUrl,jdbcType=VARCHAR},",
        	"USE_YN = #{useYn,jdbcType=VARCHAR},", 
          "REG_USER = #{regUser,jdbcType=VARCHAR},",
          "REG_DTM = #{regDtm,jdbcType=TIMESTAMP},",
          "CHG_USER = #{chgUser,jdbcType=VARCHAR},",
          "CHG_DTM = #{chgDtm,jdbcType=TIMESTAMP}",
        "where ID = #{id,jdbcType=VARCHAR}",
          "and OS_TYPE = #{osType,jdbcType=VARCHAR}",
          "and USE_TYPE = #{useType,jdbcType=VARCHAR}",
          "and LEVEL = #{level,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(MwCmImage record);
}