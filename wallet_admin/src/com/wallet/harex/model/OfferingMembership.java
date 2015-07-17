package com.wallet.harex.model;

import com.rocomo.common.model.Common;

@SuppressWarnings("serial")
public class OfferingMembership extends Common {

	private String dcRate;						//멤버쉽할인율
	private String dcRoundType;				//멤버쉽할인근사계산식
	private String membDcRate;				//멤버쉽할인단위
	private String membLowPay;				//멤버쉽할인최저결제금액
	private String maxDcPrice;				//멤버쉽최대할인금액
	private String saveRateFixedYn;		//적립률고정여부
	private String cashSaveRate;			//현금적립률
	private String cardSaveRate;			//카드적립률
	private String compSaveRate;			//자사적립률
	private String debitSaveRate;			//직불적립률
	private String saveNotice;				//적립률가변
	private String saveRoundType;			//적립근사계산식
	private String dcSavePriceType;		//적립금액구분
	private String saveMinPayPrice;		//적립가능최저결제금액
	private String saveMaxPayPrice;		//적립가능최대결제금액
	private String maxSavePoint;			//적립가능최대포인트
	private String pointUseType;			//포인트사용구분
	private String pointUseUnit;			//포인트사용단위
	private String useRoundType;			//사용근사계산식
	private String fixedRate;					//정률포인트
	private String minPayPrice;				//포인트사용가능최저결제금액
	private String minUsePoint;				//사용가능최저포인트
	private String maxUsePoint;				//사용가능최대포인트
	private String stampUnit;					//스탬프갯수단위
	private String stampMaxNo;				//스탬프판최대갯수
	
	public String getDcRate() {
		return dcRate;
	}
	
	public void setDcRate(String dcRate) {
		this.dcRate = dcRate == null ? null : dcRate.trim();
	}
	
	public String getDcRoundType() {
		return dcRoundType;
	}
	
	public void setDcRoundType(String dcRoundType) {
		this.dcRoundType = dcRoundType == null ? null : dcRoundType.trim();
	}
	
	public String getMembDcRate() {
		return membDcRate;
	}
	
	public void setMembDcRate(String membDcRate) {
		this.membDcRate = membDcRate == null ? null : membDcRate.trim();
	}
	
	public String getMembLowPay() {
		return membLowPay;
	}
	
	public void setMembLowPay(String membLowPay) {
		this.membLowPay = membLowPay == null ? null : membLowPay.trim();
	}
	
	public String getMaxDcPrice() {
		return maxDcPrice;
	}
	
	public void setMaxDcPrice(String maxDcPrice) {
		this.maxDcPrice = maxDcPrice == null ? null : maxDcPrice.trim();
	}
	
	public String getSaveRateFixedYn() {
		return saveRateFixedYn;
	}
	
	public void setSaveRateFixedYn(String saveRateFixedYn) {
		this.saveRateFixedYn = saveRateFixedYn == null ? null : saveRateFixedYn.trim();
	}
	
	public String getCashSaveRate() {
		return cashSaveRate;
	}
	
	public void setCashSaveRate(String cashSaveRate) {
		this.cashSaveRate = cashSaveRate == null ? null : cashSaveRate.trim();
	}
	
	public String getCardSaveRate() {
		return cardSaveRate;
	}
	
	public void setCardSaveRate(String cardSaveRate) {
		this.cardSaveRate = cardSaveRate == null ? null : cardSaveRate.trim();
	}
	
	public String getCompSaveRate() {
		return compSaveRate;
	}
	
	public void setCompSaveRate(String compSaveRate) {
		this.compSaveRate = compSaveRate == null ? null : compSaveRate.trim();
	}
	
	public String getDebitSaveRate() {
		return debitSaveRate;
	}
	
	public void setDebitSaveRate(String debitSaveRate) {
		this.debitSaveRate = debitSaveRate == null ? null : debitSaveRate.trim();
	}
	
	public String getSaveNotice() {
		return saveNotice;
	}
	
	public void setSaveNotice(String saveNotice) {
		this.saveNotice = saveNotice == null ? null : saveNotice.trim();
	}
	
	public String getSaveRoundType() {
		return saveRoundType;
	}
	
	public void setSaveRoundType(String saveRoundType) {
		this.saveRoundType = saveRoundType == null ? null : saveRoundType.trim();
	}
	
	public String getDcSavePriceType() {
		return dcSavePriceType;
	}
	
	public void setDcSavePriceType(String dcSavePriceType) {
		this.dcSavePriceType = dcSavePriceType == null ? null : dcSavePriceType.trim();
	}
	
	public String getSaveMinPayPrice() {
		return saveMinPayPrice;
	}
	
	public void setSaveMinPayPrice(String saveMinPayPrice) {
		this.saveMinPayPrice = saveMinPayPrice == null ? null : saveMinPayPrice.trim();
	}
	
	public String getSaveMaxPayPrice() {
		return saveMaxPayPrice;
	}
	
	public void setSaveMaxPayPrice(String saveMaxPayPrice) {
		this.saveMaxPayPrice = saveMaxPayPrice == null ? null : saveMaxPayPrice.trim();
	}
	
	public String getMaxSavePoint() {
		return maxSavePoint;
	}
	
	public void setMaxSavePoint(String maxSavePoint) {
		this.maxSavePoint = maxSavePoint == null ? null : maxSavePoint.trim();
	}
	
	public String getPointUseType() {
		return pointUseType;
	}
	
	public void setPointUseType(String pointUseType) {
		this.pointUseType = pointUseType == null ? null : pointUseType.trim();
	}
	
	public String getPointUseUnit() {
		return pointUseUnit;
	}
	
	public void setPointUseUnit(String pointUseUnit) {
		this.pointUseUnit = pointUseUnit == null ? null : pointUseUnit.trim();
	}
	
	public String getUseRoundType() {
		return useRoundType;
	}
	
	public void setUseRoundType(String useRoundType) {
		this.useRoundType = useRoundType == null ? null : useRoundType.trim();
	}
	
	public String getFixedRate() {
		return fixedRate;
	}
	
	public void setFixedRate(String fixedRate) {
		this.fixedRate = fixedRate == null ? null : fixedRate.trim();
	}
	
	public String getMinPayPrice() {
		return minPayPrice;
	}
	
	public void setMinPayPrice(String minPayPrice) {
		this.minPayPrice = minPayPrice == null ? null : minPayPrice.trim();
	}
	
	public String getMinUsePoint() {
		return minUsePoint;
	}
	
	public void setMinUsePoint(String minUsePoint) {
		this.minUsePoint = minUsePoint == null ? null : minUsePoint.trim();
	}
	
	public String getMaxUsePoint() {
		return maxUsePoint;
	}
	
	public void setMaxUsePoint(String maxUsePoint) {
		this.maxUsePoint = maxUsePoint == null ? null : maxUsePoint.trim();
	}
	
	public String getStampUnit() {
		return stampUnit;
	}
	
	public void setStampUnit(String stampUnit) {
		this.stampUnit = stampUnit == null ? null : stampUnit.trim();
	}
	
	public String getStampMaxNo() {
		return stampMaxNo;
	}
	
	public void setStampMaxNo(String stampMaxNo) {
		this.stampMaxNo = stampMaxNo == null ? null : stampMaxNo.trim();
	}
}
