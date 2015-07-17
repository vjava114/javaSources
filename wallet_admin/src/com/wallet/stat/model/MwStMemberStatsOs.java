package com.wallet.stat.model;

import com.rocomo.common.model.Common;

public class MwStMemberStatsOs extends Common {
	
		private String day;			//날짜
		private String aK;			//안드로이드 KT
		private String aS;			//안드로이드 SKT
		private String aL;			//안드로이드 LG
		private String iK;			//아이폰 KT
		private String iS;			//아이폰 SKT
		private String iL;			//아이폰 LG
		private String watIn;
		private String payIn;
		private String sAK;			//누적 안드로이드 KT
		private String sAS;			//누적 안드로이드 SKT
		private String sAL;			//누적 안드로이드 LG
		private String sIK;			//누적 아이폰 KT
		private String sIS;			//누적 아이폰 SKT
		private String sIL;			//누적 아이폰 LG
		private String sWatIn;
		private String sPayIn;
		private String aTotal; 		//안드로이드 소계
		private String iTotal;		//아이폰 소계
		private String newKTotal; //신규가입자 합계 : KT
		private String newSTotal;	//신규가입자 합계 : SKT
		private String newLTotal;	//신규가입자 합계 : LG
		private String newTotal;	//신규가입자 합계 : 소계 (KT + SKT + LG)
		private String sATotal;		//안드로이드 누적가입자 
		private String sITotal;		//아이폰 누적 가입자
		private String sTotal;		//누적가입자
		private String bSTotal;		//순증을 구하기 위한 전일 누적가입자
		private String sunjng;		//순증 (sTotal - bSTotal)
		
		private String mtowA;	//모카페이에서 모카월렛 가입자 : 약관동의 고객
		private String mtowR;	//모카페이에서 모카월렛 가입자 : 모카월렛 가입완료
		private String wtomA;	//모카월렛에서 모카페이 가입자 : 약관동의 고객
		private String wtomR;	//모카페이에서 모카월렛 가입자 : 실 모카 가입
		

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