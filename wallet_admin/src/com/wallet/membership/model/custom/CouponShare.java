/**
 * @author 이경훈
 * */
package com.wallet.membership.model.custom;

import java.util.Date;

import com.wallet.membership.common.DateTime;


public class CouponShare {
	private int rowsPerPage;//페이징 처리시 사용 변수
	
	/**결과 값을 받을수 있는 변수*/
	
	//쿠폰 분담율 저장변수
	private String cpnID;
	private String part;
	private String cpnName;
	private String membName;
	private Date shareRegDtm;
	private String shareRatio;
	private String membId;
	private Date shareChgDtm;
	private String shareChgUser;
	private String bulkYn;//벌크 쿠폰 등록여부

	private String shStartDays;
	private String shEndDays;

	private String regDtmStr;
	private String chgDtmStr;
	
	private String ValStartDay;	
	private String ValEndDay;	

	private String StartDay;	
	private String EndDay;
	
	private String partnerId;
	private String brandId;
	private String isTargetCpn;

	
	
	//쿠폰 실단가
	private Integer cpnCost;
	//쿠폰 표시단가
	private Integer cpnMarkCost;
	//수수료
	private Integer commission;
	//운영사
	private Integer operCompany;
	//KT
	private Integer kT = 0;
	//제휴사
	private Integer coopCompany;
	//멤버십
	private Integer membership;
	//가맹점
	private Integer memberShop;
	//금융사
	private Integer financeCompany;
	//결제사
	private Integer payCompany;
	//등록자
	private String shareRegUser;
	
	//쿠폰 분담율 검색을 위한 키값 변수
	private String cuponPart;
	private String coponName;
	
	//제휴사 명, 브랜드 명
	private String partnerName;
	private String brandName;

	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	public String getRegDtmStr() {
		return regDtmStr;
	}
	public void setRegDtmStr(String regDtmStr) {
		this.regDtmStr = regDtmStr;
	}
	public String getChgDtmStr() {
		return chgDtmStr;
	}
	public void setChgDtmStr(String chgDtmStr) {
		this.chgDtmStr = chgDtmStr;
	}
	public int getRowsPerPage() {
		return rowsPerPage;
	}
	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}
	public String getCpnID() {
		return cpnID;
	}
	public void setCpnID(String cpnID) {
		this.cpnID = cpnID;
	}
	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		this.part = part;
	}
	public String getCpnName() {
		return cpnName;
	}
	public void setCpnName(String cpnName) {
		this.cpnName = cpnName;
	}
	public String getMembName() {
		return membName;
	}
	public void setMembName(String membName) {
		this.membName = membName;
	}
	public String getShareRatio() {
		return shareRatio;
	}
	public void setShareRatio(String shareRatio) {
		this.shareRatio = shareRatio;
	}
	public String getMembId() {
		return membId;
	}
	public void setMembId(String membId) {
		this.membId = membId;
	}
	public Date getShareChgDtm() {
		return shareChgDtm;
	}
	public void setShareChgDtm(Date shareChgDtm) {
		this.shareChgDtm = shareChgDtm;
	}
	public String getShareChgUser() {
		return shareChgUser;
	}
	public void setShareChgUser(String shareChgUser) {
		this.shareChgUser = shareChgUser;
	}
	public String getBulkYn() {
		return bulkYn;
	}
	public void setBulkYn(String bulkYn) {
		this.bulkYn = bulkYn;
	}
	public Integer getCpnCost() {
		return cpnCost;
	}
	public void setCpnCost(Integer cpnCost) {
		this.cpnCost = cpnCost;
	}
	public Integer getCpnMarkCost() {
		return cpnMarkCost;
	}
	public void setCpnMarkCost(Integer cpnMarkCost) {
		this.cpnMarkCost = cpnMarkCost;
	}
	public Integer getCommission() {
		return commission;
	}
	public void setCommission(Integer commission) {
		this.commission = commission;
	}
	public Integer getOperCompany() {
		return operCompany;
	}
	public void setOperCompany(Integer operCompany) {
		this.operCompany = operCompany;
	}
	public Integer getkT() {
		return kT;
	}
	public void setkT(Integer kT) {
		this.kT = kT;
	}
	public Integer getCoopCompany() {
		return coopCompany;
	}
	public void setCoopCompany(Integer coopCompany) {
		this.coopCompany = coopCompany;
	}
	public Integer getMembership() {
		return membership;
	}
	public void setMembership(Integer membership) {
		this.membership = membership;
	}
	public Integer getMemberShop() {
		return memberShop;
	}
	public void setMemberShop(Integer memberShop) {
		this.memberShop = memberShop;
	}
	public Integer getFinanceCompany() {
		return financeCompany;
	}
	public void setFinanceCompany(Integer financeCompany) {
		this.financeCompany = financeCompany;
	}
	public Integer getPayCompany() {
		return payCompany;
	}
	public void setPayCompany(Integer payCompany) {
		this.payCompany = payCompany;
	}
	public String getShareRegUser() {
		return shareRegUser;
	}
	public void setShareRegUser(String shareRegUser) {
		this.shareRegUser = shareRegUser;
	}
	public String getCuponPart() {
		return cuponPart;
	}
	public void setCuponPart(String cuponPart) {
		this.cuponPart = cuponPart;
	}
	public Date getShareRegDtm() {
		return shareRegDtm;
	}
	public void setShareRegDtm(Date shareRegDtm) {
		this.shareRegDtm = shareRegDtm;
	}
	public String getShStartDays() {
		return shStartDays;
	}
	public void setShStartDays(String shStartDays) {
		this.shStartDays = shStartDays;
	}
	public String getShEndDays() {
		return shEndDays;
	}
	public void setShEndDays(String shEndDays) {
		this.shEndDays = shEndDays;
	}
	public String getCoponName() {
		return coponName;
	}
	public void setCoponName(String coponName) {
		this.coponName = coponName;
	}
	public String getValStartDay() {
		return ValStartDay;
	}
	public void setValStartDay(String valStartDay) {
		ValStartDay = valStartDay;
	}
	public String getValEndDay() {
		return ValEndDay;
	}
	public void setValEndDay(String valEndDay) {
		ValEndDay = valEndDay;
	}
	public String getStartDay() {
		return StartDay;
	}
	public void setStartDay(String startDay) {
		StartDay = startDay;
	}
	public String getEndDay() {
		return EndDay;
	}
	public void setEndDay(String endDay) {
		EndDay = endDay;
	}
	public String getPartnerName() {
		return partnerName;
	}
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getIsTargetCpn() {
		return isTargetCpn;
	}
	public void setIsTargetCpn(String isTargetCpn) {
		this.isTargetCpn = isTargetCpn;
	}


}	