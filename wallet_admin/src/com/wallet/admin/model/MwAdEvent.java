package com.wallet.admin.model;

import com.rocomo.common.model.Common;


public class MwAdEvent extends Common {

	private Integer idx;		//idx
	private Integer rowNum;	//rowNum
	private String stat;		//게시상태(사용:R, 중지:F)
  private String os;			//os 구분(android,apple)	
  private String title;		//글 제목
  private String evtMode;	//글구분(공지사항:N, 이벤트:E)
  private String sday;		//게재기간 - 시작일
  private String eday;		//게재기간 - 종료일
  private String msgMode;	//글 type(text만 등록:T, 페이지 업로드:W)
  private String imgHost;	//이미지 호스트
  private String url;			//url
  private String msg;			//글내용
  private String regTm;		//등록일
  private String chgTm;		//변경일
  private String evtImg;	//글구분이 이벤트일경우 - 이벤트이미지
	
	
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEvtMode() {
		return evtMode;
	}
	public void setEvtMode(String evtMode) {
		this.evtMode = evtMode;
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
	public String getMsgMode() {
		return msgMode;
	}
	public void setMsgMode(String msgMode) {
		this.msgMode = msgMode;
	}
	public String getImgHost() {
		return imgHost;
	}
	public void setImgHost(String imgHost) {
		this.imgHost = imgHost;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
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
	public String getEvtImg() {
		return evtImg;
	}
	public void setEvtImg(String evtImg) {
		this.evtImg = evtImg;
	}
  
}