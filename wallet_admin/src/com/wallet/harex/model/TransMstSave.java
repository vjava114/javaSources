package com.wallet.harex.model;

import com.rocomo.common.model.Common;

@SuppressWarnings("serial")
public class TransMstSave extends Common {
	private Integer seq;   					// ¹øÈ£
	private String acposTid;
	private String membId;
	private String code;
	private String pstatus;
	private String msg;
	private String membCardNo;
	private Integer expSavePoint;
	private Integer realSavePoint;
	private Integer accumPoint;
	private Integer usablePoint;
	private String apprNo;
	private String apprDate;
	private String apprTime;
	private String orgApprNo;
	private String trDate;
	private String selfMemYn;
	private String compId;
	private String brdId;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPstatus() {
		return pstatus;
	}
	public void setPstatus(String pstatus) {
		this.pstatus = pstatus;
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
	public Integer getExpSavePoint() {
		return expSavePoint;
	}
	public void setExpSavePoint(Integer expSavePoint) {
		this.expSavePoint = expSavePoint;
	}
	public Integer getRealSavePoint() {
		return realSavePoint;
	}
	public void setRealSavePoint(Integer realSavePoint) {
		this.realSavePoint = realSavePoint;
	}
	public Integer getAccumPoint() {
		return accumPoint;
	}
	public void setAccumPoint(Integer accumPoint) {
		this.accumPoint = accumPoint;
	}
	public Integer getUsablePoint() {
		return usablePoint;
	}
	public void setUsablePoint(Integer usablePoint) {
		this.usablePoint = usablePoint;
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
	public String getSelfMemYn() {
		return selfMemYn;
	}
	public void setSelfMemYn(String selfMemYn) {
		this.selfMemYn = selfMemYn;
	}
	public String getCompId() {
		return compId;
	}
	public void setCompId(String compId) {
		this.compId = compId;
	}
	public String getBrdId() {
		return brdId;
	}
	public void setBrdId(String brdId) {
		this.brdId = brdId;
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
