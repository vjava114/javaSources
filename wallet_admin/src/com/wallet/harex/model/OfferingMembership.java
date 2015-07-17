package com.wallet.harex.model;

import com.rocomo.common.model.Common;

@SuppressWarnings("serial")
public class OfferingMembership extends Common {

	private String dcRate;						//�����������
	private String dcRoundType;				//��������αٻ����
	private String membDcRate;				//��������δ���
	private String membLowPay;				//������������������ݾ�
	private String maxDcPrice;				//������ִ����αݾ�
	private String saveRateFixedYn;		//��������������
	private String cashSaveRate;			//����������
	private String cardSaveRate;			//ī��������
	private String compSaveRate;			//�ڻ�������
	private String debitSaveRate;			//����������
	private String saveNotice;				//����������
	private String saveRoundType;			//�����ٻ����
	private String dcSavePriceType;		//�����ݾױ���
	private String saveMinPayPrice;		//�����������������ݾ�
	private String saveMaxPayPrice;		//���������ִ�����ݾ�
	private String maxSavePoint;			//���������ִ�����Ʈ
	private String pointUseType;			//����Ʈ��뱸��
	private String pointUseUnit;			//����Ʈ������
	private String useRoundType;			//���ٻ����
	private String fixedRate;					//��������Ʈ
	private String minPayPrice;				//����Ʈ��밡�����������ݾ�
	private String minUsePoint;				//��밡����������Ʈ
	private String maxUsePoint;				//��밡���ִ�����Ʈ
	private String stampUnit;					//��������������
	private String stampMaxNo;				//���������ִ밹��
	
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
