package com.wallet.admin.model;

import com.rocomo.common.model.Common;

public class MwAdPayment extends Common {
   
    private String payCode;			//�����ڵ�
    private String membId;			//��� id
    private String os;					//os
    private String linkMode;		//�������(���� �� ������:W, App ȣ����:A)
    private String market;			//���� (olleh, apple,android)
    private String payType;			//���� ���� ����(�޴��� ����:P, ��ī����:M,��Ÿ����:O)
    private String disSday;			//����Ⱓ - ������
    private String disEday;			//����Ⱓ - ������
    private String stat;				//�Խû���(���:R, ����:F)
    private String name;				//�������񽺸�
    private String info;				//����url
    private String memo;				//memo
    private String regUser;			//�����
    private String chgDay;			//������
		private String regDay;			//�����
    private Integer mainIdx;		//����
    private String displayYn;		//display_yn(����Ʈ:Y)
    private String appUrl;
    private String imgHost;			//�̹��� ȣ��Ʈ
    private String lImgI3;			//����̹��� - ������3
    private String lImgI4;			//����̹��� - ������4
    private String lImgI5;		
    private String lImgR4;			//����̹��� - �ȵ���̵�
    private String lImgR5;		
    private String lImgR6;
    private String lImgR7;
    private String dImgI3;			//ī���̹��� - ������3
    private String dImgI4;			//ī���̹��� - ������4
    private String dImgI5;
    private String dImgR4;			//ī���̹��� - �ȵ���̵�
    private String dImgR5;
    private String dImgR6;
    private String dImgR7;
    private String appleId;			//������ ���� - Apple id
    private String appleUrl;		//������ ���� - Custom url
    private String googlePkg;		//�ȵ���̵� ����(play�����)- package��
    private String googleClass;	//�ȵ���̵� ����(play�����)- ���� class��
    private String googleDown;	//�ȵ���̵� ����(play�����)- �ٿ�ε� url
    private String ollehId;			//�ȵ���̵� ����(�÷�����) - app id
    private String ollehPkg;		//�ȵ���̵� ����(�÷�����) - package��
    private String ollehClass;	//�ȵ���̵� ����(�÷�����) - ���� class��
    private String ollehDown;		//�ȵ���̵� ����(�÷�����) - �ٿ�ε� url
    private String appleDown;		//������ ���� - �ٿ�ε� url
    private String companyNm;		//���޻��
    
    private Integer idx;
		private String title;
    private String web;
    private String chk;
    private String ver;
   
		
		
		public String getPayCode() {
			return payCode;
		}
		public void setPayCode(String payCode) {
			this.payCode = payCode;
		}
		public String getMembId() {
			return membId;
		}
		public void setMembId(String membId) {
			this.membId = membId;
		}
		public String getOs() {
			return os;
		}
		public void setOs(String os) {
			this.os = os;
		}
		public String getLinkMode() {
			return linkMode;
		}
		public void setLinkMode(String linkMode) {
			this.linkMode = linkMode;
		}
		public String getMarket() {
			return market;
		}
		public void setMarket(String market) {
			this.market = market;
		}
		public String getPayType() {
			return payType;
		}
		public void setPayType(String payType) {
			this.payType = payType;
		}
		public String getDisSday() {
			return disSday;
		}
		public void setDisSday(String disSday) {
			this.disSday = disSday;
		}
		public String getDisEday() {
			return disEday;
		}
		public void setDisEday(String disEday) {
			this.disEday = disEday;
		}
		public String getStat() {
			return stat;
		}
		public void setStat(String stat) {
			this.stat = stat;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getInfo() {
			return info;
		}
		public void setInfo(String info) {
			this.info = info;
		}
		public String getMemo() {
			return memo;
		}
		public void setMemo(String memo) {
			this.memo = memo;
		}
		public String getRegUser() {
			return regUser;
		}
		public void setRegUser(String regUser) {
			this.regUser = regUser;
		}
		public String getChgDay() {
			return chgDay;
		}
		public void setChgDay(String chgDay) {
			this.chgDay = chgDay;
		}
		public String getRegDay() {
			return regDay;
		}
		public void setRegDay(String regDay) {
			this.regDay = regDay;
		}
		public Integer getMainIdx() {
			return mainIdx;
		}
		public void setMainIdx(Integer mainIdx) {
			this.mainIdx = mainIdx;
		}
		public String getDisplayYn() {
			return displayYn;
		}
		public void setDisplayYn(String displayYn) {
			this.displayYn = displayYn;
		}
		public String getAppUrl() {
			return appUrl;
		}
		public void setAppUrl(String appUrl) {
			this.appUrl = appUrl;
		}
		public String getImgHost() {
			return imgHost;
		}
		public void setImgHost(String imgHost) {
			this.imgHost = imgHost;
		}
		public String getlImgI3() {
			return lImgI3;
		}
		public void setlImgI3(String lImgI3) {
			this.lImgI3 = lImgI3;
		}
		public String getlImgI4() {
			return lImgI4;
		}
		public void setlImgI4(String lImgI4) {
			this.lImgI4 = lImgI4;
		}
		public String getlImgI5() {
			return lImgI5;
		}
		public void setlImgI5(String lImgI5) {
			this.lImgI5 = lImgI5;
		}
		public String getlImgR4() {
			return lImgR4;
		}
		public void setlImgR4(String lImgR4) {
			this.lImgR4 = lImgR4;
		}
		public String getlImgR5() {
			return lImgR5;
		}
		public void setlImgR5(String lImgR5) {
			this.lImgR5 = lImgR5;
		}
		public String getlImgR6() {
			return lImgR6;
		}
		public void setlImgR6(String lImgR6) {
			this.lImgR6 = lImgR6;
		}
		public String getlImgR7() {
			return lImgR7;
		}
		public void setlImgR7(String lImgR7) {
			this.lImgR7 = lImgR7;
		}
		public String getdImgI3() {
			return dImgI3;
		}
		public void setdImgI3(String dImgI3) {
			this.dImgI3 = dImgI3;
		}
		public String getdImgI4() {
			return dImgI4;
		}
		public void setdImgI4(String dImgI4) {
			this.dImgI4 = dImgI4;
		}
		public String getdImgI5() {
			return dImgI5;
		}
		public void setdImgI5(String dImgI5) {
			this.dImgI5 = dImgI5;
		}
		public String getdImgR4() {
			return dImgR4;
		}
		public void setdImgR4(String dImgR4) {
			this.dImgR4 = dImgR4;
		}
		public String getdImgR5() {
			return dImgR5;
		}
		public void setdImgR5(String dImgR5) {
			this.dImgR5 = dImgR5;
		}
		public String getdImgR6() {
			return dImgR6;
		}
		public void setdImgR6(String dImgR6) {
			this.dImgR6 = dImgR6;
		}
		public String getdImgR7() {
			return dImgR7;
		}
		public void setdImgR7(String dImgR7) {
			this.dImgR7 = dImgR7;
		}
		public String getAppleId() {
			return appleId;
		}
		public void setAppleId(String appleId) {
			this.appleId = appleId;
		}
		public String getAppleUrl() {
			return appleUrl;
		}
		public void setAppleUrl(String appleUrl) {
			this.appleUrl = appleUrl;
		}
		public String getGooglePkg() {
			return googlePkg;
		}
		public void setGooglePkg(String googlePkg) {
			this.googlePkg = googlePkg;
		}
		public String getGoogleClass() {
			return googleClass;
		}
		public void setGoogleClass(String googleClass) {
			this.googleClass = googleClass;
		}
		public String getGoogleDown() {
			return googleDown;
		}
		public void setGoogleDown(String googleDown) {
			this.googleDown = googleDown;
		}
		public String getOllehId() {
			return ollehId;
		}
		public void setOllehId(String ollehId) {
			this.ollehId = ollehId;
		}
		public String getOllehPkg() {
			return ollehPkg;
		}
		public void setOllehPkg(String ollehPkg) {
			this.ollehPkg = ollehPkg;
		}
		public String getOllehClass() {
			return ollehClass;
		}
		public void setOllehClass(String ollehClass) {
			this.ollehClass = ollehClass;
		}
		public String getOllehDown() {
			return ollehDown;
		}
		public void setOllehDown(String ollehDown) {
			this.ollehDown = ollehDown;
		}
		public String getAppleDown() {
			return appleDown;
		}
		public void setAppleDown(String appleDown) {
			this.appleDown = appleDown;
		}
		public String getCompanyNm() {
			return companyNm;
		}
		public void setCompanyNm(String companyNm) {
			this.companyNm = companyNm;
		}
    
		public Integer getIdx() {
			return idx;
		}
		public void setIdx(Integer idx) {
			this.idx = idx;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getWeb() {
			return web;
		}
		public void setWeb(String web) {
			this.web = web;
		}
		public String getChk() {
			return chk;
		}
		public void setChk(String chk) {
			this.chk = chk;
		}
		public String getVer() {
			return ver;
		}
		public void setVer(String ver) {
			this.ver = ver;
		}

}