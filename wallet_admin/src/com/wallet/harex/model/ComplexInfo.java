package com.wallet.harex.model;

import java.util.Date;

import com.rocomo.common.model.Common;

@SuppressWarnings("serial")
public class ComplexInfo extends Common {
	
	private String allyStat;		//제휴상태
	private String businessNo;		//사업자번호
	private String mainNumber;		//대표전호
	private String addr;			//주소
	private String addrDetail;		//상세주소
	private String managerName;		//담당자
	private String mobilePhone;		//담당자 전화번호
	private String email;			//담당자email
	private String compType;			//제휴사구분
	private String complexPaymentYn;
	private String compName;  // 제휴사명
	
	private String complexPayMembSave;
	private String complexPayMembUse;
	private String complexPayMembDc;
	private String complexPayCpnYn;
	private String complexPayStampYn;
	
	private String regDtm;
	
	
	public String getComplexPayMembSave() {
		return complexPayMembSave;
	}

	public void setComplexPayMembSave(String complexPayMembSave) {
		this.complexPayMembSave = complexPayMembSave;
	}

	public String getComplexPayMembUse() {
		return complexPayMembUse;
	}

	public void setComplexPayMembUse(String complexPayMembUse) {
		this.complexPayMembUse = complexPayMembUse;
	}

	public String getComplexPayMembDc() {
		return complexPayMembDc;
	}

	public void setComplexPayMembDc(String complexPayMembDc) {
		this.complexPayMembDc = complexPayMembDc;
	}

	public String getComplexPayCpnYn() {
		return complexPayCpnYn;
	}

	public void setComplexPayCpnYn(String complexPayCpnYn) {
		this.complexPayCpnYn = complexPayCpnYn;
	}

	public String getComplexPayStampYn() {
		return complexPayStampYn;
	}

	public void setComplexPayStampYn(String complexPayStampYn) {
		this.complexPayStampYn = complexPayStampYn;
	}

	public String getComplexPaymentYn() {
		return complexPaymentYn;
	}

	public void setComplexPaymentYn(String complexPaymentYn) {
		this.complexPaymentYn = complexPaymentYn;
	}

	public String getCompType() {
		return compType;
	}

	public void setCompType(String compType) {
		this.compType = compType;
	}

	public String getAllyStat() {
		return allyStat;
	}

	public void setAllyStat(String allyStat) {
		this.allyStat = allyStat;
	}

	public String getBusinessNo() {
		return businessNo;
	}
	
	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo == null ? null : businessNo.trim();
	}
	
	public String getMainNumber() {
		return mainNumber;
	}
	
	public void setMainNumber(String mainNumber) {
		this.mainNumber = mainNumber == null ? null : mainNumber.trim().replace("-", "");
	}
	
	public String getAddr() {
		return addr;
	}
	
	public void setAddr(String addr) {
		this.addr = addr == null ? null : addr.trim();
	}
	
	public String getAddrDetail() {
		return addrDetail;
	}
	
	public void setAddrDetail(String addrDetail) {
		this.addrDetail = addrDetail == null ? null : addrDetail.trim();
	}
	
	public String getManagerName() {
		return managerName;
	}
	
	public void setManagerName(String managerName) {
		this.managerName = managerName == null ? null : managerName.trim();
	}
	
	public String getMobilePhone() {
		return mobilePhone;
	}
	
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim().replace("-", "");
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getRegDtm() {
		return regDtm;
	}

	public void setRegDtm(String regDtm) {
		this.regDtm = regDtm;
	}
}
