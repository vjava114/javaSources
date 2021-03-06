package com.wallet.membership.mapper;

import com.wallet.membership.model.CpnBankInfo;
import com.wallet.membership.model.CpnBankInfoExample;
import com.wallet.membership.model.CpnBankInfoWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface CpnBankInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cpn_bank_info
     *
     * @mbggenerated Fri Sep 07 22:07:33 KST 2012
     */
    int countByExample(CpnBankInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cpn_bank_info
     *
     * @mbggenerated Fri Sep 07 22:07:33 KST 2012
     */
    int deleteByExample(CpnBankInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cpn_bank_info
     *
     * @mbggenerated Fri Sep 07 22:07:33 KST 2012
     */
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
        "#{memo,jdbcType=LONGVARCHAR})"
    })
    int insert(CpnBankInfoWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cpn_bank_info
     *
     * @mbggenerated Fri Sep 07 22:07:33 KST 2012
     */
    int insertSelective(CpnBankInfoWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cpn_bank_info
     *
     * @mbggenerated Fri Sep 07 22:07:33 KST 2012
     */
    List<CpnBankInfoWithBLOBs> selectByExampleWithBLOBs(CpnBankInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cpn_bank_info
     *
     * @mbggenerated Fri Sep 07 22:07:33 KST 2012
     */
    List<CpnBankInfo> selectByExample(CpnBankInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cpn_bank_info
     *
     * @mbggenerated Fri Sep 07 22:07:33 KST 2012
     */
    int updateByExampleSelective(@Param("record") CpnBankInfoWithBLOBs record, @Param("example") CpnBankInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cpn_bank_info
     *
     * @mbggenerated Fri Sep 07 22:07:33 KST 2012
     */
    int updateByExampleWithBLOBs(@Param("record") CpnBankInfoWithBLOBs record, @Param("example") CpnBankInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cpn_bank_info
     *
     * @mbggenerated Fri Sep 07 22:07:33 KST 2012
     */
    int updateByExample(@Param("record") CpnBankInfo record, @Param("example") CpnBankInfoExample example);
}