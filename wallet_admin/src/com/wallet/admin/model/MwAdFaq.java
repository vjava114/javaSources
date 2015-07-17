package com.wallet.admin.model;

import com.rocomo.common.model.Common;


public class MwAdFaq extends Common {

    private Integer idx;	//idx
    private String part;	//구분(멤버십:MEM, 쿠폰:CPN ,결제:PAY, 카드:CRD, 사용자인증:UA, 기타:ETC)
    private String answer;//답변내용
    private String title;	//질문내용
    private String os;	//질문내용
    private String regTm;	//등록시간
    private String chgTm;	//변경시간
    
		
		public Integer getIdx() {
			return idx;
		}
		public void setIdx(Integer idx) {
			this.idx = idx;
		}
		public String getPart() {
			return part;
		}
		public void setPart(String part) {
			this.part = part == null ? null : part.trim();
		}
		public String getAnswer() {
			return answer;
		}
		public void setAnswer(String answer) {
			this.answer = answer == null ? null : answer.trim();
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title == null ? null : title.trim();
		}
		public String getRegTm() {
			return regTm;
		}
		public void setRegTm(String regTm) {
			this.regTm = regTm;
		}
		public String getChgTm() {
			return chgTm;
		}
		public void setChgTm(String chgTm) {
			this.chgTm = chgTm;
		}
		public String getOs() {
			return os;
		}
		public void setOs(String os) {
			this.os = os;
		}
		
		
		
}