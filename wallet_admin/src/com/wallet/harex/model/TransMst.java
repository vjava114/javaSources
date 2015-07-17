package com.wallet.harex.model;

import com.rocomo.common.model.Common;

@SuppressWarnings("serial")
public class TransMst extends Common {
	private Integer seq;   					// 번호
	private String acposTid;             	//복합결제거래번호	  
	private String trFg;                 		//거래구분	          
	private String cstatus;              //취소상태	          
	private String pstatus;              //처리상태	          
	private String trStatus;             //거래상태	          
	private String offerId;              //오퍼링ID	          
	private String custId;               //고객번호	          
	private String phoneNo;              //전화번호	          
	private String tcode;                //통신사코드	        
	private String serviceId;            //결제사ID	          
	private String kShopId;              //KT제휴사ID	        
	private String mocapayTid;           //모카페이거래번호	       
	private String mocapayTdate;         //모카페이거래일자	       
	private String mocapayTtime;         //모카페이거래시간	       
	private Integer ttlPrice;            //총결제금액	        
	private Integer expTtlPrice;         //결제예상금액	           
	private Integer realTtlPrice;        //실제결제금액	           
	private Integer expCpnDcPrice;       //쿠폰할인예정금액	      
	private Integer expMembDcPrice;      //멤버쉽할인예상금액	    
	private Integer expMembUsePoint;     //멤버쉽사용예상금액	    
	private Integer expMembSavePoint;    //멤버쉽적립예상금액	    
	private Integer expStampSaveCnt;     //스탬프적립예상갯수	    
	private Integer realCpnDcPrice;      //쿠폰실제할인금액	      
	private Integer realMembDcPrice;     //멤버쉽실제할인금액	    
	private Integer realMembUsePoint;    //멤버쉽실제사용금액	    
	private Integer realMembSavePoint;   //멤버쉽실제적립금액	    
	private Integer realStampSaveCnt;    //스탬프실제적립갯수	    
	private String orgAcposTid;          //복합결제원거래번호	    
	private String orgAcposTdate;        //복합결제원거래일자	    
	private String orgMocapayTid;        //모카페이원거래번호	    
	private String orgMocapayTdate;      //모카페이원거래일자	    
	private String trDate;               //거래일자	          
	private String mpBranch;             //오퍼링시 ACPOS_TID	
	private String mpSeq;                //모카페이 SEQ	      
	private String regDttm;              //등록일시	          
	private String lastDttm;             //최종수정일시	      
	private String ncancelYn;            //
	
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getAcposTid() {
		return acposTid;
	}
	public void setAcposTid(String acposTid) {
		this.acposTid = acposTid;
	}
	public String getTrFg() {
		return trFg;
	}
	public void setTrFg(String trFg) {
		this.trFg = trFg;
	}
	public String getCstatus() {
		return cstatus;
	}
	public void setCstatus(String cstatus) {
		this.cstatus = cstatus;
	}
	public String getPstatus() {
		return pstatus;
	}
	public void setPstatus(String pstatus) {
		this.pstatus = pstatus;
	}
	public String getTrStatus() {
		return trStatus;
	}
	public void setTrStatus(String trStatus) {
		this.trStatus = trStatus;
	}
	public String getOfferId() {
		return offerId;
	}
	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getTcode() {
		return tcode;
	}
	public void setTcode(String tcode) {
		this.tcode = tcode;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getkShopId() {
		return kShopId;
	}
	public void setkShopId(String kShopId) {
		this.kShopId = kShopId;
	}
	public String getMocapayTid() {
		return mocapayTid;
	}
	public void setMocapayTid(String mocapayTid) {
		this.mocapayTid = mocapayTid;
	}
	public String getMocapayTdate() {
		return mocapayTdate;
	}
	public void setMocapayTdate(String mocapayTdate) {
		this.mocapayTdate = mocapayTdate;
	}
	public String getMocapayTtime() {
		return mocapayTtime;
	}
	public void setMocapayTtime(String mocapayTtime) {
		this.mocapayTtime = mocapayTtime;
	}
	public Integer getTtlPrice() {
		return ttlPrice;
	}
	public void setTtlPrice(Integer ttlPrice) {
		this.ttlPrice = ttlPrice;
	}
	public Integer getExpTtlPrice() {
		return expTtlPrice;
	}
	public void setExpTtlPrice(Integer expTtlPrice) {
		this.expTtlPrice = expTtlPrice;
	}
	public Integer getRealTtlPrice() {
		return realTtlPrice;
	}
	public void setRealTtlPrice(Integer realTtlPrice) {
		this.realTtlPrice = realTtlPrice;
	}
	public Integer getExpCpnDcPrice() {
		return expCpnDcPrice;
	}
	public void setExpCpnDcPrice(Integer expCpnDcPrice) {
		this.expCpnDcPrice = expCpnDcPrice;
	}
	public Integer getExpMembDcPrice() {
		return expMembDcPrice;
	}
	public void setExpMembDcPrice(Integer expMembDcPrice) {
		this.expMembDcPrice = expMembDcPrice;
	}
	public Integer getExpMembUsePoint() {
		return expMembUsePoint;
	}
	public void setExpMembUsePoint(Integer expMembUsePoint) {
		this.expMembUsePoint = expMembUsePoint;
	}
	public Integer getExpMembSavePoint() {
		return expMembSavePoint;
	}
	public void setExpMembSavePoint(Integer expMembSavePoint) {
		this.expMembSavePoint = expMembSavePoint;
	}
	public Integer getExpStampSaveCnt() {
		return expStampSaveCnt;
	}
	public void setExpStampSaveCnt(Integer expStampSaveCnt) {
		this.expStampSaveCnt = expStampSaveCnt;
	}
	public Integer getRealCpnDcPrice() {
		return realCpnDcPrice;
	}
	public void setRealCpnDcPrice(Integer realCpnDcPrice) {
		this.realCpnDcPrice = realCpnDcPrice;
	}
	public Integer getRealMembDcPrice() {
		return realMembDcPrice;
	}
	public void setRealMembDcPrice(Integer realMembDcPrice) {
		this.realMembDcPrice = realMembDcPrice;
	}
	public Integer getRealMembUsePoint() {
		return realMembUsePoint;
	}
	public void setRealMembUsePoint(Integer realMembUsePoint) {
		this.realMembUsePoint = realMembUsePoint;
	}
	public Integer getRealMembSavePoint() {
		return realMembSavePoint;
	}
	public void setRealMembSavePoint(Integer realMembSavePoint) {
		this.realMembSavePoint = realMembSavePoint;
	}
	public Integer getRealStampSaveCnt() {
		return realStampSaveCnt;
	}
	public void setRealStampSaveCnt(Integer realStampSaveCnt) {
		this.realStampSaveCnt = realStampSaveCnt;
	}
	public String getOrgAcposTid() {
		return orgAcposTid;
	}
	public void setOrgAcposTid(String orgAcposTid) {
		this.orgAcposTid = orgAcposTid;
	}
	public String getOrgAcposTdate() {
		return orgAcposTdate;
	}
	public void setOrgAcposTdate(String orgAcposTdate) {
		this.orgAcposTdate = orgAcposTdate;
	}
	public String getOrgMocapayTid() {
		return orgMocapayTid;
	}
	public void setOrgMocapayTid(String orgMocapayTid) {
		this.orgMocapayTid = orgMocapayTid;
	}
	public String getOrgMocapayTdate() {
		return orgMocapayTdate;
	}
	public void setOrgMocapayTdate(String orgMocapayTdate) {
		this.orgMocapayTdate = orgMocapayTdate;
	}
	public String getTrDate() {
		return trDate;
	}
	public void setTrDate(String trDate) {
		this.trDate = trDate;
	}
	public String getMpBranch() {
		return mpBranch;
	}
	public void setMpBranch(String mpBranch) {
		this.mpBranch = mpBranch;
	}
	public String getMpSeq() {
		return mpSeq;
	}
	public void setMpSeq(String mpSeq) {
		this.mpSeq = mpSeq;
	}
	public String getRegDttm() {
		return regDttm;
	}
	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}
	public String getLastDttm() {
		return lastDttm;
	}
	public void setLastDttm(String lastDttm) {
		this.lastDttm = lastDttm;
	}
	public String getNcancelYn() {
		return ncancelYn;
	}
	public void setNcancelYn(String ncancelYn) {
		this.ncancelYn = ncancelYn;
	}   
	
}
