package com.wallet.harex.model;

import com.rocomo.common.model.Common;

@SuppressWarnings("serial")
public class Compare extends Common{
    
	private String seq;                               
	private String trDate;          //거래일자        
	private String compId;          //제휴사ID        
	private String compName;        //제휴사명        
	private String brandId;         //브랜드ID        
	private String brandName;       //브랜드명        
	private String shopId;          //가맹점ID        
	private String shopName;        //가맹점명        
	private String phoneNo;         //전화번호        
	private String payType;         //복합결제항목    
	private String pstatus;         //상태            
	private String acposTid;	      //복합결제거래번호
	private String apprNo;          //쿠폰승인번호    
	private String apprDate;		    //승인날짜        
	private String apprTime;		    //승인시간        
	private Integer realDcPrice;    //할인금액       
	private Integer realUsePoint;  //포인트

	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getTrDate() {
		return trDate;
	}
	public void setTrDate(String trDate) {
		this.trDate = trDate;
	}
	public String getCompId() {
		return compId;
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
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo == null ? null : phoneNo.trim().replace("-", "");
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getPstatus() {
		return pstatus;
	}
	public void setPstatus(String pstatus) {
		this.pstatus = pstatus;
	}
	public String getAcposTid() {
		return acposTid;
	}
	public void setAcposTid(String acposTid) {
		this.acposTid = acposTid;
	}
	public String getApprNo() {
		return apprNo;
	}
	public void setApprNo(String apprNo) {
		this.apprNo = apprNo;
	}
	public String getApprDate() {
		return apprDate;
	}
	public void setApprDate(String apprDate) {
		this.apprDate = apprDate;
	}
	public String getApprTime() {
		return apprTime;
	}
	public void setApprTime(String apprTime) {
		this.apprTime = apprTime;
	}
	public Integer getRealDcPrice() {
		return realDcPrice;
	}
	public void setRealDcPrice(Integer realDcPrice) {
		this.realDcPrice = realDcPrice;
	}
	public Integer getRealUsePoint() {
		return realUsePoint;
	}
	public void setRealUsePoint(Integer realUsePoint) {
		this.realUsePoint = realUsePoint;
	} 
}
