package com.wallet.admin.model;

import com.rocomo.common.model.Common;
import java.util.Date;


public class MwAdIntroPopup extends Common {

	private Integer idx;			//idx
	private Integer rowNum;		//rowNum
	private String stat;			//게시상태(사용:R, 중지:F)
	private String os;				//os 구분(android,apple)
	private String sday;			//게재기간 - 시작일
	private String eday;			//게재기간 - 종료일
	private String url;				//url
	private String regTm;			//등록시간
	private String chgTm;			//변경시간
	private String eventType;	//다시보기 타입(더 이상 보지 않기:once, 오늘만 그만보기:today, 기능 없음:none)
	
	
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	
}