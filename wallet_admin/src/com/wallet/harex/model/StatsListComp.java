package com.wallet.harex.model;

import com.rocomo.common.model.Common;

@SuppressWarnings("serial")
public class StatsListComp extends Common {
	
	private String seq;				//번호
	private String compName;
	private String brandName;
	private String regionType;
	private String regionName;
	private String shopName;
	
	private String trDate;			// 거래일자
	private String kShopId;			// KT 가맹점ID
	
	private String trFg;			// 거래구분
	private String cStatus;			// 취소구분
	private String pStatus;			// 거래상태
	private String trStatus;		// 정산상태
	private String ttlCnt;			// 총건수
	private String successCnt;		// 성공건수
	private String failCnt;			// 실패건수
	private String ttlPrice;		// 결제요청금액
	private String reqTtlPrice;		// 결제요청금액
	private String cpnCnt;			// 쿠폰건수
	private String msDcCnt;			// 멤버쉽 할인 건수
	private String msUseCnt;		// 멤버쉽 포인트 사용 건수
	private String msSaveCnt;		// 멤버쉽 적립 건수
	private String msStampCnt;		// 멤버쉽 스탬프 적립 건수
	private String cpnAmt;			// 쿠폰 할인 금액
	private String msDcAmt;			// 멤버쉽 할인 금액
	private String msUseAmt;		// 멤버쉽 포인트 사용 금액
	private String msSaveAmt;		// 멤버쉽 적립 금액
	private String msStampAmt;		// 멤버쉽 스탬프 적립 건수
	
	
	public String getSeq() {
		return seq;
	}
	
	public void setSeq(String seq) {
		this.seq = seq == null ? null : seq.trim();
	}
	
	public String getRegionName() {
		return regionName;
	}
	
	public void setRegionName(String regionName) {
		this.regionName = regionName == null ? null : regionName.trim();
	}
	
	public String getRegionType() {
		return regionType;
	}
	
	public void setRegionType(String regionType) {
		this.regionType = regionType == null ? null : regionType.trim();
	}
	
	public String getCompName() {
		return compName;
	}
	
	public void setCompName(String compName) {
		this.compName = compName == null ? null : compName.trim();
	}
	
	public String getBrandName() {
		return brandName;
	}
	
	public void setBrandName(String brandName) {
		this.brandName = brandName == null ? null : brandName.trim();
	}
	
	public String getShopName() {
		return shopName;
	}
	
	public void setShopName(String shopName) {
		this.shopName = shopName == null ? null : shopName.trim();
	}
	
	public String getTrDate() {
		return trDate;
	}
	
	public void setTrDate(String trDate) {
		this.trDate = trDate == null ? null : trDate.trim();
	}
	
	public String getkShopId() {
		return kShopId;
	}
	
	public void setkShopId(String kShopId) {
		this.kShopId = kShopId == null ? null : kShopId.trim();
	}
	
	public String getTrFg() {
		return trFg;
	}
	
	public void setTrFg(String trFg) {
		this.trFg = trFg == null ? null : trFg.trim();
	}
	
	public String getcStatus() {
		return cStatus;
	}
	
	public void setcStatus(String cStatus) {
		this.cStatus = cStatus == null ? null : cStatus.trim();
	}
	
	public String getpStatus() {
		return pStatus;
	}
	
	public void setpStatus(String pStatus) {
		this.pStatus = pStatus == null ? null : pStatus.trim();
	}
	
	public String getTrStatus() {
		return trStatus;
	}
	
	public void setTrStatus(String trStatus) {
		this.trStatus = trStatus == null ? null : trStatus.trim();
	}
	
	public String getTtlCnt() {
		return ttlCnt;
	}
	
	public void setTtlCnt(String ttlCnt) {
		this.ttlCnt = ttlCnt == null ? null : ttlCnt.trim();
	}
	
	public String getSuccessCnt() {
		return successCnt;
	}
	
	public void setSuccessCnt(String successCnt) {
		this.successCnt = successCnt == null ? null : successCnt.trim();
	}
	
	public String getFailCnt() {
		return failCnt;
	}
	
	public void setFailCnt(String failCnt) {
		this.failCnt = failCnt == null ? null : failCnt.trim();
	}
	
	public String getReqTtlPrice() {
		return reqTtlPrice;
	}
	
	public void setReqTtlPrice(String reqTtlPrice) {
		this.reqTtlPrice = reqTtlPrice == null ? null : reqTtlPrice.trim();
	}
	
	public String getCpnCnt() {
		return cpnCnt;
	}
	
	public void setCpnCnt(String cpnCnt) {
		this.cpnCnt = cpnCnt == null ? null : cpnCnt.trim();
	}
	
	public String getMsDcCnt() {
		return msDcCnt;
	}
	
	public void setMsDcCnt(String msDcCnt) {
		this.msDcCnt = msDcCnt == null ? null : msDcCnt.trim();
	}
	
	public String getMsUseCnt() {
		return msUseCnt;
	}
	
	public void setMsUseCnt(String msUseCnt) {
		this.msUseCnt = msUseCnt == null ? null : msUseCnt.trim();
	}
	
	public String getMsSaveCnt() {
		return msSaveCnt;
	}
	
	public void setMsSaveCnt(String msSaveCnt) {
		this.msSaveCnt = msSaveCnt == null ? null : msSaveCnt.trim();
	}
	
	public String getMsStampCnt() {
		return msStampCnt;
	}
	
	public void setMsStampCnt(String msStampCnt) {
		this.msStampCnt = msStampCnt == null ? null : msStampCnt.trim();
	}
	
	public String getCpnAmt() {
		return cpnAmt;
	}
	
	public void setCpnAmt(String cpnAmt) {
		this.cpnAmt = cpnAmt == null ? null : cpnAmt.trim();
	}
	
	public String getMsDcAmt() {
		return msDcAmt;
	}
	
	public void setMsDcAmt(String msDcAmt) {
		this.msDcAmt = msDcAmt == null ? null : msDcAmt.trim();
	}
	
	public String getMsUseAmt() {
		return msUseAmt;
	}
	
	public void setMsUseAmt(String msUseAmt) {
		this.msUseAmt = msUseAmt == null ? null : msUseAmt.trim();
	}
	
	public String getMsSaveAmt() {
		return msSaveAmt;
	}
	
	public void setMsSaveAmt(String msSaveAmt) {
		this.msSaveAmt = msSaveAmt == null ? null : msSaveAmt.trim();
	}
	
	public String getMsStampAmt() {
		return msStampAmt;
	}
	
	public void setMsStampAmt(String msStampAmt) {
		this.msStampAmt = msStampAmt == null ? null : msStampAmt.trim();
	}

	public String getTtlPrice() {
		return ttlPrice;
	}

	public void setTtlPrice(String ttlPrice) {
		this.ttlPrice = ttlPrice;
	}
}
