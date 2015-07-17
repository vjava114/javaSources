package com.wallet.harex.model;

import com.rocomo.common.model.Common;

@SuppressWarnings("serial")
public class OfferingCoupon extends Common {
	
	private String cpnId;							//쿠폰ID
	private String cpnName;
	private String pointDupUsableYn;	//멤버쉽중복 사용여부
	private String cpnDupUsableYn;		//쿠폰중복사용여부
	private String cpnDiscType;				//쿠폰할인유형
	private String minPayPrice;				//쿠폰을 사용하기 위해 최소한 결제 금액
	private String maxDicPrice;				//최대 할인 금액

	public String getCpnId() {
		return cpnId;
	}
	
	public void setCpnId(String cpnId) {
		this.cpnId = cpnId == null ? null : cpnId.trim();
	}
	

	public String getPointDupUsableYn() {
		return pointDupUsableYn;
	}
	
	public void setPointDupUsableYn(String pointDupUsableYn) {
		this.pointDupUsableYn = pointDupUsableYn == null ? null : pointDupUsableYn.trim();
	}
	
	public String getCpnDupUsableYn() {
		return cpnDupUsableYn;
	}
	
	public void setCpnDupUsableYn(String cpnDupUsableYn) {
		this.cpnDupUsableYn = cpnDupUsableYn == null ? null : cpnDupUsableYn.trim();
	}
	
	public String getCpnDiscType() {
		return cpnDiscType;
	}
	
	public void setCpnDiscType(String cpnDiscType) {
		this.cpnDiscType = cpnDiscType == null ? null : cpnDiscType.trim();
	}
	
	public String getMinPayPrice() {
		return minPayPrice;
	}
	
	public void setMinPayPrice(String minPayPrice) {
		this.minPayPrice = minPayPrice == null ? null : minPayPrice.trim();
	}
	
	public String getMaxDicPrice() {
		return maxDicPrice;
	}
	
	public void setMaxDicPrice(String maxDicPrice) {
		this.maxDicPrice = maxDicPrice == null ? null : maxDicPrice.trim();
	}

	public String getCpnName() {
		return cpnName;
	}

	public void setCpnName(String cpnName) {
		this.cpnName = cpnName;
	}
	
}
