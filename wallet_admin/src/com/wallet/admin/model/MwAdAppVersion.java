package com.wallet.admin.model;

import com.rocomo.common.model.Common;

public class MwAdAppVersion extends Common {
    
		private Integer idx;		//idx
		private String market;	//os ����(android,apple,olleh)
    private String ver;			//��������
    private String uploadDt;
    private String mode;		//�������
    private String msg;			//�޼���
    private String regTm;		//��Ͻð�
    private String regTmDtl;
		private String chgTm;		//����ð�
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