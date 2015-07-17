package com.wallet.admin.model;

import com.rocomo.common.model.Common;

public class MwAdBanner extends Common {
    
    private Integer idx;		//idx
    private Integer rowNum;	//rowNum
		private String stat;		//게시상태(사용:R, 중지:F)
    private String os;			//os 구분(android,apple)
    private String sday;		//게재기간 - 시작일
    private String eday;		//게재기간 - 종료일
    private String imgHost;	//이미지 호스트
    private String imgI3;		//이미지 - 아이폰3
    private String imgI4;		//이미지 - 아이폰4
    private String imgR4;		//이미지 - 안드로이드
    private String imgR5;
    private String imgR6;
    private String imgR7;
    private String name;		//배너명
    private String gotoMode;//배너 링크 구분(Web url:WEB, 멤버십:MEM, 쿠폰:CPN, 결제:PAY, 멤버십 상세:MEMDT, 쿠폰 상세:CPNDT, 이벤트/공지:EVENT)
    private String gotoId;	//배너 링크 구분 ID (멤버십상세,쿠폰상세,이벤트/공지 선택시 해당 ID)
    private String gotoInfo;//배너 링크 구분 ID의 NAME (멤버십상세,쿠폰상세,이벤트/공지 선택시 해당 ID의 NAME)
    private String regTm;		//등록시간
    private String chgTm;		//변경시간
    
    private String selectId;
    private String selectTitle;
    private String selectStat;
    private String selectStatStr;


		public Integer getIdx() {
			return idx;
		}
		public void setIdx(Integer idx) {
			this.idx = idx;
		}
		public Integer getRowNum() {
			return rowNum;
		}
		public void setRowNum(Integer rowNum) {
			this.rowNum = rowNum;
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
		public String getImgHost() {
			return imgHost;
		}
		public void setImgHost(String imgHost) {
			this.imgHost = imgHost;
		}
		public String getImgI3() {
			return imgI3;
		}
		public void setImgI3(String imgI3) {
			this.imgI3 = imgI3;
		}
		public String getImgI4() {
			return imgI4;
		}
		public void setImgI4(String imgI4) {
			this.imgI4 = imgI4;
		}
		public String getImgR4() {
			return imgR4;
		}
		public void setImgR4(String imgR4) {
			this.imgR4 = imgR4;
		}
		public String getImgR5() {
			return imgR5;
		}
		public void setImgR5(String imgR5) {
			this.imgR5 = imgR5;
		}
		public String getImgR6() {
			return imgR6;
		}
		public void setImgR6(String imgR6) {
			this.imgR6 = imgR6;
		}
		public String getImgR7() {
			return imgR7;
		}
		public void setImgR7(String imgR7) {
			this.imgR7 = imgR7;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getGotoMode() {
			return gotoMode;
		}
		public void setGotoMode(String gotoMode) {
			this.gotoMode = gotoMode;
		}
		public String getGotoId() {
			return gotoId;
		}
		public void setGotoId(String gotoId) {
			this.gotoId = gotoId;
		}
		public String getGotoInfo() {
			return gotoInfo;
		}
		public void setGotoInfo(String gotoInfo) {
			this.gotoInfo = gotoInfo;
		}
		public String getRegTm() {
			return regTm;
		}
		public void setRegTm(String regTm) {
			this.regTm = regTm;
		}
		public String getChgTm() {
			return chgTm;
		}
		public void setChgTm(String chgTm) {
			this.chgTm = chgTm;
		}
		public String getSelectId() {
			return selectId;
		}
		public void setSelectId(String selectId) {
			this.selectId = selectId;
		}
		public String getSelectTitle() {
			return selectTitle;
		}
		public void setSelectTitle(String selectTitle) {
			this.selectTitle = selectTitle;
		}
		public String getSelectStat() {
			return selectStat;
		}
		public void setSelectStat(String selectStat) {
			this.selectStat = selectStat;
		}
		public String getSelectStatStr() {
			return selectStatStr;
		}
		public void setSelectStatStr(String selectStatStr) {
			this.selectStatStr = selectStatStr;
		}
		

}