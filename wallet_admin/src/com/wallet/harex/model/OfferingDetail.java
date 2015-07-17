package com.wallet.harex.model;

import com.rocomo.common.model.Common;

@SuppressWarnings("serial")
public class OfferingDetail extends Common {

	private String acposYn;								//복합결제 여부
	private String cpnYn;									//쿠폰서비스
	private String membDcYn;							//멤버쉽 할인
	private String membUseYn;							//멤버쉽사용(포인트)
	private String membSaveYn;						//멤버쉽 적립
	private String stampSaveYn;						//스탬프 적립
	private String stampUseYn;						//스탬프 사용
	private String cpnOrder;							//쿠폰 우선순위
	private String cpnCalcBaseCd;					//계산기준금액 코드
	private String cpnCalcBaseNm;					//계산기준금액 명칭
	private String cpnFixRatePriority;		//정액/정율 우선순위 코드
	private String cpnFixRatePriorityNm;	//정액/정율 우선순위 명칭
	private String maxDupCnt;							//쿠폰중복 허용 갯수
	private String msDcOrder;							//멤버쉽 할인 우선순위
	private String msCalcBaseCd;					//계산기준금액 코드
	private String msCalcBaseNm;					//계산기준금액 명칭
	private String ollehMsUseYn;					//올레 멤버쉽 사용 여부
	private String mpUseOrder;						//멤버쉽 사용 우선순위
	private String mpCalcBaseCd;					//정율일경우 멤버쉽 포인트 사용 기준 금액 코드
	private String mpCalcBaseNm;					//멤버쉽 포인트 사용 기준 금액 명칭
	private String mpCalcOrder;						//올레 or 멤버쉽 우선 코드
	private String mpCalcOrderNm;					//올레 or 멤버쉽 우선 명칭
	private String msSaveSelfYn;					//멤버십 자체승인
	private String ruleId;								//오퍼링룰 ID
	private String useYn;									//사용여부
	private String kShopId;								//가맹점ID
	private String serviceId;								//결제사ID
	private String serviceNm;								//결제사명
	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceNm() {
		return serviceNm;
	}

	public void setServiceNm(String serviceNm) {
		this.serviceNm = serviceNm;
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
	
	public String getCpnOrder() {
		return cpnOrder;
	}

	public void setCpnOrder(String cpnOrder) {
		this.cpnOrder = cpnOrder == null ? null : cpnOrder.trim();
	}

	public String getCpnCalcBaseCd() {
		return cpnCalcBaseCd;
	}

	public void setCpnCalcBaseCd(String cpnCalcBaseCd) {
		this.cpnCalcBaseCd = cpnCalcBaseCd == null ? null : cpnCalcBaseCd.trim();
	}

	public String getCpnCalcBaseNm() {
		return cpnCalcBaseNm;
	}

	public void setCpnCalcBaseNm(String cpnCalcBaseNm) {
		this.cpnCalcBaseNm = cpnCalcBaseNm == null ? null : cpnCalcBaseNm.trim();
	}

	public String getCpnFixRatePriority() {
		return cpnFixRatePriority;
	}

	public void setCpnFixRatePriority(String cpnFixRatePriority) {
		this.cpnFixRatePriority = cpnFixRatePriority == null ? null : cpnFixRatePriority.trim();
	}

	public String getCpnFixRatePriorityNm() {
		return cpnFixRatePriorityNm;
	}

	public void setCpnFixRatePriorityNm(String cpnFixRatePriorityNm) {
		this.cpnFixRatePriorityNm = cpnFixRatePriorityNm == null ? null : cpnFixRatePriorityNm.trim();
	}

	public String getMaxDupCnt() {
		return maxDupCnt;
	}

	public void setMaxDupCnt(String maxDupCnt) {
		this.maxDupCnt = maxDupCnt == null ? null : maxDupCnt.trim();
	}

	public String getMsDcOrder() {
		return msDcOrder;
	}

	public void setMsDcOrder(String msDcOrder) {
		this.msDcOrder = msDcOrder == null ? null : msDcOrder.trim();
	}

	public String getMsCalcBaseCd() {
		return msCalcBaseCd;
	}

	public void setMsCalcBaseCd(String msCalcBaseCd) {
		this.msCalcBaseCd = msCalcBaseCd == null ? null : msCalcBaseCd.trim();
	}

	public String getMsCalcBaseNm() {
		return msCalcBaseNm;
	}

	public void setMsCalcBaseNm(String msCalcBaseNm) {
		this.msCalcBaseNm = msCalcBaseNm == null ? null : msCalcBaseNm.trim();
	}
	
	public String getOllehMsUseYn() {
		return ollehMsUseYn;
	}

	public void setOllehMsUseYn(String ollehMsUseYn) {
		this.ollehMsUseYn = ollehMsUseYn == null ? null : ollehMsUseYn.trim();
	}

	public String getMpUseOrder() {
		return mpUseOrder;
	}

	public void setMpUseOrder(String mpUseOrder) {
		this.mpUseOrder = mpUseOrder == null ? null : mpUseOrder.trim();
	}

	public String getMpCalcBaseCd() {
		return mpCalcBaseCd;
	}

	public void setMpCalcBaseCd(String mpCalcBaseCd) {
		this.mpCalcBaseCd = mpCalcBaseCd == null ? null : mpCalcBaseCd.trim();
	}

	public String getMpCalcBaseNm() {
		return mpCalcBaseNm;
	}

	public void setMpCalcBaseNm(String mpCalcBaseNm) {
		this.mpCalcBaseNm = mpCalcBaseNm == null ? null : mpCalcBaseNm.trim();
	}

	public String getMpCalcOrder() {
		return mpCalcOrder;
	}

	public void setMpCalcOrder(String mpCalcOrder) {
		this.mpCalcOrder = mpCalcOrder == null ? null : mpCalcOrder.trim();
	}

	public String getMpCalcOrderNm() {
		return mpCalcOrderNm;
	}

	public void setMpCalcOrderNm(String mpCalcOrderNm) {
		this.mpCalcOrderNm = mpCalcOrderNm == null ? null : mpCalcOrderNm.trim();
	}

	public String getMsSaveSelfYn() {
		return msSaveSelfYn;
	}

	public void setMsSaveSelfYn(String msSaveSelfYn) {
		this.msSaveSelfYn = msSaveSelfYn == null ? null : msSaveSelfYn.trim();
	}

	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId == null ? null : ruleId.trim();
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn == null ? null : useYn.trim();
	}

	public String getkShopId() {
		return kShopId;
	}

	public void setkShopId(String kShopId) {
		this.kShopId = kShopId == null ? null : kShopId.trim();
	}
}
