package com.wallet.admin.model;

import com.rocomo.common.model.Common;

public class MwAdGift extends Common {
	
	private String giftId;			//상품권 id
  private String giftSid;			//상품권 sid
  private String name;				//상품권명
  private String info;
  private String sday;
  private String eday;
  private String chgDay;			//변경일
  private String adminId;			//등록자id
  private Integer mainIdx;		//순서
  private String displayYn;		//display_yn(디폴트:Y)
  private String memo;				//소개문구
	private String stat;				//게시상태(사용:R, 중지:F)
  private String os;					//os 구분(android,apple)
  private String imgHost;			//이미지 호스트
	private String lImgI3;			//목록이미지 - 아이폰3
  private String lImgI4;			//목록이미지 - 아이폰4
  private String lImgR4;			//목록이미지 - 안드로이드
  private String lImgR5;
  private String lImgR6;
  private String lImgR7;
  private String dImgI3;			//카드이미지 - 아이폰3
  private String dImgI4;			//카드이미지 - 아이폰4
  private String dImgR4;			//카드이미지 - 안드로이드
  private String dImgD5;
  private String dImgR6;
  private String dImgR7;
  private String operater;		//운영사
  
	public String getGiftId() {
		return giftId;
	}
	public void setGiftId(String giftId) {
		this.giftId = giftId;
	}
	public String getGiftSid() {
		return giftSid;
	}
	public void setGiftSid(String giftSid) {
		this.giftSid = giftSid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getSday() {
		return sday;
	}
	public void setSday(String sday) {
		this.sday = sday;
	}
	public String getEday() {
		return eday;
	}
	public void setEday(String eday) {
		this.eday = eday;
	}
	public String getChgDay() {
		return chgDay;
	}
	public void setChgDay(String chgDay) {
		this.chgDay = chgDay;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public Integer getMainIdx() {
		return mainIdx;
	}
	public void setMainIdx(Integer mainIdx) {
		this.mainIdx = mainIdx;
	}
	public String getDisplayYn() {
		return displayYn;
	}
	public void setDisplayYn(String displayYn) {
		this.displayYn = displayYn;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
 public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getImgHost() {
		return imgHost;
	}
	public void setImgHost(String imgHost) {
		this.imgHost = imgHost;
	}
	public String getlImgI3() {
		return lImgI3;
	}
	public void setlImgI3(String lImgI3) {
		this.lImgI3 = lImgI3;
	}
	public String getlImgI4() {
		return lImgI4;
	}
	public void setlImgI4(String lImgI4) {
		this.lImgI4 = lImgI4;
	}
	public String getlImgR4() {
		return lImgR4;
	}
	public void setlImgR4(String lImgR4) {
		this.lImgR4 = lImgR4;
	}
	public String getlImgR5() {
		return lImgR5;
	}
	public void setlImgR5(String lImgR5) {
		this.lImgR5 = lImgR5;
	}
	public String getlImgR6() {
		return lImgR6;
	}
	public void setlImgR6(String lImgR6) {
		this.lImgR6 = lImgR6;
	}
	public String getlImgR7() {
		return lImgR7;
	}
	public void setlImgR7(String lImgR7) {
		this.lImgR7 = lImgR7;
	}
	public String getdImgI3() {
		return dImgI3;
	}
	public void setdImgI3(String dImgI3) {
		this.dImgI3 = dImgI3;
	}
	public String getdImgI4() {
		return dImgI4;
	}
	public void setdImgI4(String dImgI4) {
		this.dImgI4 = dImgI4;
	}
	public String getdImgR4() {
		return dImgR4;
	}
	public void setdImgR4(String dImgR4) {
		this.dImgR4 = dImgR4;
	}
	public String getdImgD5() {
		return dImgD5;
	}
	public void setdImgD5(String dImgD5) {
		this.dImgD5 = dImgD5;
	}
	public String getdImgR6() {
		return dImgR6;
	}
	public void setdImgR6(String dImgR6) {
		this.dImgR6 = dImgR6;
	}
	public String getdImgR7() {
		return dImgR7;
	}
	public void setdImgR7(String dImgR7) {
		this.dImgR7 = dImgR7;
	}
	public String getOperater() {
		return operater;
	}
	public void setOperater(String operater) {
		this.operater = operater;
	}
	
}