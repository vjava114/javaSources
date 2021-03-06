package com.wallet.membership.model;

import org.hibernate.validator.constraints.NotEmpty;

import com.rocomo.common.model.Common;

public class MwMembAgree extends Common {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column memb_agree.memb_id
     *
     * @mbggenerated Tue Aug 14 13:40:21 KST 2012
     */
	@NotEmpty(message="아이디를 입력해주세요")
    private String membId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column memb_agree.idx
     *
     * @mbggenerated Tue Aug 14 13:40:21 KST 2012
     */
    private Integer idx;
    
    private Integer divicoop;
    
    private Integer pointT;
    
    private Integer multiPay;
    
    private Integer multiMem;
    
    private Integer multiPoint;
    
    private Integer multiStamp;

    private Integer registe;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column memb_agree.title
     *
     * @mbggenerated Tue Aug 14 13:40:21 KST 2012
     */
    @NotEmpty(message="약관 제목을 입력해주세요")
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column memb_agree.info
     *
     * @mbggenerated Tue Aug 14 13:40:21 KST 2012
     */
    private String info;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column memb_agree.web
     *
     * @mbggenerated Tue Aug 14 13:40:21 KST 2012
     */
    private String web;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column memb_agree.chk
     *
     * @mbggenerated Tue Aug 14 13:40:21 KST 2012
     */
    private String chk;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column memb_agree.ver
     *
     * @mbggenerated Tue Aug 14 13:40:21 KST 2012
     */
    private String ver;
    
    private String date;
    private String date1;
    private String date2;
    private String date3;

    private String realMoney;
    private String indicateMoney;
    private String commission;
    private String divide;
    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column memb_agree.memb_id
     *
     * @return the value of memb_agree.memb_id
     *
     * @mbggenerated Tue Aug 14 13:40:21 KST 2012
     */
    public String getMembId() {
        return membId;
    }

    public String getDate() {
    		return date;
    }
    public String getDate1() {
  		return date1;
  }
    public String getDate2() {
  		return date2;
  }
    public String getDate3() {
  		return date3;
  }

    public String getRealMoney(){
    	return realMoney;
    }
    
    public String getIndicateMoney(){
    	return indicateMoney;
    }
    
    public String getCommission(){
    	return commission;
    }
    
    public String getDivide() {
			return divide;
		}
    
    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column memb_agree.memb_id
     *
     * @param membId the value for memb_agree.memb_id
     *
     * @mbggenerated Tue Aug 14 13:40:21 KST 2012
     */
    public void setMembId(String membId) {
        this.membId = membId == null ? null : membId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column memb_agree.idx
     *
     * @return the value of memb_agree.idx
     *
     * @mbggenerated Tue Aug 14 13:40:21 KST 2012
     */
    public Integer getIdx() {
        return idx;
    }

    public Integer getDivicoop() {
    	return divicoop;
    }
    public Integer getPointT() {
    	return pointT;
    }
    
    public Integer getMultiPay(){
    	return multiPay;
    }
    
    public Integer getMultiMem() {
    	return multiMem;
    }
    
    public Integer getMultiPoint() {
    	return multiPoint;
    }
    
    public Integer getMultiStamp() {
    	return multiStamp;
    }
    
    public Integer getRegiste() {
    	return registe;
    }
    
    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column memb_agree.idx
     *
     * @param idx the value for memb_agree.idx
     *
     * @mbggenerated Tue Aug 14 13:40:21 KST 2012
     */
    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column memb_agree.title
     *
     * @return the value of memb_agree.title
     *
     * @mbggenerated Tue Aug 14 13:40:21 KST 2012
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column memb_agree.title
     *
     * @param title the value for memb_agree.title
     *
     * @mbggenerated Tue Aug 14 13:40:21 KST 2012
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column memb_agree.info
     *
     * @return the value of memb_agree.info
     *
     * @mbggenerated Tue Aug 14 13:40:21 KST 2012
     */
    public String getInfo() {
        return info;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column memb_agree.info
     *
     * @param info the value for memb_agree.info
     *
     * @mbggenerated Tue Aug 14 13:40:21 KST 2012
     */
    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column memb_agree.web
     *
     * @return the value of memb_agree.web
     *
     * @mbggenerated Tue Aug 14 13:40:21 KST 2012
     */
    public String getWeb() {
        return web;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column memb_agree.web
     *
     * @param web the value for memb_agree.web
     *
     * @mbggenerated Tue Aug 14 13:40:21 KST 2012
     */
    public void setWeb(String web) {
        this.web = web == null ? null : web.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column memb_agree.chk
     *
     * @return the value of memb_agree.chk
     *
     * @mbggenerated Tue Aug 14 13:40:21 KST 2012
     */
    public String getChk() {
        return chk;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column memb_agree.chk
     *
     * @param chk the value for memb_agree.chk
     *
     * @mbggenerated Tue Aug 14 13:40:21 KST 2012
     */
    public void setChk(String chk) {
        this.chk = chk == null ? null : chk.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column memb_agree.ver
     *
     * @return the value of memb_agree.ver
     *
     * @mbggenerated Tue Aug 14 13:40:21 KST 2012
     */
    public String getVer() {
        return ver;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column memb_agree.ver
     *
     * @param ver the value for memb_agree.ver
     *
     * @mbggenerated Tue Aug 14 13:40:21 KST 2012
     */
    public void setVer(String ver) {
        this.ver = ver == null ? null : ver.trim();
    }
}