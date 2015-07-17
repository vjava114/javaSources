package com.wallet.membership.model;

import java.util.Date;

import com.rocomo.common.model.Common;

public class MwCmAdminInfo extends Common {
	
	private String compName;
	private String branName;
	private String shopName;
	private String adminLevelStr;
	private String regDtmStr;
	private String chgDtmStr;
	private String adminPass;
	private String oldAdminPass;
	private String stat;
	private Integer retryCnt;
	
	
	
  public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	public Integer getRetryCnt() {
		return retryCnt;
	}
	public void setRetryCnt(Integer retryCnt) {
		this.retryCnt = retryCnt;
	}
public String getOldAdminPass() {
		return oldAdminPass;
	}
	public void setOldAdminPass(String oldAdminPass) {
		this.oldAdminPass = oldAdminPass;
	}
	public String getAdminPass() {
		return adminPass;
	}
	public void setAdminPass(String adminPass) {
		this.adminPass = adminPass;
	}
	public String getRegDtmStr() {
		return regDtmStr;
	}
	public void setRegDtmStr(String regDtmStr) {
		this.regDtmStr = regDtmStr;
	}
	public String getChgDtmStr() {
		return chgDtmStr;
	}
	public void setChgDtmStr(String chgDtmStr) {
		this.chgDtmStr = chgDtmStr;
	}
	public String getAdminLevelStr() {
		return adminLevelStr;
	}
	public void setAdminLevelStr(String adminLevelStr) {
		this.adminLevelStr = adminLevelStr;
	}
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public String getBranName() {
		return branName;
	}
	public void setBranName(String branName) {
		this.branName = branName;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

		/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MW_CM_ADMIN_INFO.ADMIN_ID
     *
     * @mbggenerated Wed Sep 26 13:24:52 KST 2012
     */
    private String adminId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MW_CM_ADMIN_INFO.ADMIN_LEVEL
     *
     * @mbggenerated Wed Sep 26 13:24:52 KST 2012
     */
    private String adminLevel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MW_CM_ADMIN_INFO.COMP_ID
     *
     * @mbggenerated Wed Sep 26 13:24:52 KST 2012
     */
    private String compId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MW_CM_ADMIN_INFO.BRAN_ID
     *
     * @mbggenerated Wed Sep 26 13:24:52 KST 2012
     */
    private String branId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MW_CM_ADMIN_INFO.SHOP_ID
     *
     * @mbggenerated Wed Sep 26 13:24:52 KST 2012
     */
    private String shopId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MW_CM_ADMIN_INFO.ADMIN_NAME
     *
     * @mbggenerated Wed Sep 26 13:24:52 KST 2012
     */
    private String adminName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MW_CM_ADMIN_INFO.IP
     *
     * @mbggenerated Wed Sep 26 13:24:52 KST 2012
     */
    private String ip;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MW_CM_ADMIN_INFO.MOBILE_PHONE
     *
     * @mbggenerated Wed Sep 26 13:24:52 KST 2012
     */
    private String mobilePhone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MW_CM_ADMIN_INFO.EMERGEN_PHONE
     *
     * @mbggenerated Wed Sep 26 13:24:52 KST 2012
     */
    private String emergenPhone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MW_CM_ADMIN_INFO.EMAIL
     *
     * @mbggenerated Wed Sep 26 13:24:52 KST 2012
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MW_CM_ADMIN_INFO.REG_USER
     *
     * @mbggenerated Wed Sep 26 13:24:52 KST 2012
     */
    private String regUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MW_CM_ADMIN_INFO.REG_DTM
     *
     * @mbggenerated Wed Sep 26 13:24:52 KST 2012
     */
    private Date regDtm;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MW_CM_ADMIN_INFO.CHG_USER
     *
     * @mbggenerated Wed Sep 26 13:24:52 KST 2012
     */
    private String chgUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MW_CM_ADMIN_INFO.CHG_DTM
     *
     * @mbggenerated Wed Sep 26 13:24:52 KST 2012
     */
    private Date chgDtm;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MW_CM_ADMIN_INFO.ADMIN_ID
     *
     * @return the value of MW_CM_ADMIN_INFO.ADMIN_ID
     *
     * @mbggenerated Wed Sep 26 13:24:52 KST 2012
     */
    public String getAdminId() {
        return adminId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MW_CM_ADMIN_INFO.ADMIN_ID
     *
     * @param adminId the value for MW_CM_ADMIN_INFO.ADMIN_ID
     *
     * @mbggenerated Wed Sep 26 13:24:52 KST 2012
     */
    public void setAdminId(String adminId) {
        this.adminId = adminId == null ? null : adminId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MW_CM_ADMIN_INFO.ADMIN_LEVEL
     *
     * @return the value of MW_CM_ADMIN_INFO.ADMIN_LEVEL
     *
     * @mbggenerated Wed Sep 26 13:24:52 KST 2012
     */
    public String getAdminLevel() {
        return adminLevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MW_CM_ADMIN_INFO.ADMIN_LEVEL
     *
     * @param adminLevel the value for MW_CM_ADMIN_INFO.ADMIN_LEVEL
     *
     * @mbggenerated Wed Sep 26 13:24:52 KST 2012
     */
    public void setAdminLevel(String adminLevel) {
        this.adminLevel = adminLevel == null ? null : adminLevel.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MW_CM_ADMIN_INFO.COMP_ID
     *
     * @return the value of MW_CM_ADMIN_INFO.COMP_ID
     *
     * @mbggenerated Wed Sep 26 13:24:52 KST 2012
     */
    public String getCompId() {
        return compId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MW_CM_ADMIN_INFO.COMP_ID
     *
     * @param compId the value for MW_CM_ADMIN_INFO.COMP_ID
     *
     * @mbggenerated Wed Sep 26 13:24:52 KST 2012
     */
    public void setCompId(String compId) {
        this.compId = compId == null ? null : compId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MW_CM_ADMIN_INFO.BRAN_ID
     *
     * @return the value of MW_CM_ADMIN_INFO.BRAN_ID
     *
     * @mbggenerated Wed Sep 26 13:24:52 KST 2012
     */
    public String getBranId() {
        return branId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MW_CM_ADMIN_INFO.BRAN_ID
     *
     * @param branId the value for MW_CM_ADMIN_INFO.BRAN_ID
     *
     * @mbggenerated Wed Sep 26 13:24:52 KST 2012
     */
    public void setBranId(String branId) {
        this.branId = branId == null ? null : branId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MW_CM_ADMIN_INFO.SHOP_ID
     *
     * @return the value of MW_CM_ADMIN_INFO.SHOP_ID
     *
     * @mbggenerated Wed Sep 26 13:24:52 KST 2012
     */
    public String getShopId() {
        return shopId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MW_CM_ADMIN_INFO.SHOP_ID
     *
     * @param shopId the value for MW_CM_ADMIN_INFO.SHOP_ID
     *
     * @mbggenerated Wed Sep 26 13:24:52 KST 2012
     */
    public void setShopId(String shopId) {
        this.shopId = shopId == null ? null : shopId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MW_CM_ADMIN_INFO.ADMIN_NAME
     *
     * @return the value of MW_CM_ADMIN_INFO.ADMIN_NAME
     *
     * @mbggenerated Wed Sep 26 13:24:52 KST 2012
     */
    public String getAdminName() {
        return adminName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MW_CM_ADMIN_INFO.ADMIN_NAME
     *
     * @param adminName the value for MW_CM_ADMIN_INFO.ADMIN_NAME
     *
     * @mbggenerated Wed Sep 26 13:24:52 KST 2012
     */
    public void setAdminName(String adminName) {
        this.adminName = adminName == null ? null : adminName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MW_CM_ADMIN_INFO.IP
     *
     * @return the value of MW_CM_ADMIN_INFO.IP
     *
     * @mbggenerated Wed Sep 26 13:24:52 KST 2012
     */
    public String getIp() {
        return ip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MW_CM_ADMIN_INFO.IP
     *
     * @param ip the value for MW_CM_ADMIN_INFO.IP
     *
     * @mbggenerated Wed Sep 26 13:24:52 KST 2012
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MW_CM_ADMIN_INFO.MOBILE_PHONE
     *
     * @return the value of MW_CM_ADMIN_INFO.MOBILE_PHONE
     *
     * @mbggenerated Wed Sep 26 13:24:52 KST 2012
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MW_CM_ADMIN_INFO.MOBILE_PHONE
     *
     * @param mobilePhone the value for MW_CM_ADMIN_INFO.MOBILE_PHONE
     *
     * @mbggenerated Wed Sep 26 13:24:52 KST 2012
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MW_CM_ADMIN_INFO.EMERGEN_PHONE
     *
     * @return the value of MW_CM_ADMIN_INFO.EMERGEN_PHONE
     *
     * @mbggenerated Wed Sep 26 13:24:52 KST 2012
     */
    public String getEmergenPhone() {
        return emergenPhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MW_CM_ADMIN_INFO.EMERGEN_PHONE
     *
     * @param emergenPhone the value for MW_CM_ADMIN_INFO.EMERGEN_PHONE
     *
     * @mbggenerated Wed Sep 26 13:24:52 KST 2012
     */
    public void setEmergenPhone(String emergenPhone) {
        this.emergenPhone = emergenPhone == null ? null : emergenPhone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MW_CM_ADMIN_INFO.EMAIL
     *
     * @return the value of MW_CM_ADMIN_INFO.EMAIL
     *
     * @mbggenerated Wed Sep 26 13:24:52 KST 2012
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MW_CM_ADMIN_INFO.EMAIL
     *
     * @param email the value for MW_CM_ADMIN_INFO.EMAIL
     *
     * @mbggenerated Wed Sep 26 13:24:52 KST 2012
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MW_CM_ADMIN_INFO.REG_USER
     *
     * @return the value of MW_CM_ADMIN_INFO.REG_USER
     *
     * @mbggenerated Wed Sep 26 13:24:52 KST 2012
     */
    public String getRegUser() {
        return regUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MW_CM_ADMIN_INFO.REG_USER
     *
     * @param regUser the value for MW_CM_ADMIN_INFO.REG_USER
     *
     * @mbggenerated Wed Sep 26 13:24:52 KST 2012
     */
    public void setRegUser(String regUser) {
        this.regUser = regUser == null ? null : regUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MW_CM_ADMIN_INFO.REG_DTM
     *
     * @return the value of MW_CM_ADMIN_INFO.REG_DTM
     *
     * @mbggenerated Wed Sep 26 13:24:52 KST 2012
     */
    public Date getRegDtm() {
        return regDtm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MW_CM_ADMIN_INFO.REG_DTM
     *
     * @param regDtm the value for MW_CM_ADMIN_INFO.REG_DTM
     *
     * @mbggenerated Wed Sep 26 13:24:52 KST 2012
     */
    public void setRegDtm(Date regDtm) {
        this.regDtm = regDtm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MW_CM_ADMIN_INFO.CHG_USER
     *
     * @return the value of MW_CM_ADMIN_INFO.CHG_USER
     *
     * @mbggenerated Wed Sep 26 13:24:52 KST 2012
     */
    public String getChgUser() {
        return chgUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MW_CM_ADMIN_INFO.CHG_USER
     *
     * @param chgUser the value for MW_CM_ADMIN_INFO.CHG_USER
     *
     * @mbggenerated Wed Sep 26 13:24:52 KST 2012
     */
    public void setChgUser(String chgUser) {
        this.chgUser = chgUser == null ? null : chgUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MW_CM_ADMIN_INFO.CHG_DTM
     *
     * @return the value of MW_CM_ADMIN_INFO.CHG_DTM
     *
     * @mbggenerated Wed Sep 26 13:24:52 KST 2012
     */
    public Date getChgDtm() {
        return chgDtm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MW_CM_ADMIN_INFO.CHG_DTM
     *
     * @param chgDtm the value for MW_CM_ADMIN_INFO.CHG_DTM
     *
     * @mbggenerated Wed Sep 26 13:24:52 KST 2012
     */
    public void setChgDtm(Date chgDtm) {
        this.chgDtm = chgDtm;
    }
}