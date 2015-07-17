package com.wallet.harex.model;

import com.rocomo.common.model.Common;

@SuppressWarnings("serial")
public class OfferingDetail extends Common {

	private String acposYn;								//���հ��� ����
	private String cpnYn;									//��������
	private String membDcYn;							//����� ����
	private String membUseYn;							//��������(����Ʈ)
	private String membSaveYn;						//����� ����
	private String stampSaveYn;						//������ ����
	private String stampUseYn;						//������ ���
	private String cpnOrder;							//���� �켱����
	private String cpnCalcBaseCd;					//�����رݾ� �ڵ�
	private String cpnCalcBaseNm;					//�����رݾ� ��Ī
	private String cpnFixRatePriority;		//����/���� �켱���� �ڵ�
	private String cpnFixRatePriorityNm;	//����/���� �켱���� ��Ī
	private String maxDupCnt;							//�����ߺ� ��� ����
	private String msDcOrder;							//����� ���� �켱����
	private String msCalcBaseCd;					//�����رݾ� �ڵ�
	private String msCalcBaseNm;					//�����رݾ� ��Ī
	private String ollehMsUseYn;					//�÷� ����� ��� ����
	private String mpUseOrder;						//����� ��� �켱����
	private String mpCalcBaseCd;					//�����ϰ�� ����� ����Ʈ ��� ���� �ݾ� �ڵ�
	private String mpCalcBaseNm;					//����� ����Ʈ ��� ���� �ݾ� ��Ī
	private String mpCalcOrder;						//�÷� or ����� �켱 �ڵ�
	private String mpCalcOrderNm;					//�÷� or ����� �켱 ��Ī
	private String msSaveSelfYn;					//����� ��ü����
	private String ruleId;								//���۸��� ID
	private String useYn;									//��뿩��
	private String kShopId;								//������ID
	private String serviceId;								//������ID
	private String serviceNm;								//�������
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
