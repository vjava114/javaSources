package com.wallet.stat.model;

import com.rocomo.common.model.Common;

public class MwStHubStats extends Common {

	private String grpCode;	//그룹코드
	private String code;		//코드
  private String cday;		//날짜
  private String comCd;		//공통코드
    
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