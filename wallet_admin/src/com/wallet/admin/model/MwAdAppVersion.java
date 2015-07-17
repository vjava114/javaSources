package com.wallet.admin.model;

import com.rocomo.common.model.Common;

public class MwAdAppVersion extends Common {
    
		private Integer idx;		//idx
		private String market;	//os 구분(android,apple,olleh)
    private String ver;			//제공버전
    private String uploadDt;
    private String mode;		//공지모드
    private String msg;			//메세지
    private String regTm;		//등록시간
    private String regTmDtl;
		private String chgTm;		//변경시간
    private String verStr;
    

    public Integer getIdx() {
			return idx;
		}
		public void setIdx(Integer idx) {
			this.idx = idx;
		}
		public String getMarket() {
			return market;
		}
		public void setMarket(String market) {
			this.market = market;
		}
		public String getVer() {
			return ver;
		}
		public void setVer(String ver) {
			this.ver = ver;
		}
		public String getUploadDt() {
			return uploadDt;
		}
		public void setUploadDt(String uploadDt) {
			this.uploadDt = uploadDt;
		}
		public String getMode() {
			return mode;
		}
		public void setMode(String mode) {
			this.mode = mode;
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
		public String getRegTmDtl() {
			return regTmDtl;
		}
		public void setRegTmDtl(String regTmDtl) {
			this.regTmDtl = regTmDtl;
		}
		public String getChgTm() {
			return chgTm;
		}
		public void setChgTm(String chgTm) {
			this.chgTm = chgTm;
		}
		public String getVerStr() {
			return verStr;
		}
		public void setVerStr(String verStr) {
			this.verStr = verStr;
		}
		
}