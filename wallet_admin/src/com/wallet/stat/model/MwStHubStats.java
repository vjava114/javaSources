package com.wallet.stat.model;

import com.rocomo.common.model.Common;

public class MwStHubStats extends Common {

	private String grpCode;	//�׷��ڵ�
	private String code;		//�ڵ�
  private String cday;		//��¥
  private String comCd;		//�����ڵ�
    
	public String getGrpCode() {
		return grpCode;
	}
	public void setGrpCode(String grpCode) {
		this.grpCode = grpCode;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCday() {
		return cday;
	}
	public void setCday(String cday) {
		this.cday = cday;
	}
	public String getComCd() {
		return comCd;
	}
	public void setComCd(String comCd) {
		this.comCd = comCd;
	}
   
}