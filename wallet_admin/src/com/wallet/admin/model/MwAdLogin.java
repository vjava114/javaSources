package com.wallet.admin.model;

import com.rocomo.common.model.Common;

public class MwAdLogin extends Common {

    private String mgrId;			//id
    private String passwd;		//��й�ȣ
    private String name;			//�̸�
    private String part;			//�Ҽ�
    private String phone;			//����ó
    private String email;			//�̸���
    private String rgDay;			//�����
    private String chUsid;		//������id
    private String chDay;			//������
    private String stat;			//����(���,������)
    private Integer retryCnt;	//��й�ȣ ���� Ƚ��
		private String sms;				//sms
		private String lev;				//���� ����
    private String expDate;		//��й�ȣ ������	
    private String pwdEndDay;	//���й�ȣ ������ - ���ó�¥
    private String loginDate;	//�������α�������
    private String ipaddress;	//����Ű(�ߺ��α��ι���)
    

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