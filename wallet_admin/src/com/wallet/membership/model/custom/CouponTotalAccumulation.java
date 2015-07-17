package com.wallet.membership.model.custom;

public class CouponTotalAccumulation {

	private String term;
	private String issueCpnCnt;
	private String accumCnt;
	private String amount;
	private String cpnDiscAmount;
	
	
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getIssueCpnCnt() {
		return issueCpnCnt;
	}
	public void setIssueCpnCnt(String issueCpnCnt) {
		this.issueCpnCnt = issueCpnCnt;
	}
	public String getAccumCnt() {
		return accumCnt;
	}
	public void setAccumCnt(String accumCnt) {
		this.accumCnt = accumCnt;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCpnDiscAmount() {
		return cpnDiscAmount;
	}
	public void setCpnDiscAmount(String cpnDiscAmount) {
		this.cpnDiscAmount = cpnDiscAmount;
	}
}
