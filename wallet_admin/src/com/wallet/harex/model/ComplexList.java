package com.wallet.harex.model;

import com.rocomo.common.model.Common;

@SuppressWarnings("serial")
public class ComplexList extends Common {
    private Integer seq;
    private String compName;
    private String compCode;
    private String regionCode;
    private String regionName;
    private String shopId;
    private String shopName;
    private String compPayCpnYn;
    private String compPayMembDc;
    private String compPayMembUse;
    private String compPayMembSave;     
    private String compPayStampSaveYn; 		// 적립
    private String compPayStampYn;      	// 사용
    private String regDtm;
    private String offerType;
    private String mappingType;
    
    
	public String getMappingType() {
		return mappingType;
	}
	
	public void setMappingType(String mappingType) {
		this.mappingType = mappingType;
	}
	
	public Integer getSeq() {
		return seq;
	}
	
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	
	public String getCompName() {
		return compName;
	}
	
	public void setCompName(String compName) {
		this.compName = compName;
	}
	
	public String getCompCode() {
		return compCode;
	}
	
	public void setCompCode(String compCode) {
		this.compCode = compCode;
	}
	
	public String getRegionCode() {
		return regionCode;
	}
	
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getCompPayCpnYn() {
		return compPayCpnYn;
	}
	public void setCompPayCpnYn(String compPayCpnYn) {
		this.compPayCpnYn = compPayCpnYn;
	}
	public String getCompPayMembDc() {
		return compPayMembDc;
	}
	public void setCompPayMembDc(String compPayMembDc) {
		this.compPayMembDc = compPayMembDc;
	}
	public String getCompPayMembUse() {
		return compPayMembUse;
	}
	public void setCompPayMembUse(String compPayMembUse) {
		this.compPayMembUse = compPayMembUse;
	}
	public String getCompPayMembSave() {
		return compPayMembSave;
	}
	public void setCompPayMembSave(String compPayMembSave) {
		this.compPayMembSave = compPayMembSave;
	}
	public String getCompPayStampSaveYn() {
		return compPayStampSaveYn;
	}
	public void setCompPayStampSaveYn(String compPayStampSaveYn) {
		this.compPayStampSaveYn = compPayStampSaveYn;
	}
	public String getCompPayStampYn() {
		return compPayStampYn;
	}
	public void setCompPayStampYn(String compPayStampYn) {
		this.compPayStampYn = compPayStampYn;
	}
	public String getRegDtm() {
		return regDtm;
	}
	public void setRegDtm(String regDtm) {
		this.regDtm = regDtm;
	}
	public String getOfferType() {
		return offerType;
	}
	public void setOfferType(String offerType) {
		this.offerType = offerType;
	}             

}
