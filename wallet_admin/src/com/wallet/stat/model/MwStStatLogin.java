package com.wallet.stat.model;

import com.rocomo.common.model.Common;

public class MwStStatLogin extends Common {

    private String mgrId;			//id
    private String passwd;		//비밀번호
    private String name;			//이름
    private String part;			//소속
    private String phone;			//핸드폰번호
    private String email;			//이메일
    private String rgDay;			//등록일
    private String chUsid;		//변경자id
    private String chDay;			//변경일
    private String stat;			//상태(사용,정지중)
    private Integer retryCnt;	//비밀번호 오류 횟수
		private String sms;				//sms
    
    
		public String getMgrId() {
			return mgrId;
		}
		public void setMgrId(String mgrId) {
			this.mgrId = mgrId;
		}
		public String getPasswd() {
			return passwd;
		}
		public void setPasswd(String passwd) {
			this.passwd = passwd;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPart() {
			return part;
		}
		public void setPart(String part) {
			this.part = part;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getRgDay() {
			return rgDay;
		}
		public void setRgDay(String rgDay) {
			this.rgDay = rgDay;
		}
		public String getChUsid() {
			return chUsid;
		}
		public void setChUsid(String chUsid) {
			this.chUsid = chUsid;
		}
		public String getChDay() {
			return chDay;
		}
		public void setChDay(String chDay) {
			this.chDay = chDay;
		}
		public String getStat() {
			return stat;
		}
		public void setStat(String stat) {
			this.stat = stat;
		}
		public Integer getRetryCnt() {
			return retryCnt;
		}
		public void setRetryCnt(Integer retryCnt) {
			this.retryCnt = retryCnt;
		}
		 public String getSms() {
				return sms;
			}
			public void setSms(String sms) {
				this.sms = sms;
			}
   
}