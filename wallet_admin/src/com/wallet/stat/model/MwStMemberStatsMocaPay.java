package com.wallet.stat.model;

import com.rocomo.common.model.Common;

public class MwStMemberStatsMocaPay extends Common {
	
		private String day;			//날짜
		private String mtowA;		//모카페이에서 모카월렛 가입자 : 약관동의 고객
		private String mtowR;		//모카페이에서 모카월렛 가입자 : 모카월렛 가입완료
		private String wtomA;		//모카월렛에서 모카페이 가입자 : 약관동의 고객
		private String wtomR;		//모카월렛에서 모카페이 가입자 : 실 모카 가입
		private String mtowAS;	//
		private String mtowRS;	//
		private String wtomAS;	//
		private String wtomRS;	//
		private String wtomNewS;//신규가입자중 가입성공
		private String wtomNewF;//신규가입자 미가입
		private String wtomNewSA;//신규가입자중 가입성공합
		private String wtomNewFA;//신규가입자 미가입합
		
		
		public String getDay() {
			return day;
		}
		public void setDay(String day) {
			this.day = day;
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
		public String getMtowAS() {
			return mtowAS;
		}
		public void setMtowAS(String mtowAS) {
			this.mtowAS = mtowAS;
		}
		public String getMtowRS() {
			return mtowRS;
		}
		public void setMtowRS(String mtowRS) {
			this.mtowRS = mtowRS;
		}
		public String getWtomAS() {
			return wtomAS;
		}
		public void setWtomAS(String wtomAS) {
			this.wtomAS = wtomAS;
		}
		public String getWtomRS() {
			return wtomRS;
		}
		public void setWtomRS(String wtomRS) {
			this.wtomRS = wtomRS;
		}
		public String getWtomNewS() {
			return wtomNewS;
		}
		public void setWtomNewS(String wtomNewS) {
			this.wtomNewS = wtomNewS;
		}
		public String getWtomNewF() {
			return wtomNewF;
		}
		public void setWtomNewF(String wtomNewF) {
			this.wtomNewF = wtomNewF;
		}
		public String getWtomNewSA() {
			return wtomNewSA;
		}
		public void setWtomNewSA(String wtomNewSA) {
			this.wtomNewSA = wtomNewSA;
		}
		public String getWtomNewFA() {
			return wtomNewFA;
		}
		public void setWtomNewFA(String wtomNewFA) {
			this.wtomNewFA = wtomNewFA;
		}
		
	
}