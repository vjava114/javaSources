package com.wallet.harex.model;

import com.rocomo.common.model.Common;

@SuppressWarnings("serial")
public class OfferingPromo extends Common {
	private Integer seq;   					// ¹øÈ£
	private String offerId;
	private String promoCd;
	private Integer dcPrice;
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
	public String getPromoCd() {
		return promoCd;
	}
	public void setPromoCd(String promoCd) {
		this.promoCd = promoCd;
	}
	public Integer getDcPrice() {
		return dcPrice;
	}
	public void setDcPrice(Integer dcPrice) {
		this.dcPrice = dcPrice;
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
