package com.wallet.admin.model;

import com.rocomo.common.model.Common;

public class MwAdUser extends Common {
   
    private String mgrId;			//계정id
    private String passwd;		//비밀번호
    private String name;			//이름
    private String part;			//소속
    private String phone;			//핸드폰번호
    private String email;			//emadil
    private String rgDay;			//등록일자
    private String chUsid;		//변경id
    private String chDay;			//변경일자
    private String stat;			//상태(사용:R, 중지:F)
		private Integer retryCnt;	//비밀번호 호류 횟수
    private String lev;				//권한 레벨
    private String expDate;		//비밀번호 만료일자
    private String pwdEndDay;	//현재 남은 비밀번호 만료일 (비밀번호 만료일자 - 오늘일자 ) 
    private String loginDate;	//마지막 로그인 일시
    private String ipaddress;	//세션키
    
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
		public String getLev() {
			return lev;
		}
		public void setLev(String lev) {
			this.lev = lev;
		}
		public String getExpDate() {
			return expDate;
		}
		public void setExpDate(String expDate) {
			this.expDate = expDate;
		}
		public String getPwdEndDay() {
			return pwdEndDay;
		}
		public void setPwdEndDay(String pwdEndDay) {
			this.pwdEndDay = pwdEndDay;
		}
		public String getLoginDate() {
			return loginDate;
		}
		public void setLoginDate(String loginDate) {
			this.loginDate = loginDate;
		}
		public String getIpaddress() {
			return ipaddress;
		}
		public void setIpaddress(String ipaddress) {
			this.ipaddress = ipaddress;
		}

}