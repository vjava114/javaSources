package com.wallet.membership.model;

import java.util.Date;

import com.rocomo.common.model.Common;

public class MwCmCompany extends Common {
	
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MW_CM_COMPANY.COMP_ID
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	private String partnerId;
	
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MW_CM_COMPANY.COMP_ID
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	private String franchiseId;
	
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MW_CM_COMPANY.COMP_ID
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	private String complexYn;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MW_CM_COMPANY.COMP_ID
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	private String storePartnerName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MW_CM_COMPANY.COMP_ID
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	private String storeFranchiseName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MW_CM_COMPANY.COMP_ID
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	private String allyStatStr;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MW_CM_COMPANY.COMP_ID
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	private String compId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MW_CM_COMPANY.COMP_NAME
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	private String compName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MW_CM_COMPANY.ALLY_STAT
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	private String allyStat;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MW_CM_COMPANY.UPPER_COMP_ID
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	private String upperCompId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MW_CM_COMPANY.COMP_LEVEL_TYPE
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	private String compLevelType;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MW_CM_COMPANY.COMP_TYPE
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	private String compType;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MW_CM_COMPANY.CPN_RTIME_ISSUE_YN
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	private String cpnRtimeIssueYn;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MW_CM_COMPANY.COMPLEX_PAYMENT_YN
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	private String complexPaymentYn;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MW_CM_COMPANY.ZIPCODE
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	private String zipcode;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MW_CM_COMPANY.ADDR
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	private String addr;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MW_CM_COMPANY.ADDR_DETAIL
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	private String addrDetail;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MW_CM_COMPANY.MAIN_NUMBER
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	private String mainNumber;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MW_CM_COMPANY.PHONE_NO
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	private String phoneNo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MW_CM_COMPANY.MOBILE_PHONE
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	private String mobilePhone;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MW_CM_COMPANY.EMAIL
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	private String email;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MW_CM_COMPANY.LIMIT_AMOUNT_DAY
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	private Integer limitAmountDay;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MW_CM_COMPANY.LIMIT_AMOUNT_WEEK
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	private Integer limitAmountWeek;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MW_CM_COMPANY.LIMIT_AMOUNT_MONTH
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	private Integer limitAmountMonth;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MW_CM_COMPANY.LIMIT_AMOUNT_YEAR
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	private Integer limitAmountYear;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MW_CM_COMPANY.BUSINESS_NO
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	private String businessNo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MW_CM_COMPANY.GPS_X
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	private String gpsX;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MW_CM_COMPANY.GPS_Y
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	private String gpsY;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MW_CM_COMPANY.SHOP_INFO_URL
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	private String shopInfoUrl;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MW_CM_COMPANY.COMP_SHOP_ID
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	private String compShopId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MW_CM_COMPANY.MEMB_ID
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	private String membId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MW_CM_COMPANY.REG_USER
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	private String regUser;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MW_CM_COMPANY.REG_DTM
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	private Date regDtm;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MW_CM_COMPANY.CHG_USER
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	private String chgUser;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MW_CM_COMPANY.CHG_DTM
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	private Date chgDtm;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MW_CM_COMPANY.REGION_TYPE
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	private String regionType;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MW_CM_COMPANY.MANAGER_NAME
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	private String managerName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column MW_CM_COMPANY.MEMO
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	private String memo;

	private String ios3_img;
	private String ios4s_img;
	private String android_img;
	private String ios3_card_img;
	private String ios4s_card_img;
	private String android_card_img;
	private String moveId;
	private String multyYn;
	//가맹점에서 브랜드와 제휴사 복합결제 사용여부 
	private String partnerComplexYn;
	private String franchiseComplexYn;
	private String midShopId;
	public String getMidShopId() {
		return midShopId;
	}


	//날짜 
	private String regDtmStr;
	private String chgDtmStr;
	
	private String membName;
		
	
	
	
	
	public String getIos3_card_img() {
		return ios3_card_img;
	}

	public void setIos3_card_img(String ios3_card_img) {
		this.ios3_card_img = ios3_card_img;
	}

	public String getIos4s_card_img() {
		return ios4s_card_img;
	}

	public void setIos4s_card_img(String ios4s_card_img) {
		this.ios4s_card_img = ios4s_card_img;
	}

	public String getAndroid_card_img() {
		return android_card_img;
	}

	public void setAndroid_card_img(String android_card_img) {
		this.android_card_img = android_card_img;
	}

	public String getMultyYn() {
		return multyYn;
	}

	public void setMultyYn(String multyYn) {
		this.multyYn = multyYn;
	}

	public String getMoveId() {
		return moveId;
	}

	public void setMoveId(String moveId) {
		this.moveId = moveId;
	}

	public String getPartnerComplexYn() {
		return partnerComplexYn;
	}

	public void setPartnerComplexYn(String PartnerComplexYn) {
		partnerComplexYn = PartnerComplexYn;
	}

	public String getFranchiseComplexYn() {
		return franchiseComplexYn;
	}

	public void setFranchiseComplexYn(String FranchiseComplexYn) {
		franchiseComplexYn = FranchiseComplexYn;
	}

	public String getComplexYn() {
		return complexYn;
	}
	
	public String getPartnerId() {
		return partnerId;
	}
	
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	
	public void setComplexYn(String complexYn) {
		this.complexYn = complexYn;
	}

	public String getStorePartnerName() {
		return storePartnerName;
	}

	public void setStorePartnerName(String storePartnerName) {
		this.storePartnerName = storePartnerName;
	}

	public String getStoreFranchiseName() {
		return storeFranchiseName;
	}

	public void setStoreFranchiseName(String storeFranchiseName) {
		this.storeFranchiseName = storeFranchiseName;
	}

	
	public String getFranchiseId() {
		return franchiseId;
	}

	public void setFranchiseId(String franchiseId) {
		this.franchiseId = franchiseId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MW_CM_COMPANY.COMP_ID
	 * @return  the value of MW_CM_COMPANY.COMP_ID
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public String getAllyStatStr() {
		return allyStatStr;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MW_CM_COMPANY.COMP_ID
	 * @return  the value of MW_CM_COMPANY.COMP_ID
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public void setAllyStatStr(String allyStatStr) {
		this.allyStatStr = allyStatStr;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MW_CM_COMPANY.COMP_ID
	 * @return  the value of MW_CM_COMPANY.COMP_ID
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public String getCompId() {
		return compId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MW_CM_COMPANY.COMP_ID
	 * @param compId  the value for MW_CM_COMPANY.COMP_ID
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public void setCompId(String compId) {
		this.compId = compId == null ? null : compId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MW_CM_COMPANY.COMP_NAME
	 * @return  the value of MW_CM_COMPANY.COMP_NAME
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public String getCompName() {
		return compName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MW_CM_COMPANY.COMP_NAME
	 * @param compName  the value for MW_CM_COMPANY.COMP_NAME
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public void setCompName(String compName) {
		this.compName = compName == null ? null : compName.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MW_CM_COMPANY.ALLY_STAT
	 * @return  the value of MW_CM_COMPANY.ALLY_STAT
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public String getAllyStat() {
		return allyStat;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MW_CM_COMPANY.ALLY_STAT
	 * @param allyStat  the value for MW_CM_COMPANY.ALLY_STAT
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public void setAllyStat(String allyStat) {
		this.allyStat = allyStat == null ? null : allyStat.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MW_CM_COMPANY.UPPER_COMP_ID
	 * @return  the value of MW_CM_COMPANY.UPPER_COMP_ID
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public String getUpperCompId() {
		return upperCompId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MW_CM_COMPANY.UPPER_COMP_ID
	 * @param upperCompId  the value for MW_CM_COMPANY.UPPER_COMP_ID
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public void setUpperCompId(String upperCompId) {
		this.upperCompId = upperCompId == null ? null : upperCompId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MW_CM_COMPANY.COMP_LEVEL_TYPE
	 * @return  the value of MW_CM_COMPANY.COMP_LEVEL_TYPE
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public String getCompLevelType() {
		return compLevelType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MW_CM_COMPANY.COMP_LEVEL_TYPE
	 * @param compLevelType  the value for MW_CM_COMPANY.COMP_LEVEL_TYPE
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public void setCompLevelType(String compLevelType) {
		this.compLevelType = compLevelType == null ? null : compLevelType.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MW_CM_COMPANY.COMP_TYPE
	 * @return  the value of MW_CM_COMPANY.COMP_TYPE
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public String getCompType() {
		return compType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MW_CM_COMPANY.COMP_TYPE
	 * @param compType  the value for MW_CM_COMPANY.COMP_TYPE
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public void setCompType(String compType) {
		this.compType = compType == null ? null : compType.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MW_CM_COMPANY.CPN_RTIME_ISSUE_YN
	 * @return  the value of MW_CM_COMPANY.CPN_RTIME_ISSUE_YN
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public String getCpnRtimeIssueYn() {
		return cpnRtimeIssueYn;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MW_CM_COMPANY.CPN_RTIME_ISSUE_YN
	 * @param cpnRtimeIssueYn  the value for MW_CM_COMPANY.CPN_RTIME_ISSUE_YN
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public void setCpnRtimeIssueYn(String cpnRtimeIssueYn) {
		this.cpnRtimeIssueYn = cpnRtimeIssueYn == null ? null : cpnRtimeIssueYn
				.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MW_CM_COMPANY.COMPLEX_PAYMENT_YN
	 * @return  the value of MW_CM_COMPANY.COMPLEX_PAYMENT_YN
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public String getComplexPaymentYn() {
		return complexPaymentYn;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MW_CM_COMPANY.COMPLEX_PAYMENT_YN
	 * @param complexPaymentYn  the value for MW_CM_COMPANY.COMPLEX_PAYMENT_YN
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public void setComplexPaymentYn(String complexPaymentYn) {
		this.complexPaymentYn = complexPaymentYn == null ? null : complexPaymentYn
				.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MW_CM_COMPANY.ZIPCODE
	 * @return  the value of MW_CM_COMPANY.ZIPCODE
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public String getZipcode() {
		return zipcode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MW_CM_COMPANY.ZIPCODE
	 * @param zipcode  the value for MW_CM_COMPANY.ZIPCODE
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode == null ? null : zipcode.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MW_CM_COMPANY.ADDR
	 * @return  the value of MW_CM_COMPANY.ADDR
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public String getAddr() {
		return addr;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MW_CM_COMPANY.ADDR
	 * @param addr  the value for MW_CM_COMPANY.ADDR
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public void setAddr(String addr) {
		this.addr = addr == null ? null : addr.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MW_CM_COMPANY.ADDR_DETAIL
	 * @return  the value of MW_CM_COMPANY.ADDR_DETAIL
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public String getAddrDetail() {
		return addrDetail;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MW_CM_COMPANY.ADDR_DETAIL
	 * @param addrDetail  the value for MW_CM_COMPANY.ADDR_DETAIL
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public void setAddrDetail(String addrDetail) {
		this.addrDetail = addrDetail == null ? null : addrDetail.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MW_CM_COMPANY.MAIN_NUMBER
	 * @return  the value of MW_CM_COMPANY.MAIN_NUMBER
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public String getMainNumber() {
		return mainNumber;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MW_CM_COMPANY.MAIN_NUMBER
	 * @param mainNumber  the value for MW_CM_COMPANY.MAIN_NUMBER
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public void setMainNumber(String mainNumber) {
		this.mainNumber = mainNumber == null ? null : mainNumber.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MW_CM_COMPANY.PHONE_NO
	 * @return  the value of MW_CM_COMPANY.PHONE_NO
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public String getPhoneNo() {
		return phoneNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MW_CM_COMPANY.PHONE_NO
	 * @param phoneNo  the value for MW_CM_COMPANY.PHONE_NO
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo == null ? null : phoneNo.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MW_CM_COMPANY.MOBILE_PHONE
	 * @return  the value of MW_CM_COMPANY.MOBILE_PHONE
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public String getMobilePhone() {
		return mobilePhone;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MW_CM_COMPANY.MOBILE_PHONE
	 * @param mobilePhone  the value for MW_CM_COMPANY.MOBILE_PHONE
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MW_CM_COMPANY.EMAIL
	 * @return  the value of MW_CM_COMPANY.EMAIL
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MW_CM_COMPANY.EMAIL
	 * @param email  the value for MW_CM_COMPANY.EMAIL
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MW_CM_COMPANY.LIMIT_AMOUNT_DAY
	 * @return  the value of MW_CM_COMPANY.LIMIT_AMOUNT_DAY
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public Integer getLimitAmountDay() {
		return limitAmountDay;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MW_CM_COMPANY.LIMIT_AMOUNT_DAY
	 * @param limitAmountDay  the value for MW_CM_COMPANY.LIMIT_AMOUNT_DAY
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public void setLimitAmountDay(Integer limitAmountDay) {
		this.limitAmountDay = limitAmountDay;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MW_CM_COMPANY.LIMIT_AMOUNT_WEEK
	 * @return  the value of MW_CM_COMPANY.LIMIT_AMOUNT_WEEK
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public Integer getLimitAmountWeek() {
		return limitAmountWeek;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MW_CM_COMPANY.LIMIT_AMOUNT_WEEK
	 * @param limitAmountWeek  the value for MW_CM_COMPANY.LIMIT_AMOUNT_WEEK
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public void setLimitAmountWeek(Integer limitAmountWeek) {
		this.limitAmountWeek = limitAmountWeek;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MW_CM_COMPANY.LIMIT_AMOUNT_MONTH
	 * @return  the value of MW_CM_COMPANY.LIMIT_AMOUNT_MONTH
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public Integer getLimitAmountMonth() {
		return limitAmountMonth;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MW_CM_COMPANY.LIMIT_AMOUNT_MONTH
	 * @param limitAmountMonth  the value for MW_CM_COMPANY.LIMIT_AMOUNT_MONTH
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public void setLimitAmountMonth(Integer limitAmountMonth) {
		this.limitAmountMonth = limitAmountMonth;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MW_CM_COMPANY.LIMIT_AMOUNT_YEAR
	 * @return  the value of MW_CM_COMPANY.LIMIT_AMOUNT_YEAR
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public Integer getLimitAmountYear() {
		return limitAmountYear;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MW_CM_COMPANY.LIMIT_AMOUNT_YEAR
	 * @param limitAmountYear  the value for MW_CM_COMPANY.LIMIT_AMOUNT_YEAR
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public void setLimitAmountYear(Integer limitAmountYear) {
		this.limitAmountYear = limitAmountYear;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MW_CM_COMPANY.BUSINESS_NO
	 * @return  the value of MW_CM_COMPANY.BUSINESS_NO
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public String getBusinessNo() {
		return businessNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MW_CM_COMPANY.BUSINESS_NO
	 * @param businessNo  the value for MW_CM_COMPANY.BUSINESS_NO
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo == null ? null : businessNo.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MW_CM_COMPANY.GPS_X
	 * @return  the value of MW_CM_COMPANY.GPS_X
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public String getGpsX() {
		return gpsX;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MW_CM_COMPANY.GPS_X
	 * @param gpsX  the value for MW_CM_COMPANY.GPS_X
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public void setGpsX(String gpsX) {
		this.gpsX = gpsX == null ? null : gpsX.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MW_CM_COMPANY.GPS_Y
	 * @return  the value of MW_CM_COMPANY.GPS_Y
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public String getGpsY() {
		return gpsY;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MW_CM_COMPANY.GPS_Y
	 * @param gpsY  the value for MW_CM_COMPANY.GPS_Y
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public void setGpsY(String gpsY) {
		this.gpsY = gpsY == null ? null : gpsY.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MW_CM_COMPANY.SHOP_INFO_URL
	 * @return  the value of MW_CM_COMPANY.SHOP_INFO_URL
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public String getShopInfoUrl() {
		return shopInfoUrl;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MW_CM_COMPANY.SHOP_INFO_URL
	 * @param shopInfoUrl  the value for MW_CM_COMPANY.SHOP_INFO_URL
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public void setShopInfoUrl(String shopInfoUrl) {
		this.shopInfoUrl = shopInfoUrl == null ? null : shopInfoUrl.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MW_CM_COMPANY.COMP_SHOP_ID
	 * @return  the value of MW_CM_COMPANY.COMP_SHOP_ID
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public String getCompShopId() {
		return compShopId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MW_CM_COMPANY.COMP_SHOP_ID
	 * @param compShopId  the value for MW_CM_COMPANY.COMP_SHOP_ID
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public void setCompShopId(String compShopId) {
		this.compShopId = compShopId == null ? null : compShopId.trim();
	}
	
	public void setMidShopId(String midShopId) {
		this.midShopId = midShopId == null ? null : midShopId.trim();
	}


	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MW_CM_COMPANY.MEMB_ID
	 * @return  the value of MW_CM_COMPANY.MEMB_ID
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public String getMembId() {
		return membId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MW_CM_COMPANY.MEMB_ID
	 * @param membId  the value for MW_CM_COMPANY.MEMB_ID
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public void setMembId(String membId) {
		this.membId = membId == null ? null : membId.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MW_CM_COMPANY.REG_USER
	 * @return  the value of MW_CM_COMPANY.REG_USER
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public String getRegUser() {
		return regUser;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MW_CM_COMPANY.REG_USER
	 * @param regUser  the value for MW_CM_COMPANY.REG_USER
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public void setRegUser(String regUser) {
		this.regUser = regUser == null ? null : regUser.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MW_CM_COMPANY.REG_DTM
	 * @return  the value of MW_CM_COMPANY.REG_DTM
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public Date getRegDtm() {
		return regDtm;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MW_CM_COMPANY.REG_DTM
	 * @param regDtm  the value for MW_CM_COMPANY.REG_DTM
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public void setRegDtm(Date regDtm) {
		this.regDtm = regDtm;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MW_CM_COMPANY.CHG_USER
	 * @return  the value of MW_CM_COMPANY.CHG_USER
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public String getChgUser() {
		return chgUser;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MW_CM_COMPANY.CHG_USER
	 * @param chgUser  the value for MW_CM_COMPANY.CHG_USER
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public void setChgUser(String chgUser) {
		this.chgUser = chgUser == null ? null : chgUser.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MW_CM_COMPANY.CHG_DTM
	 * @return  the value of MW_CM_COMPANY.CHG_DTM
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public Date getChgDtm() {
		return chgDtm;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MW_CM_COMPANY.CHG_DTM
	 * @param chgDtm  the value for MW_CM_COMPANY.CHG_DTM
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public void setChgDtm(Date chgDtm) {
		this.chgDtm = chgDtm;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MW_CM_COMPANY.REGION_TYPE
	 * @return  the value of MW_CM_COMPANY.REGION_TYPE
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public String getRegionType() {
		return regionType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MW_CM_COMPANY.REGION_TYPE
	 * @param regionType  the value for MW_CM_COMPANY.REGION_TYPE
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public void setRegionType(String regionType) {
		this.regionType = regionType == null ? null : regionType.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MW_CM_COMPANY.MANAGER_NAME
	 * @return  the value of MW_CM_COMPANY.MANAGER_NAME
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public String getManagerName() {
		return managerName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MW_CM_COMPANY.MANAGER_NAME
	 * @param managerName  the value for MW_CM_COMPANY.MANAGER_NAME
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public void setManagerName(String managerName) {
		this.managerName = managerName == null ? null : managerName.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column MW_CM_COMPANY.MEMO
	 * @return  the value of MW_CM_COMPANY.MEMO
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column MW_CM_COMPANY.MEMO
	 * @param memo  the value for MW_CM_COMPANY.MEMO
	 * @mbggenerated  Thu Sep 06 14:03:53 KST 2012
	 */
	public void setMemo(String memo) {
		this.memo = memo == null ? null : memo.trim();
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

	public String getIos3_img() {
		return ios3_img;
	}

	public void setIos3_img(String ios3_img) {
		this.ios3_img = ios3_img;
	}

	public String getIos4s_img() {
		return ios4s_img;
	}

	public void setIos4s_img(String ios4s_img) {
		this.ios4s_img = ios4s_img;
	}

	public String getAndroid_img() {
		return android_img;
	}

	public void setAndroid_img(String android_img) {
		this.android_img = android_img;
	}

	public String getMembName() {
		return membName;
	}

	public void setMembName(String membName) {
		this.membName = membName;
	}
}