package com.wallet.harex.model;

import com.rocomo.common.model.Common;

@SuppressWarnings("serial")
public class OfferingOrder extends Common {
	private Integer seq;   					// ¹øÈ£
	private String acposTid;
	private String offerOrder;
	private String offerId;
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
	public String getOfferOrder() {
		return offerOrder;
	}
	public void setOfferOrder(String offerOrder) {
		this.offerOrder = offerOrder;
	}
	public String getOfferId() {
		return offerId;
	}
	public void setOfferId(String offerId) {
		this.offerId = offerId;
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
