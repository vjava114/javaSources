package com.wallet.harex.model;

import com.rocomo.common.model.Common;

@SuppressWarnings("serial")
public class TransMstCpn extends Common {
	private Integer seq;   					// ¹øÈ£
	private String acposTid;
	private String cpnId;
	private String pstatus;
	private String code;
	private String msg;
	private String cpnNo;
	private Integer expDcPrice;
	private Integer realDcPrice;
	private Integer calcBasePrice;
	private String apprNo;
	private String apprDate;
	private String apprTime;
	private String orgApprNo;
	private String trDate;
	private String regDttm;
	private String lastDttm;
	
	public final Integer getSeq() {
		return seq;
	}
	public final void setSeq(Integer seq) {
		this.seq = seq;
	}
	public final String getAcposTid() {
		return acposTid;
	}
	public final void setAcposTid(String acposTid) {
		this.acposTid = acposTid;
	}
	public final String getCpnId() {
		return cpnId;
	}
	public final void setCpnId(String cpnId) {
		this.cpnId = cpnId;
	}
	public final String getPstatus() {
		return pstatus;
	}
	public final void setPstatus(String pstatus) {
		this.pstatus = pstatus;
	}
	public final String getCode() {
		return code;
	}
	public final void setCode(String code) {
		this.code = code;
	}
	public final String getMsg() {
		return msg;
	}
	public final void setMsg(String msg) {
		this.msg = msg;
	}
	public final String getCpnNo() {
		return cpnNo;
	}
	public final void setCpnNo(String cpnNo) {
		this.cpnNo = cpnNo;
	}
	public final Integer getExpDcPrice() {
		return expDcPrice;
	}
	public final void setExpDcPrice(Integer expDcPrice) {
		this.expDcPrice = expDcPrice;
	}
	public final Integer getRealDcPrice() {
		return realDcPrice;
	}
	public final void setRealDcPrice(Integer realDcPrice) {
		this.realDcPrice = realDcPrice;
	}
	public final Integer getCalcBasePrice() {
		return calcBasePrice;
	}
	public final void setCalcBasePrice(Integer calcBasePrice) {
		this.calcBasePrice = calcBasePrice;
	}
	public final String getApprNo() {
		return apprNo;
	}
	public final void setApprNo(String apprNo) {
		this.apprNo = apprNo;
	}
	public final String getApprDate() {
		return apprDate;
	}
	public final void setApprDate(String apprDate) {
		this.apprDate = apprDate;
	}
	public final String getApprTime() {
		return apprTime;
	}
	public final void setApprTime(String apprTime) {
		this.apprTime = apprTime;
	}
	public final String getOrgApprNo() {
		return orgApprNo;
	}
	public final void setOrgApprNo(String orgApprNo) {
		this.orgApprNo = orgApprNo;
	}
	public final String getTrDate() {
		return trDate;
	}
	public final void setTrDate(String trDate) {
		this.trDate = trDate;
	}
	public final String getRegDttm() {
		return regDttm;
	}
	public final void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}
	public final String getLastDttm() {
		return lastDttm;
	}
	public final void setLastDttm(String lastDttm) {
		this.lastDttm = lastDttm;
	}

	
	
}
