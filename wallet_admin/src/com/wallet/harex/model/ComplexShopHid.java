package com.wallet.harex.model;

import com.rocomo.common.model.Common;

@SuppressWarnings("serial")
public class ComplexShopHid extends Common {
	private String seq;
	private String kShopId;
	private String shopName;
	private String pkShopId;
	private String brandName;
	private String regionName;
	private String hShopId;
	private String status;
	private String acposYn;
	private String cpnYn;
	private String membDcYn;
	private String membUseYn;
	private String membSaveYn;
	private String stampSaveYn;
	private String stampUseYn;
	private String sendDttm;
	private String recvDttm;
	private String regId;
	private String regDttm;
	
	private String ollehUseYn;
	private String ollehDcYn;
	private String ollehSaveYn;
	
	
	public String getSeq() {
		return seq;
	}
	
	public void setSeq(String seq) {
		this.seq = seq == null ? null : seq.trim();
	}
	
	public String getkShopId() {
		return kShopId;
	}
	
	public void setkShopId(String kShopId) {
		this.kShopId = kShopId == null ? null : kShopId.trim();
	}
	
	public String getShopName() {
		return shopName;
	}
	
	public void setShopName(String shopName) {
		this.shopName = shopName == null ? null : shopName.trim();
	}
	
	public String getPkShopId() {
		return pkShopId;
	}
	
	public void setPkShopId(String pkShopId) {
		this.pkShopId = pkShopId == null ? null : pkShopId.trim();
	}
	
	public String getBrandName() {
		return brandName;
	}
	
	public void setBrandName(String brandName) {
		this.brandName = brandName == null ? null : brandName.trim();
	}
	
	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName == null ? null : regionName.trim();
	}

	public String gethShopId() {
		return hShopId;
	}

	public void sethShopId(String hShopId) {
		this.hShopId = hShopId == null ? null : hShopId.trim();
	}

	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}
	
	public String getAcposYn() {
		return acposYn;
	}
	
	public void setAcposYn(String acposYn) {
		this.acposYn = acposYn == null ? null : acposYn.trim();
	}
	
	public String getCpnYn() {
		return cpnYn;
	}
	
	public void setCpnYn(String cpnYn) {
		this.cpnYn = cpnYn == null ? null : cpnYn.trim();
	}
	
	public String getMembDcYn() {
		return membDcYn;
	}
	
	public void setMembDcYn(String membDcYn) {
		this.membDcYn = membDcYn == null ? null : membDcYn.trim();
	}
	
	public String getMembUseYn() {
		return membUseYn;
	}
	
	public void setMembUseYn(String membUseYn) {
		this.membUseYn = membUseYn == null ? null : membUseYn.trim();
	}
	
	public String getMembSaveYn() {
		return membSaveYn;
	}
	
	public void setMembSaveYn(String membSaveYn) {
		this.membSaveYn = membSaveYn == null ? null : membSaveYn.trim();
	}
	
	public String getStampSaveYn() {
		return stampSaveYn;
	}
	
	public void setStampSaveYn(String stampSaveYn) {
		this.stampSaveYn = stampSaveYn == null ? null : stampSaveYn.trim();
	}
	
	public String getStampUseYn() {
		return stampUseYn;
	}
	
	public void setStampUseYn(String stampUseYn) {
		this.stampUseYn = stampUseYn == null ? null : stampUseYn.trim();
	}
	
	public String getSendDttm() {
		return sendDttm;
	}
	
	public void setSendDttm(String sendDttm) {
		this.sendDttm = sendDttm == null ? null : sendDttm.trim();
	}
	
	public String getRecvDttm() {
		return recvDttm;
	}
	
	public void setRecvDttm(String recvDttm) {
		this.recvDttm = recvDttm == null ? null : recvDttm.trim();
	}
	
	public String getRegId() {
		return regId;
	}
	
	public void setRegId(String regId) {
		this.regId = regId == null ? null : regId.trim();
	}
	
	public String getRegDttm() {
		return regDttm;
	}
	
	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm == null ? null : regDttm.trim();
	}

	public String getOllehUseYn() {
		return ollehUseYn;
	}

	public void setOllehUseYn(String ollehUseYn) {
		this.ollehUseYn = ollehUseYn;
	}

	public String getOllehDcYn() {
		return ollehDcYn;
	}

	public void setOllehDcYn(String ollehDcYn) {
		this.ollehDcYn = ollehDcYn;
	}

	public String getOllehSaveYn() {
		return ollehSaveYn;
	}

	public void setOllehSaveYn(String ollehSaveYn) {
		this.ollehSaveYn = ollehSaveYn;
	}
	
}
