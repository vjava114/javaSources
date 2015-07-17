package com.wallet.harex.model;

import com.rocomo.common.model.Common;

@SuppressWarnings("serial")
public class PromotionList extends Common {
	private Integer seq;
	private String promoName;            // 프로모션명
	private String compName;             // 제휴사
	private String brandName;            // 가맹점    
	private String regionType;           // 지역 ID
	private String regionName;           // 지역명
	private String shopName;             // 가맹점명
	private String bankCode;             //결제사 코드
	private String bankName;             // 결제사명
	private String startDt;              // 프로모션 시작일
	private String endDt;                // 프로모션 종료일
	private String regDttm;              //등록일자
	private String compId;               // 제휴사ID
	private String brandId;              // 브랜드ID
	private String shopId;               // 가맹점ID
	private String promoCd;              // 프로모션 CD
	                                     
	private String  period;              // 개별기간 
	private String  dcGubunCode;         // 할인구분  
	private String  dcGubunName;         // 할인구분명  
	private String  dcTypeCode;          // 할인유형
	private String  dcTypeName;          // 할인유형명
	private String  dcPrice;             
	private String  dcRate;              
	private String  dcGuideTx;           // 할인율 text
	private Integer minPayPrice;         // 최저결제금액
	private Integer maxDcPrice;          // 최대할인금액
	private String  dcCntBaseCode;       // 할인기준
	private String  dcCntBaseName;       // 할인기준명
	private Integer dcCnt;               //  할인횟수
	private String dcCntTx;              //  할인횟수문자열
    
    
    public String getPromoCd() {
		return promoCd;
	}
	public void setPromoCd(String promoCd) {
		this.promoCd = promoCd;
	}
	public String getBrandName() {
    	return brandName;
    }
    public void setBrandName(String brandName) {
    	this.brandName = brandName;
    }
    public Integer getSeq() {
    	return seq;
    }
    public void setSeq(Integer seq) {
    	this.seq = seq;
    }
	public String getPromoName() {
		return promoName;
	}
	public void setPromoName(String promoName) {
		this.promoName = promoName;
	}
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public String getRegionType() {
		return regionType;
	}
	public void setRegionType(String regionType) {
		this.regionType = regionType;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getStartDt() {
		return startDt;
	}
	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}
	public String getEndDt() {
		return endDt;
	}
	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}
	public String getRegDttm() {
		return regDttm;
	}
	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}
	public String getCompId() {
		return compId;
	}
	public void setCompId(String compId) {
		this.compId = compId;
	}
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	
	
	
	
	
	
	
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getDcGubunCode() {
		return dcGubunCode;
	}
	public void setDcGubunCode(String dcGubunCode) {
		this.dcGubunCode = dcGubunCode;
	}
	public String getDcGubunName() {
		return dcGubunName;
	}
	public void setDcGubunName(String dcGubunName) {
		this.dcGubunName = dcGubunName;
	}
	public String getDcTypeCode() {
		return dcTypeCode;
	}
	public void setDcTypeCode(String dcTypeCode) {
		this.dcTypeCode = dcTypeCode;
	}
	public String getDcTypeName() {
		return dcTypeName;
	}
	public void setDcTypeName(String dcTypeName) {
		this.dcTypeName = dcTypeName;
	}
	public Integer getMinPayPrice() {
		return minPayPrice;
	}
	public void setMinPayPrice(Integer minPayPrice) {
		this.minPayPrice = minPayPrice;
	}
	public Integer getMaxDcPrice() {
		return maxDcPrice;
	}
	public void setMaxDcPrice(Integer maxDcPrice) {
		this.maxDcPrice = maxDcPrice;
	}
	public String getDcCntBaseCode() {
		return dcCntBaseCode;
	}
	public void setDcCntBaseCode(String dcCntBaseCode) {
		this.dcCntBaseCode = dcCntBaseCode;
	}
	public String getDcCntBaseName() {
		return dcCntBaseName;
	}
	public void setDcCntBaseName(String dcCntBaseName) {
		this.dcCntBaseName = dcCntBaseName;
	}
	public Integer getDcCnt() {
		return dcCnt;
	}
	public void setDcCnt(Integer dcCnt) {
		this.dcCnt = dcCnt;
	}
	public String getDcPrice() {
		return dcPrice;
	}
	public void setDcPrice(String dcPrice) {
		this.dcPrice = dcPrice;
	}
	public String getDcRate() {
		return dcRate;
	}
	public void setDcRate(String dcRate) {
		this.dcRate = dcRate;
	}
	public String getDcGuideTx() {
		return dcGuideTx;
	}
	public void setDcGuideTx(String dcGuideTx) {
		this.dcGuideTx = dcGuideTx;
	}
	public String getDcCntTx() {
		return dcCntTx;
	}
	public void setDcCntTx(String dcCntTx) {
		this.dcCntTx = dcCntTx;
	}
	
	
	
	
	
	
	

}
