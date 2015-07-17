package com.wallet.stat.model;

import java.util.Date;

import com.rocomo.common.model.Common;

public class MwStCmCode extends Common {

	private String grpCode;		//그룹코드
	private String comCd;			//공통코드
	private String comCdVal;	//코드이름
	private String comCdDesc;	//코드desc
  private Integer seqNo;		//seqno
  private String useYn;			//사용여부
  private String remark1;
  private String remark2;
  private String remark3;
  private String remark4;
  private String regUser;		//등록자명
  private Date regDtm;			//등록일
  private String chgUser;		//변경일
  private Date chgDtm;			//변경시간
  
 
	public String getGrpCode() {
		return grpCode;
	}
	public void setGrpCode(String grpCode) {
		this.grpCode = grpCode;
	}
	public String getComCd() {
		return comCd;
	}
	public void setComCd(String comCd) {
		this.comCd = comCd;
	}
	public String getComCdVal() {
		return comCdVal;
	}
	public void setComCdVal(String comCdVal) {
		this.comCdVal = comCdVal;
	}
	public String getComCdDesc() {
		return comCdDesc;
	}
	public void setComCdDesc(String comCdDesc) {
		this.comCdDesc = comCdDesc;
	}
	public Integer getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getRemark1() {
		return remark1;
	}
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}
	public String getRemark2() {
		return remark2;
	}
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}
	public String getRemark3() {
		return remark3;
	}
	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}
	public String getRemark4() {
		return remark4;
	}
	public void setRemark4(String remark4) {
		this.remark4 = remark4;
	}
	public String getRegUser() {
		return regUser;
	}
	public void setRegUser(String regUser) {
		this.regUser = regUser;
	}
	public Date getRegDtm() {
		return regDtm;
	}
	public void setRegDtm(Date regDtm) {
		this.regDtm = regDtm;
	}
	public String getChgUser() {
		return chgUser;
	}
	public void setChgUser(String chgUser) {
		this.chgUser = chgUser;
	}
	public Date getChgDtm() {
		return chgDtm;
	}
	public void setChgDtm(Date chgDtm) {
		this.chgDtm = chgDtm;
	}
   
  
}