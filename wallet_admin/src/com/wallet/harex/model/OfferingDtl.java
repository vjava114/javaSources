package com.wallet.harex.model;

import com.rocomo.common.model.Common;

@SuppressWarnings("serial")
public class OfferingDtl extends Common {
	private Integer seq;   					// ¹øÈ£
	private String offerId;                  
	private String status;             
	private String offerOrder;        
	private String bankCode;          
	private String acposTid;          
	private Integer ttlPrice;          
	private Integer expCpnDcPrice;   
	private Integer expMembDcPrice;  
	private Integer expMembUsePoint; 
	private Integer expMembSavePoint;
	private Integer expStampSaveCnt; 
	private Integer expPayPrice;      
	private Integer bankMinPrice;     
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOfferOrder() {
		return offerOrder;
	}
	public void setOfferOrder(String offerOrder) {
		this.offerOrder = offerOrder;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getAcposTid() {
		return acposTid;
	}
	public void setAcposTid(String acposTid) {
		this.acposTid = acposTid;
	}
	public Integer getTtlPrice() {
		return ttlPrice;
	}
	public void setTtlPrice(Integer ttlPrice) {
		this.ttlPrice = ttlPrice;
	}
	public Integer getExpCpnDcPrice() {
		return expCpnDcPrice;
	}
	public void setExpCpnDcPrice(Integer expCpnDcPrice) {
		this.expCpnDcPrice = expCpnDcPrice;
	}
	public Integer getExpMembDcPrice() {
		return expMembDcPrice;
	}
	public void setExpMembDcPrice(Integer expMembDcPrice) {
		this.expMembDcPrice = expMembDcPrice;
	}
	public Integer getExpMembUsePoint() {
		return expMembUsePoint;
	}
	public void setExpMembUsePoint(Integer expMembUsePoint) {
		this.expMembUsePoint = expMembUsePoint;
	}
	public Integer getExpMembSavePoint() {
		return expMembSavePoint;
	}
	public void setExpMembSavePoint(Integer expMembSavePoint) {
		this.expMembSavePoint = expMembSavePoint;
	}
	public Integer getExpStampSaveCnt() {
		return expStampSaveCnt;
	}
	public void setExpStampSaveCnt(Integer expStampSaveCnt) {
		this.expStampSaveCnt = expStampSaveCnt;
	}
	public Integer getExpPayPrice() {
		return expPayPrice;
	}
	public void setExpPayPrice(Integer expPayPrice) {
		this.expPayPrice = expPayPrice;
	}
	public Integer getBankMinPrice() {
		return bankMinPrice;
	}
	public void setBankMinPrice(Integer bankMinPrice) {
		this.bankMinPrice = bankMinPrice;
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
