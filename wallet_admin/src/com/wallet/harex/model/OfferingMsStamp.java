package com.wallet.harex.model;

import com.rocomo.common.model.Common;

@SuppressWarnings("serial")
public class OfferingMsStamp extends Common {
	private Integer seq;   					// ¹øÈ£
	private String offerId;
	private String membId;
	private String membName;
	private String membCardNo;
	private String selfMemYn;
	private String compId;
	private String brdId;
	private String stampSvcYn;
	private String stampSvcType;
	private Integer stampUnit;
	private Integer usableStampCnt;
	private Integer accumStampCnt;
	private String regDttm;
	private String lastDttm;
	
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getOfferId() {
		return offerId;
	}
	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}
	public String getMembId() {
		return membId;
	}
	public void setMembId(String membId) {
		this.membId = membId;
	}
	public String getMembName() {
		return membName;
	}
	public void setMembName(String membName) {
		this.membName = membName;
	}
	public String getMembCardNo() {
		return membCardNo;
	}
	public void setMembCardNo(String membCardNo) {
		this.membCardNo = membCardNo;
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
	public String getStampSvcYn() {
		return stampSvcYn;
	}
	public void setStampSvcYn(String stampSvcYn) {
		this.stampSvcYn = stampSvcYn;
	}
	public String getStampSvcType() {
		return stampSvcType;
	}
	public void setStampSvcType(String stampSvcType) {
		this.stampSvcType = stampSvcType;
	}
	public Integer getStampUnit() {
		return stampUnit;
	}
	public void setStampUnit(Integer stampUnit) {
		this.stampUnit = stampUnit;
	}
	public Integer getUsableStampCnt() {
		return usableStampCnt;
	}
	public void setUsableStampCnt(Integer usableStampCnt) {
		this.usableStampCnt = usableStampCnt;
	}
	public Integer getAccumStampCnt() {
		return accumStampCnt;
	}
	public void setAccumStampCnt(Integer accumStampCnt) {
		this.accumStampCnt = accumStampCnt;
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
