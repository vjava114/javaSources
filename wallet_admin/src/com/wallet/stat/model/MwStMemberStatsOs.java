package com.wallet.stat.model;

import com.rocomo.common.model.Common;

public class MwStMemberStatsOs extends Common {
	
		private String day;			//��¥
		private String aK;			//�ȵ���̵� KT
		private String aS;			//�ȵ���̵� SKT
		private String aL;			//�ȵ���̵� LG
		private String iK;			//������ KT
		private String iS;			//������ SKT
		private String iL;			//������ LG
		private String watIn;
		private String payIn;
		private String sAK;			//���� �ȵ���̵� KT
		private String sAS;			//���� �ȵ���̵� SKT
		private String sAL;			//���� �ȵ���̵� LG
		private String sIK;			//���� ������ KT
		private String sIS;			//���� ������ SKT
		private String sIL;			//���� ������ LG
		private String sWatIn;
		private String sPayIn;
		private String aTotal; 		//�ȵ���̵� �Ұ�
		private String iTotal;		//������ �Ұ�
		private String newKTotal; //�ű԰����� �հ� : KT
		private String newSTotal;	//�ű԰����� �հ� : SKT
		private String newLTotal;	//�ű԰����� �հ� : LG
		private String newTotal;	//�ű԰����� �հ� : �Ұ� (KT + SKT + LG)
		private String sATotal;		//�ȵ���̵� ���������� 
		private String sITotal;		//������ ���� ������
		private String sTotal;		//����������
		private String bSTotal;		//������ ���ϱ� ���� ���� ����������
		private String sunjng;		//���� (sTotal - bSTotal)
		
		private String mtowA;	//��ī���̿��� ��ī���� ������ : ������� ��
		private String mtowR;	//��ī���̿��� ��ī���� ������ : ��ī���� ���ԿϷ�
		private String wtomA;	//��ī�������� ��ī���� ������ : ������� ��
		private String wtomR;	//��ī���̿��� ��ī���� ������ : �� ��ī ����
		

		public String getDay() {
			return day;
		}
		public void setDay(String day) {
			this.day = day;
		}
		public String getaK() {
			return aK;
		}
		public void setaK(String aK) {
			this.aK = aK;
		}
		public String getaS() {
			return aS;
		}
		public void setaS(String aS) {
			this.aS = aS;
		}
		public String getaL() {
			return aL;
		}
		public void setaL(String aL) {
			this.aL = aL;
		}
		public String getiK() {
			return iK;
		}
		public void setiK(String iK) {
			this.iK = iK;
		}
		public String getiS() {
			return iS;
		}
		public void setiS(String iS) {
			this.iS = iS;
		}
		public String getiL() {
			return iL;
		}
		public void setiL(String iL) {
			this.iL = iL;
		}
		public String getWatIn() {
			return watIn;
		}
		public void setWatIn(String watIn) {
			this.watIn = watIn;
		}
		public String getPayIn() {
			return payIn;
		}
		public void setPayIn(String payIn) {
			this.payIn = payIn;
		}
		public String getsAK() {
			return sAK;
		}
		public void setsAK(String sAK) {
			this.sAK = sAK;
		}
		public String getsAS() {
			return sAS;
		}
		public void setsAS(String sAS) {
			this.sAS = sAS;
		}
		public String getsAL() {
			return sAL;
		}
		public void setsAL(String sAL) {
			this.sAL = sAL;
		}
		public String getsIK() {
			return sIK;
		}
		public void setsIK(String sIK) {
			this.sIK = sIK;
		}
		public String getsIS() {
			return sIS;
		}
		public void setsIS(String sIS) {
			this.sIS = sIS;
		}
		public String getsIL() {
			return sIL;
		}
		public void setsIL(String sIL) {
			this.sIL = sIL;
		}
		public String getsWatIn() {
			return sWatIn;
		}
		public void setsWatIn(String sWatIn) {
			this.sWatIn = sWatIn;
		}
		public String getsPayIn() {
			return sPayIn;
		}
		public void setsPayIn(String sPayIn) {
			this.sPayIn = sPayIn;
		}
		public String getaTotal() {
			return aTotal;
		}
		public void setaTotal(String aTotal) {
			this.aTotal = aTotal;
		}
		public String getiTotal() {
			return iTotal;
		}
		public void setiTotal(String iTotal) {
			this.iTotal = iTotal;
		}
		public String getNewKTotal() {
			return newKTotal;
		}
		public void setNewKTotal(String newKTotal) {
			this.newKTotal = newKTotal;
		}
		public String getNewSTotal() {
			return newSTotal;
		}
		public void setNewSTotal(String newSTotal) {
			this.newSTotal = newSTotal;
		}
		public String getNewLTotal() {
			return newLTotal;
		}
		public void setNewLTotal(String newLTotal) {
			this.newLTotal = newLTotal;
		}
		public String getNewTotal() {
			return newTotal;
		}
		public void setNewTotal(String newTotal) {
			this.newTotal = newTotal;
		}
		public String getsATotal() {
			return sATotal;
		}
		public void setsATotal(String sATotal) {
			this.sATotal = sATotal;
		}
		public String getsITotal() {
			return sITotal;
		}
		public void setsITotal(String sITotal) {
			this.sITotal = sITotal;
		}
		public String getsTotal() {
			return sTotal;
		}
		public void setsTotal(String sTotal) {
			this.sTotal = sTotal;
		}
		public String getSunjng() {
			return sunjng;
		}
		public void setSunjng(String sunjng) {
			this.sunjng = sunjng;
		}
		public String getbSTotal() {
			return bSTotal;
		}
		public void setbSTotal(String bSTotal) {
			this.bSTotal = bSTotal;
		}
		public String getMtowA() {
			return mtowA;
		}
		public void setMtowA(String mtowA) {
			this.mtowA = mtowA;
		}
		public String getMtowR() {
			return mtowR;
		}
		public void setMtowR(String mtowR) {
			this.mtowR = mtowR;
		}
		public String getWtomA() {
			return wtomA;
		}
		public void setWtomA(String wtomA) {
			this.wtomA = wtomA;
		}
		public String getWtomR() {
			return wtomR;
		}
		public void setWtomR(String wtomR) {
			this.wtomR = wtomR;
		}
}