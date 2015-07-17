package com.wallet.harex.model;

import com.rocomo.common.model.Common;

@SuppressWarnings("serial")
public class OfferingList extends Common {
	private String seq;						//번호
	private String compId;					//제휴사ID
	private String compName;				//제휴사명
	private String shopId;					//가맹점ID
	private String shopName;				//가맹점명
	private String brandId;					//브랜드ID
	private String brandName;				//브랜드명
	private String regionCode;				//지역코드
	private String regionName;				//지역명
	private String cpnOrder;				//쿠폰
	private String dcOrder;					//멤버쉽할인
	private String useOrder;				//멤버쉽포인트사용
	private String ollehMsUseYn;			//올레멤버십
	private String stampSaveYn;				//스탬프적립
	private String stampUseYn;				//스탬프사용
	private String regDttm;					//오퍼링조건 등록일
	private String ruleId;					//오퍼링룰ID
	private String useYn;
	private String serviceId;				// 결제사서비스
	
	
	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq == null ? null : seq.trim();
	}

	public String getCompId() {
		return compId;
	}

	public void setCompId(String compId) {
		this.compId = compId == null ? null : compId.trim();
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName == null ? null : compName.trim();
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId == null ? null : shopId.trim();
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName == null ? null : shopName.trim();
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId == null ? null : brandId.trim();
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName == null ? null : brandName.trim();
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode == null ? null : regionCode.trim();
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName == null ? null : regionName.trim();
	}
	
	public String getCpnOrder() {
		return cpnOrder;
	}

	public void setCpnOrder(String cpnOrder) {
		this.cpnOrder = cpnOrder == null ? null : cpnOrder.trim();
	}

	public String getDcOrder() {
		return dcOrder;
	}

	public void setDcOrder(String dcOrder) {
		this.dcOrder = dcOrder == null ? null : dcOrder.trim();
	}

	public String getUseOrder() {
		return useOrder;
	}

	public void setUseOrder(String useOrder) {
		this.useOrder = useOrder == null ? null : useOrder.trim();
	}

	public String getOllehMsUseYn() {
		return ollehMsUseYn;
	}

	public void setOllehMsUseYn(String ollehMsUseYn) {
		this.ollehMsUseYn = ollehMsUseYn == null ? null : ollehMsUseYn.trim();
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

	public String getRegDttm() {
		return regDttm;
	}

	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm == null ? null : regDttm.trim();
	}

	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId == null ? null : ruleId.trim();
	}
}