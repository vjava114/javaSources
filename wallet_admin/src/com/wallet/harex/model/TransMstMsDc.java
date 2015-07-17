package com.wallet.harex.model;

import com.rocomo.common.model.Common;

@SuppressWarnings("serial")
public class TransMstMsDc extends Common {
	private Integer seq;   					// ¹øÈ£
	private String acposTid;          
	private String membId;
	private String pstatus;
	private String code;
	private String msg;
	private String membCardNo;       
	private Integer expMembDcPrice;  
	private Integer realMembDcPrice; 
	private Integer calcBasePrice;    
	private String apprNo;
	private String apprDate;
	private String apprTime;
	private String orgApprNo;        
	private String trDate;
	private String selfMembYn;       
	private String compId;
	private String brandId;
	private String regDttm;
	private String lastDttm;
	
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getAcposTid() {
		return acposTid;
	}
	public void setAcposTid(String acposTid) {
		this.acposTid = acposTid;
	}
	public String getMembId() {
		return membId;
	}
	public void setMembId(String membId) {
		this.membId = membId;
	}
	public String getPstatus() {
		return pstatus;
	}
	public void setPstatus(String pstatus) {
		this.pstatus = pstatus;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getMembCardNo() {
		return membCardNo;
	}
	public void setMembCardNo(String membCardNo) {
		this.membCardNo = membCardNo;
	}
	public Integer getExpMembDcPrice() {
		return expMembDcPrice;
	}
	public void setExpMembDcPrice(Integer expMembDcPrice) {
		this.expMembDcPrice = expMembDcPrice;
	}
	public Integer getRealMembDcPrice() {
		return realMembDcPrice;
	}
	public void setRealMembDcPrice(Integer realMembDcPrice) {
		this.realMembDcPrice = realMembDcPrice;
	}
	public Integer getCalcBasePrice() {
		return calcBasePrice;
	}
	public void setCalcBasePrice(Integer calcBasePrice) {
		this.calcBasePrice = calcBasePrice;
	}
	public String getApprNo() {
		return apprNo;
	}
	public void setApprNo(String apprNo) {
		this.apprNo = apprNo;
	}
	public String getApprDate() {
		return apprDate;
	}
	public void setApprDate(String apprDate) {
		this.apprDate = apprDate;
	}
	public String getApprTime() {
		return apprTime;
	}
	public void setApprTime(String apprTime) {
		this.apprTime = apprTime;
	}
	public String getOrgApprNo() {
		return orgApprNo;
	}
	public void setOrgApprNo(String orgApprNo) {
		this.orgApprNo = orgApprNo;
	}
	public String getTrDate() {
		return trDate;
	}
	public void setTrDate(String trDate) {
		this.trDate = trDate;
	}
	public String getSelfMembYn() {
		return selfMembYn;
	}
	public void setSelfMembYn(String selfMembYn) {
		this.selfMembYn = selfMembYn;
	}
	public String getCompId() {
		return compId;
	}
	public void setCompId(String compId) {
		this.compId = compId;
	}
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	public String getRegDttm() {
		return regDttm;
	}
	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}
	public String getLastDttm() {
		return lastDttm;
	}
	public void setLastDttm(String lastDttm) {
		this.lastDttm = lastDttm;
	}
	
}
