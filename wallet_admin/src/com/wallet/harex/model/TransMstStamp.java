package com.wallet.harex.model;

import com.rocomo.common.model.Common;

@SuppressWarnings("serial")
public class TransMstStamp extends Common {
	private Integer seq;   					// ¹øÈ£
	private String acposTid;                        
	private String membId;        
	private String pstatus;        
	private String code;           
	private String msg;            
	private String membCardNo;   
	private Integer expSavePoint; 
	private Integer realSavePoint;
	private Integer accumStampCnt;    
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
	public final String getMembId() {
		return membId;
	}
	public final void setMembId(String membId) {
		this.membId = membId;
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
	public final String getMembCardNo() {
		return membCardNo;
	}
	public final void setMembCardNo(String membCardNo) {
		this.membCardNo = membCardNo;
	}
	public final Integer getExpSavePoint() {
		return expSavePoint;
	}
	public final void setExpSavePoint(Integer expSavePoint) {
		this.expSavePoint = expSavePoint;
	}
	public final Integer getRealSavePoint() {
		return realSavePoint;
	}
	public final void setRealSavePoint(Integer realSavePoint) {
		this.realSavePoint = realSavePoint;
	}
	public final Integer getAccumStampCnt() {
		return accumStampCnt;
	}
	public final void setAccumStampCnt(Integer accumStampCnt) {
		this.accumStampCnt = accumStampCnt;
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
	public final String getSelfMemYn() {
		return selfMemYn;
	}
	public final void setSelfMemYn(String selfMemYn) {
		this.selfMemYn = selfMemYn;
	}
	public final String getCompId() {
		return compId;
	}
	public final void setCompId(String compId) {
		this.compId = compId;
	}
	public final String getBrdId() {
		return brdId;
	}
	public final void setBrdId(String brdId) {
		this.brdId = brdId;
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
