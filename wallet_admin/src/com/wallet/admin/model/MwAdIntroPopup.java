package com.wallet.admin.model;

import com.rocomo.common.model.Common;
import java.util.Date;


public class MwAdIntroPopup extends Common {

	private Integer idx;			//idx
	private Integer rowNum;		//rowNum
	private String stat;			//�Խû���(���:R, ����:F)
	private String os;				//os ����(android,apple)
	private String sday;			//����Ⱓ - ������
	private String eday;			//����Ⱓ - ������
	private String url;				//url
	private String regTm;			//��Ͻð�
	private String chgTm;			//����ð�
	private String eventType;	//�ٽú��� Ÿ��(�� �̻� ���� �ʱ�:once, ���ø� �׸�����:today, ��� ����:none)
	
	
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