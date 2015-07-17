package com.wallet.admin.model;

import com.rocomo.common.model.Common;


public class MwAdEvent extends Common {

	private Integer idx;		//idx
	private Integer rowNum;	//rowNum
	private String stat;		//�Խû���(���:R, ����:F)
  private String os;			//os ����(android,apple)	
  private String title;		//�� ����
  private String evtMode;	//�۱���(��������:N, �̺�Ʈ:E)
  private String sday;		//����Ⱓ - ������
  private String eday;		//����Ⱓ - ������
  private String msgMode;	//�� type(text�� ���:T, ������ ���ε�:W)
  private String imgHost;	//�̹��� ȣ��Ʈ
  private String url;			//url
  private String msg;			//�۳���
  private String regTm;		//�����
  private String chgTm;		//������
  private String evtImg;	//�۱����� �̺�Ʈ�ϰ�� - �̺�Ʈ�̹���
	
	
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