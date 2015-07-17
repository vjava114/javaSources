/**
 * @author 이경훈
 * */
package com.wallet.membership.model.custom;

import java.util.Date;


public class BulkCoupon {
	private int rowsPerPage;//페이징 처리시 사용 변수
	
	/**결과 값을 받을수 있는 변수*/
	
	//벌크 쿠폰 저장변수
	private String CpnID;
	private String Part;
	private String Name;
	private String SortV;
	private String TotCnt;
	private String IssueCnt;
	private String CpnIssueStat;
	private String RegDtm;
	private String ValStartDay;	
	private String ValEndDay;	
	private String StartDay;	
	private String EndDay;
	private String CpnName;
	private String RegUser;
	private Date ChgDtm;
	private String ChgUser;
	private String regDtmStr;
	private String chgDtmStr;
	
	private String bulkYn;//벌크 쿠폰 등록여부
	
	//벌크쿠폰 검색을 위한 키값 변수
	private String cuponPart;
	private String membId;
	private String coponName;
	private String shStartDays;
	private String shEndDays;
	
	//벌크쿠폰 발행 정보를 위한 변수
	private String UserID;
	private String AuthName;
	private String Barcode;
	private String RegDay;
	private String membName;
	
	//제휴사 명, 브랜드 명
	private String partnerName;
	private String brandName;
	private String compName;
		
	
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
	public String getCpnName() {
		return CpnName;
	}
	public void setCpnName(String cpnName) {
		CpnName = cpnName;
	}
	public void setCuponPart(String cuponPart) {
		this.cuponPart = cuponPart;
	}
	public String getCuponPart() {
		return cuponPart;
	}
	public void setCoponPart(String CuponPart) {
		cuponPart = CuponPart;
	}
	public String getMembId() {
		return membId;
	}
	public void setMembId(String MembId) {
		membId = MembId;
	}
	public String getCoponName() {
		return coponName;
	}
	public void setCoponName(String CoponName) {
		coponName = CoponName;
	}
	public String getShStartDays() {
		return shStartDays;
	}
	public void setShStartDays(String ShStartDays) {
		shStartDays = ShStartDays;
	}
	public String getShEndDays() {
		return shEndDays;
	}
	public void setShEndDays(String ShEndDays) {
		shEndDays = ShEndDays;
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
	public int getRowsPerPage() {
		return rowsPerPage;
	}
	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}
	public String getCpnID() {
		return CpnID;
	}
	public void setCpnID(String cpnID) {
		CpnID = cpnID;
	}
	public String getPart() {
		return Part;
	}
	public void setPart(String part) {
		Part = part;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getSortV() {
		return SortV;
	}
	public void setSortV(String sortV) {
		SortV = sortV;
	}
	public String getTotCnt() {
		return TotCnt;
	}
	public void setTotCnt(String totCnt) {
		TotCnt = totCnt;
	}
	public String getIssueCnt() {
		return IssueCnt;
	}
	public void setIssueCnt(String issueCnt) {
		IssueCnt = issueCnt;
	}
	public String getCpnIssueStat() {
		return CpnIssueStat;
	}
	public void setCpnIssueStat(String cpnIssueStat) {
		CpnIssueStat = cpnIssueStat;
	}
	public String getRegDtm() {
		return RegDtm;
	}
	public void setRegDtm(String regDtm) {
		RegDtm = regDtm;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getAuthName() {
		return AuthName;
	}
	public void setAuthName(String authName) {
		AuthName = authName;
	}
	public String getBarcode() {
		return Barcode;
	}
	public void setBarcode(String barcode) {
		Barcode = barcode;
	}
	public String getRegDay() {
		return RegDay;
	}
	public void setRegDay(String regDay) {
		RegDay = regDay;
	}
	public String getRegUser() {
		return RegUser;
	}
	public void setRegUser(String regUser) {
		RegUser = regUser;
	}
	public Date getChgDtm() {
		return ChgDtm;
	}
	public void setChgDtm(Date chgDtm) {
		ChgDtm = chgDtm;
	}
	public String getChgUser() {
		return ChgUser;
	}
	public void setChgUser(String chgUser) {
		ChgUser = chgUser;
	}
	public String getMembName() {
		return membName;
	}
	public void setMembName(String membName) {
		this.membName = membName;
	}
	public String getBulkYn() {
		return bulkYn;
	}
	public void setBulkYn(String bulkYn) {
		this.bulkYn = bulkYn;
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
	
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	
}
