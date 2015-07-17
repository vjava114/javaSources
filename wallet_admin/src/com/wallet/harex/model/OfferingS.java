package com.wallet.harex.model;

import com.rocomo.common.model.Common;

@SuppressWarnings("serial")
public class OfferingS extends Common {
	private Integer seq;   					// ¹øÈ£
	private String acposTid;	
	private String status;	
	private String custId;	
	private String phoneNo;	
	private String tcode;	
	private String serviceId;	
	private String kShopId;	
	private String mocapayTid;	
	private String mocapayTdate;	
	private String mocapayTtime;	
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getTcode() {
		return tcode;
	}
	public void setTcode(String tcode) {
		this.tcode = tcode;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getkShopId() {
		return kShopId;
	}
	public void setkShopId(String kShopId) {
		this.kShopId = kShopId;
	}
	public String getMocapayTid() {
		return mocapayTid;
	}
	public void setMocapayTid(String mocapayTid) {
		this.mocapayTid = mocapayTid;
	}
	public String getMocapayTdate() {
		return mocapayTdate;
	}
	public void setMocapayTdate(String mocapayTdate) {
		this.mocapayTdate = mocapayTdate;
	}
	public String getMocapayTtime() {
		return mocapayTtime;
	}
	public void setMocapayTtime(String mocapayTtime) {
		this.mocapayTtime = mocapayTtime;
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
