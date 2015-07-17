package com.wallet.stat.model;

import com.rocomo.common.model.Common;

public class MwStMemberStatsAge extends Common {

		private String day;			//날짜
	  private String m0;			//남자 10대 미만
	  private String w0;			//여자 10대 미만
	  private String m1;			//남자 10대
	  private String w1;			//여자 10대
	  private String m2;			//남자 20대
	  private String w2;			//여자 20대
	  private String m3;			//남자 30대
	  private String w3;			//여자 30대
	  private String m4;			//남자 40대
	  private String w4;			//여자 40대
	  private String m5;			//남자 50대
	  private String w5;			//여자 50대
	  private String m6;			//남자 60대 이상
	  private String w6;			//여자 60대 이상
	  private String sM0;			//누적 남자 10대 미만
	  private String sW0;			//누적 여자 10대 미만
	  private String sM1;			//누적 남자 10대
	  private String sW1;			//누적 여자 10대
	  private String sM2;			//누적 남자 20대
	  private String sW2;			//누적 여자 20대
	  private String sM3;			//누적 남자 30대
	  private String sW3;			//누적 여자 30대
	  private String sM4;			//누적 남자 40대
	  private String sW4;			//누적 여자 40대
	  private String sM5;			//누적 남자 50대
	  private String sW5;			//누적 여자 50대
	  private String sM6;			//누적 남자 60대 이상
	  private String sW6;			//누적 여자 60대 이상
	  private String mw0Total;//10대 미만 합
	  private String mw1Total;//10대 합
	  private String mw2Total;//20대 합
	  private String mw3Total;//30대 합
	  private String mw4Total;//40대 합
	  private String mw5Total;//50대 합
	  private String mw6Total;//60대 이상 합
	  private String mTotal;	//남자 합계
	  private String wTotal;	//여자 합계
	  private String mwTotal;	//남녀 합계
	  

		
		public String getDay() {
			return day;
		}
		public void setDay(String day) {
			this.day = day;
		}
		public String getM0() {
			return m0;
		}
		public void setM0(String m0) {
			this.m0 = m0;
		}
		public String getW0() {
			return w0;
		}
		public void setW0(String w0) {
			this.w0 = w0;
		}
		public String getM1() {
			return m1;
		}
		public void setM1(String m1) {
			this.m1 = m1;
		}
		public String getW1() {
			return w1;
		}
		public void setW1(String w1) {
			this.w1 = w1;
		}
		public String getM2() {
			return m2;
		}
		public void setM2(String m2) {
			this.m2 = m2;
		}
		public String getW2() {
			return w2;
		}
		public void setW2(String w2) {
			this.w2 = w2;
		}
		public String getM3() {
			return m3;
		}
		public void setM3(String m3) {
			this.m3 = m3;
		}
		public String getW3() {
			return w3;
		}
		public void setW3(String w3) {
			this.w3 = w3;
		}
		public String getM4() {
			return m4;
		}
		public void setM4(String m4) {
			this.m4 = m4;
		}
		public String getW4() {
			return w4;
		}
		public void setW4(String w4) {
			this.w4 = w4;
		}
		public String getM5() {
			return m5;
		}
		public void setM5(String m5) {
			this.m5 = m5;
		}
		public String getW5() {
			return w5;
		}
		public void setW5(String w5) {
			this.w5 = w5;
		}
		public String getM6() {
			return m6;
		}
		public void setM6(String m6) {
			this.m6 = m6;
		}
		public String getW6() {
			return w6;
		}
		public void setW6(String w6) {
			this.w6 = w6;
		}
		public String getsM0() {
			return sM0;
		}
		public void setsM0(String sM0) {
			this.sM0 = sM0;
		}
		public String getsW0() {
			return sW0;
		}
		public void setsW0(String sW0) {
			this.sW0 = sW0;
		}
		public String getsM1() {
			return sM1;
		}
		public void setsM1(String sM1) {
			this.sM1 = sM1;
		}
		public String getsW1() {
			return sW1;
		}
		public void setsW1(String sW1) {
			this.sW1 = sW1;
		}
		public String getsM2() {
			return sM2;
		}
		public void setsM2(String sM2) {
			this.sM2 = sM2;
		}
		public String getsW2() {
			return sW2;
		}
		public void setsW2(String sW2) {
			this.sW2 = sW2;
		}
		public String getsM3() {
			return sM3;
		}
		public void setsM3(String sM3) {
			this.sM3 = sM3;
		}
		public String getsW3() {
			return sW3;
		}
		public void setsW3(String sW3) {
			this.sW3 = sW3;
		}
		public String getsM4() {
			return sM4;
		}
		public void setsM4(String sM4) {
			this.sM4 = sM4;
		}
		public String getsW4() {
			return sW4;
		}
		public void setsW4(String sW4) {
			this.sW4 = sW4;
		}
		public String getsM5() {
			return sM5;
		}
		public void setsM5(String sM5) {
			this.sM5 = sM5;
		}
		public String getsW5() {
			return sW5;
		}
		public void setsW5(String sW5) {
			this.sW5 = sW5;
		}
		public String getsM6() {
			return sM6;
		}
		public void setsM6(String sM6) {
			this.sM6 = sM6;
		}
		public String getsW6() {
			return sW6;
		}
		public void setsW6(String sW6) {
			this.sW6 = sW6;
		}
		public String getMw0Total() {
			return mw0Total;
		}
		public void setMw0Total(String mw0Total) {
			this.mw0Total = mw0Total;
		}
		public String getMw1Total() {
			return mw1Total;
		}
		public void setMw1Total(String mw1Total) {
			this.mw1Total = mw1Total;
		}
		public String getMw2Total() {
			return mw2Total;
		}
		public void setMw2Total(String mw2Total) {
			this.mw2Total = mw2Total;
		}
		public String getMw3Total() {
			return mw3Total;
		}
		public void setMw3Total(String mw3Total) {
			this.mw3Total = mw3Total;
		}
		public String getMw4Total() {
			return mw4Total;
		}
		public void setMw4Total(String mw4Total) {
			this.mw4Total = mw4Total;
		}
		public String getMw5Total() {
			return mw5Total;
		}
		public void setMw5Total(String mw5Total) {
			this.mw5Total = mw5Total;
		}
		public String getMw6Total() {
			return mw6Total;
		}
		public void setMw6Total(String mw6Total) {
			this.mw6Total = mw6Total;
		}
		public String getmTotal() {
			return mTotal;
		}
		public void setmTotal(String mTotal) {
			this.mTotal = mTotal;
		}
		public String getwTotal() {
			return wTotal;
		}
		public void setwTotal(String wTotal) {
			this.wTotal = wTotal;
		}
		public String getMwTotal() {
			return mwTotal;
		}
		public void setMwTotal(String mwTotal) {
			this.mwTotal = mwTotal;
		}
	  
}