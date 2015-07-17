package com.wallet.stat.model;

import com.rocomo.common.model.Common;

public class MwStSearchTerms extends Common {

    private String sday;		//날짜 - 시작일
    private String eday;		//날짜 - 종료일
    private String period;	//검색조건 : 일별,월별,년도별,누적 등
    private String kind;		//검색조건 : 단말기준, 이용자기준, 모카페이 등
    
    
		public String getSday() {
			return sday;
		}
		public void setSday(String sday) {
			this.sday = sday;
		}
		public String getEday() {
			return eday;
		}
		public void setEday(String eday) {
			this.eday = eday;
		}
		public String getPeriod() {
			return period;
		}
		public void setPeriod(String period) {
			this.period = period;
		}
		public String getKind() {
			return kind;
		}
		public void setKind(String kind) {
			this.kind = kind;
		}
   
}