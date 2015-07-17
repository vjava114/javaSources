package com.wallet.harex.model;

import com.rocomo.common.model.Common;

@SuppressWarnings("serial")
public class PromotionInfo extends Common {
	private String  bankCode;   //  ������code   
	private String  bankName;   // �������	
	private String  promoName;  // ���θ�Ǹ�   
	private String  startDt;       // ���θ�ǽ�����
	private String  endDt;        //  ���θ��������
	private String  period;        // �����Ⱓ 
	private String  dcGubunCode;//  ���α���  
	private String  dcGubunName;//  ���α��и�  
	private String  dcTypeCode;  //   ��������
	private String  dcTypeName;  //   ����������
	private String  dcPrice;
	private String  dcRate;
	private String  dcGuideTx;     // ������ text
	private Integer minPayPrice;   // ���������ݾ�
	private Integer maxDcPrice;    // �ִ����αݾ�

	private String  dcCntBaseCode; // ���α���
	private String  dcCntBaseName; // ���α��ظ�
	private Integer dcCnt; //  ����Ƚ��    
	private String dcCntTx;       //  ����Ƚ�����ڿ�
	private String dcUnit; // �������αٻ���	����
	private String roundType;// �������αٻ���
	private String promoCode;// ���θ�� �ڵ�
	
	private String code;  // code id
	private String name;  // code ��
	private String serviceId; // ���� ���񽺻� code
	
	private String startDtInd;       // �������θ�� ������
	private String endDtInd;        //  �������θ�� ������
	private String promoNameInd; //  �������θ�Ǹ�
	private String shopId; // ������ ID
	private String shopName; // ��������
	
	private String compId;  // ���޻�ID
	private String compName; // ���޻��
	private String brandId; // �귣�� ID
	private String brandName; // �귣���
	private String serviceName;// ���񽺸�
	private String promoDate; // ���θ�� ����-������
	
	
	public String getPromoDate() {
		return promoDate;
	}
	public void setPromoDate(String promoDate) {
		this.promoDate = promoDate;
	}
	public String getStartDtInd() {
		return startDtInd;
	}
	public void setStartDtInd(String startDtInd) {
		this.startDtInd = startDtInd;
	}
	public String getEndDtInd() {
		return endDtInd;
	}
	public void setEndDtInd(String endDtInd) {
		this.endDtInd = endDtInd;
	}
	public String getPromoNameInd() {
		return promoNameInd;
	}
	public void setPromoNameInd(String promoNameInd) {
		this.promoNameInd = promoNameInd;
	}
	public String getPromoCode() {
		return promoCode;
	}
	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDcUnit() {
		return dcUnit;
	}
	public void setDcUnit(String dcUnit) {
		this.dcUnit = dcUnit;
	}
	public String getRoundType() {
		return roundType;
	}
	public void setRoundType(String roundType) {
		this.roundType = roundType;
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
	public String getPromoName() {
		return promoName;
	}
	public void setPromoName(String promoName) {
		this.promoName = promoName;
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
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getCompId() {
		return compId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public void setCompId(String compId) {
		this.compId = compId;
	}
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getDcGubunCode() {
		return dcGubunCode;
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
	public String getDcCntTx() {
		return dcCntTx;
	}
	public void setDcCntTx(String dcCntTx) {
		this.dcCntTx = dcCntTx;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	
}
