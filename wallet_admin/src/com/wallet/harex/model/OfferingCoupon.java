package com.wallet.harex.model;

import com.rocomo.common.model.Common;

@SuppressWarnings("serial")
public class OfferingCoupon extends Common {
	
	private String cpnId;							//����ID
	private String cpnName;
	private String pointDupUsableYn;	//������ߺ� ��뿩��
	private String cpnDupUsableYn;		//�����ߺ���뿩��
	private String cpnDiscType;				//������������
	private String minPayPrice;				//������ ����ϱ� ���� �ּ��� ���� �ݾ�
	private String maxDicPrice;				//�ִ� ���� �ݾ�

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
