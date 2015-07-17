package com.wallet.harex.model;

import com.rocomo.common.model.Common;

@SuppressWarnings("serial")
public class OfferingMsDc extends Common {
	private Integer seq;   					// ¹øÈ£
	private String offerId;
	private String membId;
	private String membName;
	private String membCardNo;
	private String selfMemYn;
	private String compId;
	private String brdId;
	private String membClass;
	private Integer dcRate;
	private String dcUnit;
	private String roundType;
	private Integer minPayPrice;
	private Integer maxDcPrice;
	private String calcBaseCd;
	private Integer calcBasePrice;
	private Integer expDcPrice;
	private String membNotiUrl;
	private String imgHost;
	private String imgUrl;
	private String onoff;
	private String regDttm;
	private String lastDttm;
	
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getOfferId() {
		return offerId;
	}
	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}
	public String getMembId() {
		return membId;
	}
	public void setMembId(String membId) {
		this.membId = membId;
	}
	public String getMembName() {
		return membName;
	}
	public void setMembName(String membName) {
		this.membName = membName;
	}
	public String getMembCardNo() {
		return membCardNo;
	}
	public void setMembCardNo(String membCardNo) {
		this.membCardNo = membCardNo;
	}
	public String getSelfMemYn() {
		return selfMemYn;
	}
	public void setSelfMemYn(String selfMemYn) {
		this.selfMemYn = selfMemYn;
	}
	public String getCompId() {
		return compId;
	}
	public void setCompId(String compId) {
		this.compId = compId;
	}
	public String getBrdId() {
		return brdId;
	}
	public void setBrdId(String brdId) {
		this.brdId = brdId;
	}
	public String getMembClass() {
		return membClass;
	}
	public void setMembClass(String membClass) {
		this.membClass = membClass;
	}
	public Integer getDcRate() {
		return dcRate;
	}
	public void setDcRate(Integer dcRate) {
		this.dcRate = dcRate;
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
	public String getCalcBaseCd() {
		return calcBaseCd;
	}
	public void setCalcBaseCd(String calcBaseCd) {
		this.calcBaseCd = calcBaseCd;
	}
	public Integer getCalcBasePrice() {
		return calcBasePrice;
	}
	public void setCalcBasePrice(Integer calcBasePrice) {
		this.calcBasePrice = calcBasePrice;
	}
	public Integer getExpDcPrice() {
		return expDcPrice;
	}
	public void setExpDcPrice(Integer expDcPrice) {
		this.expDcPrice = expDcPrice;
	}
	public String getMembNotiUrl() {
		return membNotiUrl;
	}
	public void setMembNotiUrl(String membNotiUrl) {
		this.membNotiUrl = membNotiUrl;
	}
	public String getImgHost() {
		return imgHost;
	}
	public void setImgHost(String imgHost) {
		this.imgHost = imgHost;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getOnoff() {
		return onoff;
	}
	public void setOnoff(String onoff) {
		this.onoff = onoff;
	}
	public String getRegDttm() {
		return regDttm;
	}
	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}
	public String getLastDttm() {
		return lastDttm;
	}
	public void setLastDttm(String lastDttm) {
		this.lastDttm = lastDttm;
	}
}
